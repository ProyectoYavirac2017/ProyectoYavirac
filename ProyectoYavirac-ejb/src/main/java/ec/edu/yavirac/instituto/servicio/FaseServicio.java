/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.servicio;

import ec.edu.yavirac.instituto.dao.DAOServices;
import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.Ifase;
import ec.edu.yavirac.instituto.modelo.Fase;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Labs-DECC
 */
public class FaseServicio implements Ifase, Serializable {

    private static final long serialVersionUID = 438604383147078831L;

    @Override
    public Boolean insert(Fase fase, HibernateUtil util) {
        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (fase != null) {
                ds.save(fase);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Boolean update(Fase fase, HibernateUtil util) {
        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (fase != null) {
                ds.update(fase);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Fase findById(Fase fase, HibernateUtil util) {
        Fase findmFase = null;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());

            QueryParameter query_1 = new QueryParameter(QueryParameter.$TYPE_WHERE);
            query_1.setColumnName("cliIdcli");
            query_1.setWhereClause("=");
            query_1.setValue(fase.getIdFase());

            List parameList = new ArrayList();
            parameList.add(query_1);

            List<Fase> listClients = ds.customQuery(parameList, Fase.class);

            if (!listClients.isEmpty()) {
                findmFase = listClients.get(0);
            }

        } catch (Exception ex) {
            // log.level.info("findById : " + ex.getMessage());
        }

        return findmFase;
    }

    @Override
    public List<Fase> listFases(HibernateUtil util) {
        List<Fase> mFases = null;
        try {
            
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            
            List parameList = new ArrayList();
            
            List<Fase> listFases = ds.customQuery(parameList, Fase.class);
            if (!listFases.isEmpty()) {
                mFases = (List<Fase>) listFases;
            }
        } catch (Exception ex) {
            // log.level.info("getListFases : " + ex.getMessage());
        }

        return mFases;
    }
    
}
