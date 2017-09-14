/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.util;

import ec.edu.yavirac.instituto.logger.L;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author luis
 */
public class HibernateUtil {

    private final static L log = new L(HibernateUtil.class);
    private SessionFactory sessionFactory;
    private ServiceRegistry sessionServiceRegistry;

    public HibernateUtil() {
        try {

            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            StandardServiceRegistry standardRegistry
                    = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();
            sessionServiceRegistry = standardRegistry;
            sessionFactory = configuration.buildSessionFactory(sessionServiceRegistry);
        } catch (Throwable ex) {
            log.level.info("Initial SessionFactory creation failed." + ex);
            System.out.println(">> error :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void init() {
        if (sessionFactory == null) {
            throw new NullPointerException("No se creo la conexion.");
        } else {
            log.level.info("Se ha iniciado la sesion de Hiberate");
        }
    }
}
