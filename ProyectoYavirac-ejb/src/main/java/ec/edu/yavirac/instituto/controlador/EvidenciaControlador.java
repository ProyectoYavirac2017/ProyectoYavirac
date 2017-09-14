/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;

import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.Ievidencia;
import ec.edu.yavirac.instituto.modelo.Evidencia;
import ec.edu.yavirac.instituto.util.HibernateSessionHandler;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Labs-DECC
 */
public class EvidenciaControlador implements Ievidencia ,Serializable {

    private EvidenciaControlador evidenciaControlador = new EvidenciaControlador();
    private HibernateUtil util = new HibernateUtil();

    @PostConstruct
    public void init() {
        util.init();
    }

   public Boolean insert(Evidencia evidencia ,HibernateUtil util) {
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

   public Boolean update(Evidencia evidencia,HibernateUtil util) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evidencia != null) {
                exito = evidenciaControlador.insert(evidencia, util);
            }
        } catch (Exception ex) {
            // log.level.error("insert : " + ex.getMessage());
            delegateException = ex;
        } finally {
            hss.close();
            if (delegateException != null) {
                try {
                    throw delegateException;
                } catch (Exception ex) {
                    // log.level.info("delageException insert " + ex.toString());
                }
            }
        }
        return exito;
    }

    public Evidencia findByCode(Evidencia evidencia,HibernateUtil util) {
        Evidencia findmEvidencia = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evidencia != null) {
                findmEvidencia = evidenciaControlador.findByCode(evidencia, util);
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

     public List<Evidencia> findByFacNum(HibernateUtil util){
           List<Evidencia> mAsistencia = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mAsistencia = evidenciaControlador.findByFacNum(util);
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
        return mAsistencia;
    }

}
