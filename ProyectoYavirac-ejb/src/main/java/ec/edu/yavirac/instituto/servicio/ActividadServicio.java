/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.servicio;

import ec.edu.yavirac.instituto.dao.DAOServices;
import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.iActividad;
import ec.edu.yavirac.instituto.modelo.Actividad;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean

public class ActividadServicio implements iActividad, Serializable{
    
    private static final long serialVersionUID = -5971913672287412493L;
    

    @Override
    public Boolean insert(Actividad actividad,HibernateUtil util) {
        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (actividad != null) {
                ds.save(actividad);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Boolean update(Actividad actividad, HibernateUtil util) {
        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (actividad != null) {
                ds.update(actividad);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Actividad findById(Actividad actividad, HibernateUtil util) {
    Actividad findmActividad = null;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());

            QueryParameter query_1 = new QueryParameter(QueryParameter.$TYPE_WHERE);
            query_1.setColumnName("idActividad");
            query_1.setWhereClause("=");
            query_1.setValue(actividad.getIdActividad());

            List parameList = new ArrayList();
            parameList.add(query_1);

            List<Actividad> listClients = ds.customQuery(parameList, Actividad.class);

            if (!listClients.isEmpty()) {
                findmActividad = listClients.get(0);
            }

        } catch (Exception ex) {
            // log.level.info("findById : " + ex.getMessage());
        }

        return findmActividad;
    }

    @Override
    public List<Actividad> listActividads(HibernateUtil util) {
     List<Actividad> mActividads = null;
        try {
            
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            
            List parameList = new ArrayList();
            
            List<Actividad> listActividads = ds.customQuery(parameList, Actividad.class);
            if (!listActividads.isEmpty()) {
                mActividads = (List<Actividad>) listActividads;
            }
        } catch (Exception ex) {
            // log.level.info("getListActividads : " + ex.getMessage());
        }

        return mActividads;
    }
    
}
