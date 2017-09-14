/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;

import ec.edu.yavirac.instituto.dao.DAOServices;
import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.Ievaluacion;
import ec.edu.yavirac.instituto.modelo.Evaluacion;
import ec.edu.yavirac.instituto.servicio.EvaluacionServicio;
import ec.edu.yavirac.instituto.util.HibernateSessionHandler;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
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
public class EvaluacionControlador implements Ievaluacion,Serializable {
    private static final long serialVersionUID = -7260505214139170751L;
    @Inject
    private EvaluacionServicio evaluacionServicio; 
    private HibernateUtil util = new HibernateUtil();
    
    @PostConstruct
    public void init() {
        util.init();
    }

    @Override
    public Boolean insert(Evaluacion evaluacion, HibernateUtil util) {
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

    @Override
    public Boolean update(Evaluacion evaluacion, HibernateUtil util) {
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

    @Override
    public Evaluacion findById(Evaluacion evaluacion, HibernateUtil util) {
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

    @Override
    public List<Evaluacion> listEvaluacion(HibernateUtil util) {
          List<Evaluacion> mEvaluacion = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mEvaluacion = evaluacionServicio.listEvaluacion(util);
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
