/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;


import ec.edu.yavirac.instituto.modelo.Evaluacion;
import ec.edu.yavirac.instituto.servicio.EvaluacionServicio;
import ec.edu.yavirac.instituto.util.HibernateSessionHandler;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Labs-DECC
 */
@Stateless
@LocalBean
public class EvaluacionControlador implements Serializable{
    private static final long serialVersionUID = -7260505214139170751L;
    @Inject
    private EvaluacionServicio evaluacionServicio; 
    private HibernateUtil util = new HibernateUtil();
    
    
    public Boolean insert(Evaluacion evaluacion) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evaluacion != null) {
                exito = evaluacionServicio.insert(evaluacion, util);
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


    public Boolean update(Evaluacion evaluacion) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evaluacion != null) {
                exito = evaluacionServicio.update(evaluacion, util);
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

    public Evaluacion findById(Evaluacion evaluacion) {
        Evaluacion findmEvaluacion = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evaluacion != null) {
                findmEvaluacion = evaluacionServicio.findById(evaluacion, util);
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
        return findmEvaluacion;
    }

    public List<Evaluacion> listEvaluacions() {
        List<Evaluacion> mEvaluacion = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mEvaluacion = evaluacionServicio.listEvaluaciones(util);
        } catch (Exception ex) {
            // log.level.error("GetListClientes : " + ex.getMessage());
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
        return mEvaluacion;
    }
}