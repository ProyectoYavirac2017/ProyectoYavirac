/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.servicio;

import ec.edu.yavirac.instituto.dao.DAOServices;
import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.Ievidencia;
import ec.edu.yavirac.instituto.modelo.Evidencia;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Labs-DECC
 */
public class EvidenciaServicio implements Ievidencia, Serializable{
    private static final long serialVersionUID = -5971913672287412493L;

    @Override
    public Boolean insert(Evidencia evidencia, HibernateUtil util) {
        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (evidencia != null) {
                ds.save(evidencia);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Boolean update(Evidencia evidencia, HibernateUtil util) {
        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (evidencia != null) {
                ds.update(evidencia);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }
        

    @Override
    public Evidencia findById(Evidencia evidencia, HibernateUtil util) {
        Evidencia findmEvidencia = null;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());

            QueryParameter query_1 = new QueryParameter(QueryParameter.$TYPE_WHERE);
            query_1.setColumnName("idEvidencia");
            query_1.setWhereClause("=");
            query_1.setValue(evidencia.getIdEvidencia());

            List parameList = new ArrayList();
            parameList.add(query_1);

            List<Evidencia> listEvidencias = ds.customQuery(parameList, Evidencia.class);

            if (!listEvidencias.isEmpty()) {
                findmEvidencia = listEvidencias.get(0);
            }

        } catch (Exception ex) {
            // log.level.info("findById : " + ex.getMessage());
        }

        return findmEvidencia;
    }
    
    @Override
    public List<Evidencia> listEvidencias(HibernateUtil util) {
    List<Evidencia> mEvidencias = null;
        try {
            
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            
            List parameList = new ArrayList();
            
            List<Evidencia> listEvidencias = ds.customQuery(parameList, Evidencia.class);
            if (!listEvidencias.isEmpty()) {
                mEvidencias = (List<Evidencia>) listEvidencias;
            }
        } catch (Exception ex) {
            // log.level.info("getListEvidencias : " + ex.getMessage());
        }

        return mEvidencias;
    }
}

    
