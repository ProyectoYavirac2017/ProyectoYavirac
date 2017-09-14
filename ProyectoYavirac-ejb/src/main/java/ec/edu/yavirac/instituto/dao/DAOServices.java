package ec.edu.yavirac.instituto.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.edu.yavirac.instituto.logger.L;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class DAOServices<T> {

   private final static L log = new L(DAOServices.class);

    private Session session;

    public DAOServices(Session session) {
        this.session = session;
    }

    public Boolean update(T t) throws HibernateException {
        Boolean success = false;
        try {
            session.update(t);
            success = true;
        } catch (HibernateException e) {
            log.level.info(e.getMessage());
            throw e;
        }
        return success;
    }

    public T save(T t) throws HibernateException {
        T object = null;
        try {
            object = (T) session.merge(t);
        } catch (HibernateException e) {
            log.level.info(e.getMessage());
            throw e;
        }
        return object;
    }

    public Boolean delete(T t) throws HibernateException {
        Boolean success = false;
        try {
            session.delete(t);
            success = true;
        } catch (HibernateException e) {
            log.level.info(e.getMessage());
            throw e;
        }
        return success;
    }

    public T retriveEntity(Class s, Object id) throws HibernateException {
        return (T) session.get(s, (Serializable) id);
    }

    public List<T> customQuery(List<QueryParameter> parameters,
            Class principalEntity) throws HibernateException {
        List<T> result = null;
        result = prepareCustomQuery(parameters, principalEntity).getExecutableCriteria(session).list();
        return result;
    }

    public List<T> customQueryLazy(List<QueryParameter> parameters,
            Class principalEntity, int first, int pageSize) throws HibernateException {
        Criteria queryCriteria = prepareCustomQuery(parameters, principalEntity).getExecutableCriteria(session);
        queryCriteria.setFirstResult(first);
        queryCriteria.setMaxResults(pageSize);
        return queryCriteria.list();
    }

    public int totalQueryResults(List<QueryParameter> parameters,
            Class principalEntity) throws HibernateException {
        return prepareCustomQuery(parameters, principalEntity).getExecutableCriteria(session).list().size();
    }

    public DetachedCriteria prepareCustomQuery(List<QueryParameter> parameters,
            Class principalEntity) throws HibernateException {

        DetachedCriteria queryCriteria = DetachedCriteria.forClass(principalEntity, "Principal");

        Boolean[] detachedTypes = validateDetachedParameters(parameters);
        if (detachedTypes[0]) {
            queryCriteria = createConjunctionQuery(queryCriteria, parameters);
        }
        if (detachedTypes[1]) {
            queryCriteria = createDisjunctionQuery(queryCriteria, parameters);
        }

        Boolean[] parameterTypes = validateParameters(parameters);
        if (parameterTypes[0]) {
            try {
                queryCriteria = createUnicResultQuery(queryCriteria, parameters);
            } catch (Exception ex) {
                throw new HibernateException(ex.getMessage());
            }
        }
        if (parameterTypes[1]) {
            queryCriteria = createJoins(queryCriteria, parameters);
        }
        if (parameterTypes[2]) {
            queryCriteria = createRegularWhere(queryCriteria, parameters);
        }
        if (parameterTypes[3]) {
            queryCriteria = createOrder(queryCriteria, parameters);
        }
        if (parameterTypes[4]) {
            queryCriteria = createFetch(queryCriteria, parameters);
        }
        return queryCriteria;
    }

    private Boolean[] validateParameters(List<QueryParameter> parameters) {
        Boolean[] revisionFlags = new Boolean[]{false, false, false, false, false};
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().equals("COUNT")
                    || parameters.get(i).getType().equals("MIN")
                    || parameters.get(i).getType().equals("MAX")
                    || parameters.get(i).getType().equals("DISTINCT")) {
                revisionFlags[0] = true;
            } else if ((parameters.get(i).getType().equals("JOIN"))
                    && (parameters.get(i).getColumnName() != null)) {
                revisionFlags[1] = true;
            } else if (parameters.get(i).getType().equals("WHERE")) {
                revisionFlags[2] = true;
            } else if (parameters.get(i).getType().equals("ORDER")) {
                revisionFlags[3] = true;
            } else if (parameters.get(i).getType().equals("FETCH")) {
                revisionFlags[4] = true;
            }
        }
        return revisionFlags;
    }

    private Boolean[] validateDetachedParameters(List<QueryParameter> parameters) {
        Boolean[] revisionFlags = new Boolean[]{false, false};
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().equals(QueryParameter.$TYPE_AND) && parameters.get(i).getDetachedParameters().size() > 0) {
                revisionFlags[0] = true;
            } else if (parameters.get(i).getType().equals(QueryParameter.$TYPE_OR) && parameters.get(i).getDetachedParameters().size() > 0) {
                revisionFlags[1] = true;
            }
        }
        return revisionFlags;
    }

    private DetachedCriteria createConjunctionQuery(DetachedCriteria detachedCriteria,
            List<QueryParameter> parameters) {

        List<QueryParameter> conjuctionParameters = new ArrayList<>();
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().equals(QueryParameter.$TYPE_AND)) {
                conjuctionParameters.add(parameters.get(i));
            }
        }

        for (int i = 0; i < conjuctionParameters.size(); i++) {
            Boolean[] detachedParameters = validateDetachedParameters(conjuctionParameters.get(i).getDetachedParameters());
            if (detachedParameters[0]) {
                detachedCriteria = createConjunctionQuery(detachedCriteria, conjuctionParameters.get(i).getDetachedParameters());
            }

            if (detachedParameters[1]) {
                detachedCriteria = createDisjunctionQuery(detachedCriteria, conjuctionParameters.get(i).getDetachedParameters());
            }

            detachedCriteria = addAllCriterionToConjunction(detachedCriteria, createDetachedWhere(conjuctionParameters.get(i).getDetachedParameters()));
        }
        return detachedCriteria;
    }

    private DetachedCriteria createDisjunctionQuery(DetachedCriteria detachedCriteria,
            List<QueryParameter> parameters) {

        List<QueryParameter> disjuctionParameters = new ArrayList<>();
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().equals(QueryParameter.$TYPE_OR)) {
                disjuctionParameters.add(parameters.get(i));
            }
        }

        for (int i = 0; i < disjuctionParameters.size(); i++) {
            Boolean[] detachedParameters = validateDetachedParameters(disjuctionParameters.get(i).getDetachedParameters());
            if (detachedParameters[0]) {
                detachedCriteria = createConjunctionQuery(detachedCriteria, disjuctionParameters.get(i).getDetachedParameters());
            }

            if (detachedParameters[1]) {
                detachedCriteria = createDisjunctionQuery(detachedCriteria, disjuctionParameters.get(i).getDetachedParameters());
            }

            detachedCriteria = addAllCriterionToDisjunction(detachedCriteria, createDetachedWhere(disjuctionParameters.get(i).getDetachedParameters()));
        }
        return detachedCriteria;
    }

    private DetachedCriteria createUnicResultQuery(DetachedCriteria detachedCriteria,
            List<QueryParameter> parameters) throws Exception {
        Integer count = 0;
        Integer distinctPosition = 1;
        Integer unicType = 0;
        List<QueryParameter> unicResultParameters = new ArrayList();
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().equals("COUNT")) {
                count++;
                unicType = 1;
                unicResultParameters.add(0, parameters.get(i));
            } else if (parameters.get(i).getType().equals("MAX")) {
                count++;
                unicType = 2;
                unicResultParameters.add(0, parameters.get(i));
            } else if (parameters.get(i).getType().equals("MIN")) {
                count++;
                unicType = 3;
                unicResultParameters.add(0, parameters.get(i));
            } else if (parameters.get(i).getType().equals("DISTINCT")) {
                unicResultParameters.add(distinctPosition, parameters.get(i));
                distinctPosition++;
            }
        }

        if (count > 1) {
            throw new Exception("Existen mas de dos parametros Unique Result.");
        }

        if (count == 1) {
            switch (unicType) {
                case 1:
                    detachedCriteria.setProjection(Projections.count(
                            unicResultParameters.get(0).getColumnName()));
                    break;
                case 2:
                    detachedCriteria.setProjection(
                            Projections.max(
                                    unicResultParameters.get(0).getColumnName()));
                    break;
                case 3:
                    detachedCriteria.setProjection(
                            Projections.min(
                                    unicResultParameters.get(0).getColumnName()));
                    break;
            }
        }

        for (int j = count;
                j < unicResultParameters.size();
                j++) {
            detachedCriteria.setProjection(Projections.distinct(
                    Projections.property(
                            unicResultParameters.get(j).getColumnName())));
        }

        return detachedCriteria;
    }

    private DetachedCriteria createJoins(DetachedCriteria detachedCriteria,
            List<QueryParameter> parameters) {

        List<QueryParameter> joinParameters = new ArrayList();
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().equals("JOIN")) {
                joinParameters.add(parameters.get(i).getJoinOrderNumber() - 1,
                        parameters.get(i));
            }
        }

        detachedCriteria.createAlias(detachedCriteria.getAlias() + "."
                + joinParameters.get(0).getColumnName(),
                joinParameters.get(0).getJoinAlias());

        for (int j = 1; j < joinParameters.size(); j++) {
            detachedCriteria.createAlias(joinParameters.get(j - 1).getJoinAlias()
                    + "." + joinParameters.get(j).getColumnName(),
                    joinParameters.get(j).getJoinAlias());
        }

        return detachedCriteria;
    }

    private DetachedCriteria createRegularWhere(DetachedCriteria detachedCriteria,
            List<QueryParameter> parameters) {
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getWhereClause() != null) {
                if (parameters.get(i).getWhereClause().equals("=")) {
                    detachedCriteria.add(Restrictions.eq(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals(">")) {
                    detachedCriteria.add(Restrictions.gt(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals(">=")) {
                    detachedCriteria.add(Restrictions.ge(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals("<")) {
                    detachedCriteria.add(Restrictions.lt(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals("<=")) {
                    detachedCriteria.add(Restrictions.le(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals("<>")) {
                    ArrayList betweenParams = (ArrayList) parameters.get(i).getValue();
                    detachedCriteria.add(Restrictions.between(
                            parameters.get(i).getColumnName(),
                            betweenParams.get(0), betweenParams.get(1)));
                } else if (parameters.get(i).getWhereClause().equals("!=")) {
                    detachedCriteria.add(Restrictions.ne(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals("in")) {
                    ArrayList inParams = (ArrayList) parameters.get(i).getValue();
                    detachedCriteria.add(Restrictions.in(
                            parameters.get(i).getColumnName(), inParams));
                } else if (parameters.get(i).getWhereClause().equals("null")) {
                    detachedCriteria.add(Restrictions.isNull(
                            parameters.get(i).getColumnName()));
                } else if(parameters.get(i).getWhereClause().equals("%")) {
                    detachedCriteria.add(Restrictions.like(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                }
            }
        }
        return detachedCriteria;
    }

    private List<Criterion> createDetachedWhere(List<QueryParameter> parameters) {
        List<Criterion> criterion = new ArrayList<>();
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().equals(QueryParameter.$TYPE_WHERE)
                    && parameters.get(i).getWhereClause() != null) {
                if (parameters.get(i).getWhereClause().equals("=")) {
                    criterion.add(Restrictions.eq(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals(">")) {
                    criterion.add(Restrictions.gt(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals(">=")) {
                    criterion.add(Restrictions.ge(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals("<")) {
                    criterion.add(Restrictions.lt(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals("<=")) {
                    criterion.add(Restrictions.le(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals("<>")) {
                    ArrayList betweenParams = (ArrayList) parameters.get(i).getValue();
                    criterion.add(Restrictions.between(
                            parameters.get(i).getColumnName(),
                            betweenParams.get(0), betweenParams.get(1)));
                } else if (parameters.get(i).getWhereClause().equals("!=")) {
                    criterion.add(Restrictions.ne(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                } else if (parameters.get(i).getWhereClause().equals("in")) {
                    ArrayList inParams = (ArrayList) parameters.get(i).getValue();
                    criterion.add(Restrictions.in(
                            parameters.get(i).getColumnName(), inParams));
                } else if (parameters.get(i).getWhereClause().equals("null")) {
                    criterion.add(Restrictions.isNull(
                            parameters.get(i).getColumnName()));
                }else if(parameters.get(i).getWhereClause().equals("%")) {
                    criterion.add(Restrictions.like(
                            parameters.get(i).getColumnName(),
                            parameters.get(i).getValue()));
                }
            }
        }
        return criterion;
    }

    private DetachedCriteria addAllCriterionToConjunction(DetachedCriteria detachedCriteria,
            List<Criterion> criterion) {
        Conjunction conjuction = Restrictions.conjunction();
        for (int i = 0; i < criterion.size(); i++) {
            conjuction.add(criterion.get(i));
        }
        detachedCriteria.add(conjuction);
        return detachedCriteria;
    }

    private DetachedCriteria addAllCriterionToDisjunction(DetachedCriteria detachedCriteria,
            List<Criterion> criterion) {
        Disjunction disjunction = Restrictions.disjunction();
        for (int i = 0; i < criterion.size(); i++) {
            disjunction.add(criterion.get(i));
        }
        detachedCriteria.add(disjunction);
        return detachedCriteria;
    }

    private DetachedCriteria createOrder(DetachedCriteria detachedCriteria,
            List<QueryParameter> parameters) {
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().compareTo(QueryParameter.$TYPE_ORDER) == 0) {
                if (parameters.get(i).getValue().equals("ASC")) {
                    detachedCriteria.addOrder(Order.asc(
                            parameters.get(i).getColumnName()));
                } else {
                    detachedCriteria.addOrder(Order.desc(
                            parameters.get(i).getColumnName()));
                }
            }
        }
        return detachedCriteria;
    }

    private DetachedCriteria createFetch(DetachedCriteria detachedCriteria,
            List<QueryParameter> parameters) {
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getType().equals("FETCH")) {
                List<String> fetch = parameters.get(i).getFetch();
                for (int j = 0; j < fetch.size(); j++) {
                    FetchMode temp = FetchMode.JOIN;
                    if (parameters.get(i).getFetchMode()!=null){
                        temp = parameters.get(i).getFetchMode();
                    }
                    detachedCriteria.setFetchMode(fetch.get(j), temp);
                }
            }
        }
        return detachedCriteria;
    }

    public List<T> createSQLQuery(String sqlString,
            List<QueryParameter> parameters, Class entidad) {

        SQLQuery SQL = session.createSQLQuery(sqlString);
        if (entidad != null) {
            SQL.addEntity(entidad);
        }

        if (parameters.size() > 0) {
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i).getColumnName() != null
                        && !parameters.get(i).getColumnName().equals("")
                        && parameters.get(i).getValue() != null
                        && !parameters.get(i).getValue().equals("")) {
                    SQL.setParameter(parameters.get(i).getColumnName(), parameters.get(i).getValue());
                }
            }
        }
        return SQL.list();
    }
    
    public List<T> createSQLQuery(String sqlString) {
        SQLQuery SQL = session.createSQLQuery(sqlString);
        return SQL.list();
    }

    public Integer updateSQL(String updateSQLString,
            List<QueryParameter> parameters) {
        Integer filasActualizadas = 0;
        SQLQuery SQL = session.createSQLQuery(updateSQLString);
        if (parameters.size() > 0) {
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i).getColumnName() != null
                        && !parameters.get(i).getColumnName().equals("")
                        && parameters.get(i).getValue() != null
                        && !parameters.get(i).getValue().equals("")) {
                    SQL.setParameter(parameters.get(i).getColumnName(),
                            parameters.get(i).getValue());
                }
            }
        }
        filasActualizadas = SQL.executeUpdate();
        return filasActualizadas;
    }

    public Integer updateSQL(List<QueryParameter> updatedParameters,
            List<QueryParameter> conditions, Class entity) {

        Integer filasActualizadas = 0;
        StringBuilder SQLString = new StringBuilder("update ");
        SQLString.append(entity.getSimpleName().toLowerCase());
        SQLString.append(" set ");
        if (updatedParameters.size() > 0) {
            for (int i = 0; i < updatedParameters.size(); i++) {
                if (updatedParameters.get(i).getColumnName() != null
                        && !updatedParameters.get(i).getColumnName().equals("")
                        && updatedParameters.get(i).getValue() != null) {
                    if (i > 0) {
                        SQLString.append(",");
                    }
                    SQLString.append(updatedParameters.get(i).getColumnName());
                    SQLString.append("=:");
                    SQLString.append(updatedParameters.get(i).getColumnName());
                }
            }
        }

        SQLString.append(" where ");
        if (conditions.size() > 0) {
            for (int i = 0; i < conditions.size(); i++) {
                if (conditions.get(i).getColumnName() != null
                        && !conditions.get(i).getColumnName().equals("")
                        && conditions.get(i).getValue() != null
                        && !conditions.get(i).getValue().equals("")) {
                    if (i > 0) {
                        SQLString.append(" and ");
                    }
                    SQLString.append(conditions.get(i).getColumnName());
                    SQLString.append("=:");
                    SQLString.append(conditions.get(i).getColumnName());
                }
            }
        }

        Query SQL = session.createSQLQuery(SQLString.toString());
        if (updatedParameters.size() > 0) {
            for (int i = 0; i < updatedParameters.size(); i++) {
                if (updatedParameters.get(i).getColumnName() != null
                        && !updatedParameters.get(i).getColumnName().equals("")
                        && updatedParameters.get(i).getValue() != null) {
                    SQL.setParameter(updatedParameters.get(i).getColumnName(),
                            updatedParameters.get(i).getValue());
                }
            }
        }
        if (conditions.size() > 0) {
            for (int i = 0; i < conditions.size(); i++) {
                if (conditions.get(i).getColumnName() != null
                        && !conditions.get(i).getColumnName().equals("")
                        && conditions.get(i).getValue() != null
                        && !conditions.get(i).getValue().equals("")) {
                    SQL.setParameter(conditions.get(i).getColumnName(),
                            conditions.get(i).getValue());
                }
            }
        }

        filasActualizadas = SQL.executeUpdate();
        return filasActualizadas;
    }
}
