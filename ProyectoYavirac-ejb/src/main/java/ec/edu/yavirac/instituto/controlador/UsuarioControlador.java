/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;
import ec.edu.yavirac.instituto.modelo.Usuario;
import ec.edu.yavirac.instituto.servicio.UsuarioServicio;
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
public class UsuarioControlador implements Serializable {
    private static final long serialVersionUID = -7260505214139170751L;
    @Inject
    private UsuarioServicio usuarioServicio; //no instancio el obj. solo si da errores instancio
    private HibernateUtil util = new HibernateUtil();
    
    @PostConstruct
    public void init(){
        util.init();
    }
    
    public Boolean insert(Usuario usuario) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (usuario != null) {
                //usuario.setCliIdcli("C"+RandomStringUtils.randomNumeric(4));
                exito = usuarioServicio.insert(usuario,util);
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

    public Boolean update(Usuario usuario) {
        Boolean exito = false;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (usuario != null) {
                exito = usuarioServicio.update(usuario,util);
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

    public Usuario findById(Usuario usuario) {
        Usuario findmUsuario = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            if (usuario != null) {
                findmUsuario = usuarioServicio.findById(usuario,util);
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
        return findmUsuario;
    }

    public List<Usuario> listUsuarios() {
        List<Usuario> mUsuarios = null;
        HibernateSessionHandler hss = new HibernateSessionHandler(util);
        Exception delegateException = null;
        try {
            mUsuarios = usuarioServicio.listUsuario(util);
        } catch (Exception ex) {
            // log.level.error("GetListUsuarios : " + ex.getMessage());
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
        return mUsuarios;
    }
    
}
