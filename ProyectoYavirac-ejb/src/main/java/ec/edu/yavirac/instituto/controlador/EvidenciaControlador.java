/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;

import ec.edu.yavirac.instituto.modelo.Evidencia;
import ec.edu.yavirac.instituto.servicio.EvidenciaServicio;
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
public class EvidenciaControlador implements Serializable{
    private static final long serialVersionUID = -7260505214139170751L;
    
    @Inject
    private EvidenciaServicio evidenciaServicio; 
    
    private HibernateUtil util = new HibernateUtil();
    
    
    public Boolean insert(Evidencia evidencia) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evidencia != null) {
                exito = evidenciaServicio.insert(evidencia, util);
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


    public Boolean update(Evidencia evidencia) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evidencia != null) {
                exito = evidenciaServicio.update(evidencia, util);
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

    public Evidencia findById(Evidencia evidencia) {
        Evidencia findmEvidencia = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (evidencia != null) {
                findmEvidencia = evidenciaServicio.findById(evidencia, util);
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

    public List<Evidencia> listEvidencias() {
        List<Evidencia> mEvidencia = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mEvidencia = evidenciaServicio.listEvidencias(util);
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