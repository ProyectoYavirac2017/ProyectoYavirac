/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.servicio;

import ec.edu.yavirac.instituto.dao.DAOServices;
import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.Iproyecto;
import ec.edu.yavirac.instituto.modelo.Proyecto;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Labs-DECC
 */

@Stateless
@LocalBean
public class ProyectoServicio implements Iproyecto, Serializable  {
      private static final long serialVersionUID = -5971913672287412493L;
    @Override
    public Boolean insert(Proyecto proyecto, HibernateUtil util) {
             Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (proyecto != null) {
                ds.save(proyecto);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Boolean update(Proyecto proyecto, HibernateUtil util) {
                Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (proyecto != null) {
                ds.update(proyecto);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Proyecto findById(Proyecto proyecto, HibernateUtil util) {
           Proyecto findmProyecto = null;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());

            QueryParameter query_1 = new QueryParameter(QueryParameter.$TYPE_WHERE);
            query_1.setColumnName("idproyecto");//idProyecto
            query_1.setWhereClause("=");
            query_1.setValue(proyecto.getIdProyecto());

            List parameList = new ArrayList();
            parameList.add(query_1);

            List<Proyecto> listProyectos = ds.customQuery(parameList, Proyecto.class);

            if (!listProyectos.isEmpty()) {
                findmProyecto = listProyectos.get(0);
            }

        } catch (Exception ex) {
            // log.level.info("findById : " + ex.getMessage());
        }

        return findmProyecto;
    }

    @Override
    public List<Proyecto> listProyecto(HibernateUtil util) {
             List<Proyecto> mProyectos = null;
        try {
            
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            
            List parameList = new ArrayList();
            
            List<Proyecto> listProyectos = ds.customQuery(parameList, Proyecto.class);
            if (!listProyectos.isEmpty()) {
                mProyectos = (List<Proyecto>) listProyectos;
            }
        } catch (Exception ex) {
            // log.level.info("getListClientes : " + ex.getMessage());
        }

        return mProyectos;
    }
    
    
}
