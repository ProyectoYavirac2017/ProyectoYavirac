/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.servicio;

import ec.edu.yavirac.instituto.dao.DAOServices;
import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.Iasistencia;
import ec.edu.yavirac.instituto.modelo.Asistencia;
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
public class AsistenciaServicio implements Iasistencia, Serializable {
  private static final long serialVersionUID = -5971913672287412493L;
    @Override
    public Boolean insert(Asistencia asistencia, HibernateUtil util) {
        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (asistencia != null) {
                ds.save(asistencia);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;

    }

    @Override
    public Boolean update(Asistencia asistencia, HibernateUtil util) {

        Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (asistencia != null) {
                ds.update(asistencia);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;

    }

    @Override
    public Asistencia findById(Asistencia asistencia, HibernateUtil util) {

        Asistencia findmAsistencia = null;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());

            QueryParameter query_1 = new QueryParameter(QueryParameter.$TYPE_WHERE);
            query_1.setColumnName("idAsistencia");
            query_1.setWhereClause("=");
            query_1.setValue(asistencia.getIdAsistencia());

            List parameList = new ArrayList();
            parameList.add(query_1);

            List<Asistencia> listClients = ds.customQuery(parameList, Asistencia.class);

            if (!listClients.isEmpty()) {
                findmAsistencia = listClients.get(0);
            }

        } catch (Exception ex) {
            // log.level.info("findById : " + ex.getMessage());
        }

        return findmAsistencia;

    }

    @Override
    public List<Asistencia> listAsistencias(HibernateUtil util) {
        List<Asistencia> mAsistencias = null;
        try {

            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());

            List parameList = new ArrayList();

            List<Asistencia> listAsistencias = ds.customQuery(parameList, Asistencia.class);
            if (!listAsistencias.isEmpty()) {
                mAsistencias = (List<Asistencia>) listAsistencias;
            }
        } catch (Exception ex) {
            // log.level.info("getListAsistencias : " + ex.getMessage());
        }

        return mAsistencias;

    }

}
