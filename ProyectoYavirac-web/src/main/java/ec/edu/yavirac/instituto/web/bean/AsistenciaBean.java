/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.web.bean;

import ec.edu.yavirac.instituto.controlador.AsistenciaControlador;
import ec.edu.yavirac.instituto.modelo.Asistencia;
import ec.edu.yavirac.instituto.web.bean.i.Imethods;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Labs-DECC
 */
@Named(value = "asistenciaBean")
@ViewScoped
public class AsistenciaBean implements Imethods, Serializable {

    private static final long serialVersionUID = -2033941921967400870L;

    private Asistencia asistencia;
    @Inject
    private AsistenciaControlador asistenciaControl;

    @PostConstruct
    public void init() {
        this.asistencia = new Asistencia();
    }

    @Override
    public void add(ActionEvent evt) {
        if (this.asistencia != null) {
           Boolean exito = this.asistenciaControl.insert(asistencia);
           if(exito){
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

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

}

    

