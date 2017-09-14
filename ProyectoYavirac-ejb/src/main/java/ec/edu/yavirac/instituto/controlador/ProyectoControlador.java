/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;

import ec.edu.yavirac.instituto.i.Iproyecto;
import ec.edu.yavirac.instituto.modelo.Proyecto;
import ec.edu.yavirac.instituto.servicio.ProyectoServicio;
import ec.edu.yavirac.instituto.util.HibernateSessionHandler;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Labs-DECC
 */
@Stateless
@LocalBean
public class ProyectoControlador implements Serializable {

   private static final long serialVersionUID = -7260505214139170751L;
    @Inject
    private ProyectoServicio proyectoServicio; //no instancio el obj. solo si da errores instancio
    private HibernateUtil util = new HibernateUtil();
    
    
//    @PostConstruct
//    public void init(){
//        util.init();
//    }
    
    public Boolean insert(Proyecto proyecto) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (proyecto != null) {
                //proyecto.setCliIdcli("C"+RandomStringUtils.randomNumeric(4));
                exito = proyectoServicio.insert(proyecto,util);
            }
        } catch (Exception ex) {
            // // log.level.error("insert : " + ex.getMessage());
            delegateException = ex;
        } finally {
            hss.close();
            if (delegateException != null) {
                try {
                    throw delegateException;
                } catch (Exception ex) {
                    // // log.level.info("delageException insert " + ex.toString());
                }
            }
        }
        return exito;
    }

    public Boolean update(Proyecto proyecto) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (proyecto != null) {
                exito = proyectoServicio.update(proyecto,util);
            }
        } catch (Exception ex) {
            // // log.level.error("insert : " + ex.getMessage());
            delegateException = ex;
        } finally {
            hss.close();
            if (delegateException != null) {
                try {
                    throw delegateException;
                } catch (Exception ex) {
                    // // log.level.info("delageException insert " + ex.toString());
                }
            }
        }
        return exito;
    }

    public Proyecto findById(Proyecto proyecto) {
        Proyecto findmProyecto = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (proyecto != null) {
                findmProyecto = proyectoServicio.findById(proyecto,util);
            }
        } catch (Exception ex) {
            // log.level.error("FindUserLogin : " + ex.getMessage());
            delegateException = ex;
        } finally {
            hss.close();
            if (delegateException != null) {
                try {
                    throw delegateException;
                } catch (Exception ex) {
                    // log.level.info("delageException " + ex.toString());
                }
            }
        }
        return findmProyecto;
    }

    public List<Proyecto> listProyectos() {
        List<Proyecto> mProyectos = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mProyectos = proyectoServicio.listProyectos(util);
        } catch (Exception ex) {
            // log.level.error("GetListProyectos : " + ex.getMessage());
            delegateException = ex;
        } finally {
            hss.close();
            if (delegateException != null) {
                try {
                    throw delegateException;
                } catch (Exception ex) {
                    // log.level.info("delageException " + ex.toString());
                }
            }
        }
        return mProyectos;
    }
  
    
}
