/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.servicio;
import ec.edu.yavirac.instituto.dao.DAOServices;
import ec.edu.yavirac.instituto.dao.QueryParameter;
import ec.edu.yavirac.instituto.i.Iusuario;
import ec.edu.yavirac.instituto.modelo.Usuario;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
/**
 *
 * @author Labs-DECC
 */
@Stateless
@LocalBean
public class UsuarioServicio implements Iusuario, Serializable {
    private static final long serialVersionUID = -5971913672287412493L;
    
    @Override
    public Boolean insert(Usuario usuario, HibernateUtil util) {
          Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (usuario != null) {
                ds.save(usuario);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Boolean update(Usuario usuario, HibernateUtil util) {
         Boolean exito = false;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            if (usuario != null) {
                ds.update(usuario);
                exito = true;
            }
        } catch (Exception e) {
            System.out.println(">> ERROR " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public Usuario findById(Usuario usuario, HibernateUtil util) {
        Usuario findmUsuario = null;
        try {
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());

            QueryParameter query_1 = new QueryParameter(QueryParameter.$TYPE_WHERE);
            query_1.setColumnName("idUsuario");//idUsuario
            query_1.setWhereClause("=");
            query_1.setValue(usuario.getIdUsuario());

            List parameList = new ArrayList();
            parameList.add(query_1);

            List<Usuario> listClients = ds.customQuery(parameList, Usuario.class);

            if (!listClients.isEmpty()) {
                findmUsuario = listClients.get(0);
            }

        } catch (Exception ex) {
            // log.level.info("findById : " + ex.getMessage());
        }

        return findmUsuario;
    }

    @Override
    public List<Usuario> listUsuario(HibernateUtil util) {
       List<Usuario> mUsuarios = null;
        try {
            
            DAOServices ds = new DAOServices(util.
                    getSessionFactory().getCurrentSession());
            
            List parameList = new ArrayList();
            
            List<Usuario> listUsuarios = ds.customQuery(parameList, Usuario.class);
            if (!listUsuarios.isEmpty()) {
                mUsuarios = (List<Usuario>) listUsuarios;
            }
        } catch (Exception ex) {
            // log.level.info("getListUsuarios : " + ex.getMessage());
        }

        return mUsuarios;
    }
}
