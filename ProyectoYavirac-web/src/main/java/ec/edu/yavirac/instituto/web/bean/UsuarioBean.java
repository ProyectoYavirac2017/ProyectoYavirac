/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.web.bean;
import ec.edu.yavirac.instituto.controlador.UsuarioControlador;
import ec.edu.yavirac.instituto.modelo.Usuario;
import ec.edu.yavirac.instituto.web.bean.i.Imethods;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Labs-DECC
 */
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Imethods, Serializable {
    // private static final long serialVersionUID = -2033941921967400870L;

    private Usuario usuario;
    @Inject
    private UsuarioControlador usuarioControl;

    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
    }

    @Override
    public void add(ActionEvent evt) {
        if (this.usuario != null) {
            Boolean exito = this.usuarioControl.insert(this.usuario);
            if (exito) {
                this.init();
            }
        }
    }

    @Override
    public void modify(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
