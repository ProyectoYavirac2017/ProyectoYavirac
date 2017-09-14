/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.servicio;

import ec.edu.yavirac.instituto.dao.DAOServices;
import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.Ievaluacion;
import ec.edu.yavirac.instituto.modelo.Evaluacion;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
/**
 *
 * @author Labs-DECC
 */
public class EvaluacionServicio implements Ievaluacion,Serializable {
    
    private static final long serialVersionUID = -5971913672287412493L;
    
    
    @Override
    public Boolean insert(Evaluacion evaluacion, HibernateUtil util) {
        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (evaluacion != null) {
                ds.save(evaluacion);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;

    }

    @Override
    public Boolean update(Evaluacion evaluacion, HibernateUtil util) {

        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (evaluacion != null) {
                ds.update(evaluacion);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;

    }

    @Override
    public Evaluacion findById(Evaluacion evaluacion, HibernateUtil util) {

        Evaluacion findmEvaluacion = null;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());

            QueryParameter query_1 = new QueryParameter(QueryParameter.$TYPE_WHERE);
            query_1.setColumnName("ID_EVALUACION");
            query_1.setWhereClause("=");
            query_1.setValue(evaluacion.getIdEvaluacion());

            List parameList = new ArrayList();
            parameList.add(query_1);

            List<Evaluacion> listClients = ds.customQuery(parameList, Evaluacion.class);

            if (!listClients.isEmpty()) {
                findmEvaluacion = listClients.get(0);
            }

        } catch (Exception ex) {
            // log.level.info("findById : " + ex.getMessage());
        }

        return findmEvaluacion;

    }

    @Override
    public List<Evaluacion> listEvaluaciones(HibernateUtil util) {
         List<Evaluacion> mEvaluacions = null;
        try {
            
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            
            List parameList = new ArrayList();
            
            List<Evaluacion> listEvaluacions = ds.customQuery(parameList, Evaluacion.class);
            if (!listEvaluacions.isEmpty()) {
                mEvaluacions = (List<Evaluacion>) listEvaluacions;
            }
        } catch (Exception ex) {
            // log.level.info("getListEvaluacions : " + ex.getMessage());
        }

        return mEvaluacions;
    }
    
}
