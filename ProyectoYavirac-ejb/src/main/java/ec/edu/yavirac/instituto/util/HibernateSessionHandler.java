package ec.edu.yavirac.instituto.util;

import org.hibernate.SessionFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class HibernateSessionHandler {

    private SessionFactory dbServicesSession;
   // private final static L log = new L(HibernateSessionHandler.class);

    public HibernateSessionHandler(HibernateUtil util) {

        dbServicesSession = util.getSessionFactory();

        try {
            dbServicesSession.getCurrentSession().beginTransaction();

        } catch (Exception e) {
            //log.level.error("Error en la sesion de Hibernate", e);
        }
    }

    public void close() {
        try {
            dbServicesSession.getCurrentSession().getTransaction().commit();
        } catch (Exception e) {
            if (dbServicesSession.getCurrentSession().getTransaction().isActive()) {
             //   log.level.error("Trying to rollback database transaction after exception. Módulo RapidLoans_Users.", e);
                dbServicesSession.getCurrentSession().getTransaction().rollback();
            }
           // log.level.error("Error al cerrar la sesion de Hibernate", e);
        } finally {
            dbServicesSession.getCurrentSession().close();
        }

    }
}
