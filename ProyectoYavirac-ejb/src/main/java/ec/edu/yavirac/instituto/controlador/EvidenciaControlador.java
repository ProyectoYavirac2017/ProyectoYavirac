/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;

import ec.edu.yavirac.instituto.i.Ievidencia;
import ec.edu.yavirac.instituto.modelo.Evidencia;
import ec.edu.yavirac.instituto.util.HibernateSessionHandler;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Labs-DECC
 */
public class EvidenciaControlador  implements Ievidencia,Serializable{
      private static final long serialVersionUID = -7260505214139170751L;
    @Inject
    private EvidenciaControlador evidenciaControlador; 
    private HibernateUtil util = new HibernateUtil();
    
    @PostConstruct
    public void init() {
        util.init();
    }

    @Override
    public Boolean insert(Evidencia evidencia, HibernateUtil util) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evidencia != null) {
                exito = evidenciaControlador.insert(evidencia, util);
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
    public Boolean update(Evidencia evidencia, HibernateUtil util) {
         Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evidencia != null) {
                exito = evidenciaControlador.update(evidencia, util);
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
    public Evidencia findById(Evidencia evidencia, HibernateUtil util) {
        Evidencia findmEvidencia = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evidencia != null) {
                findmEvidencia = evidenciaControlador.findById(evidencia, util);
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
        return findmEvidencia;
    }

    @Override
    public List<Evidencia> listEvidencias(HibernateUtil util) {
         List<Evidencia> mEvidencia = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mEvidencia = evidenciaControlador.listEvidencias(util);
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
        return mEvidencia;
    }
    
    
}
