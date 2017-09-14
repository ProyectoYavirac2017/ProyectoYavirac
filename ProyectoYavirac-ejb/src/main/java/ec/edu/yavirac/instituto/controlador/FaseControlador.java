/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;

import ec.edu.yavirac.instituto.i.Ifase;
import ec.edu.yavirac.instituto.modelo.Fase;
import ec.edu.yavirac.instituto.servicio.FaseServicio;
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
public class FaseControlador implements Ifase, Serializable {

    private static final long serialVersionUID = -3073928298785803508L;
    @Inject
    private FaseServicio faseServicio; //no instancio el obj. solo si da errores instancio
    private HibernateUtil util = new HibernateUtil();

    @PostConstruct
    public void init() {
        util.init();
    }

    @Override
    public Boolean insert(Fase fase, HibernateUtil util) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (fase != null) {
                //fase.setCliIdcli("C"+RandomStringUtils.randomNumeric(4));
                exito = faseServicio.insert(fase, util);
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
    public Boolean update(Fase fase, HibernateUtil util) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (fase != null) {
                exito = faseServicio.update(fase, util);
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
    public Fase findById(Fase fase, HibernateUtil util) {
        Fase findmFase = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (fase != null) {
                findmFase = faseServicio.findById(fase, util);
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
        return findmFase;
    }

    @Override
    public List<Fase> listFases(HibernateUtil util) {
        List<Fase> mFases = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mFases = faseServicio.listFases(util);
        } catch (Exception ex) {
            // log.level.error("GetListFases : " + ex.getMessage());
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
        return mFases;
    }
}
