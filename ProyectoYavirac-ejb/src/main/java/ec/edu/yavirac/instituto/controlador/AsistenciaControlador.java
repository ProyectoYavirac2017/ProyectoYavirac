/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;

import ec.edu.yavirac.instituto.modelo.Asistencia;
import ec.edu.yavirac.instituto.servicio.AsistenciaServicio;
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
public class AsistenciaControlador implements Serializable{
    private static final long serialVersionUID = -7260505214139170751L;
    @Inject
    private AsistenciaServicio asistenciaServicio; 
    private HibernateUtil util = new HibernateUtil();
    
    @PostConstruct
    public void init() {
        util.init();
    }
    public Boolean insert(Asistencia asistencia) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (asistencia != null) {
                // cliente.setCliIdcli("C"+RandomStringUtils.randomNumeric(4));
                //cliente.setCliIdcli("C" +RandomStringUtils.randomNumeric(5));
                exito = asistenciaServicio.insert(asistencia, util);
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


    public Boolean update(Asistencia asistencia) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (asistencia != null) {
                exito = asistenciaServicio.update(asistencia, util);
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

    public Asistencia findById(Asistencia asistencia) {
        Asistencia findmAsistencia = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (asistencia != null) {
                findmAsistencia = asistenciaServicio.findById(asistencia, util);
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
        return findmAsistencia;
    }

    public List<Asistencia> listAsistencias() {
        List<Asistencia> mAsistencia = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mAsistencia = asistenciaServicio.listAsistencias(util);
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


