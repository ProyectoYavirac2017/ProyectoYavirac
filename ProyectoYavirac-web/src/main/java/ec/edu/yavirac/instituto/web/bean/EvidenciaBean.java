/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.web.bean;

import ec.edu.yavirac.instituto.controlador.EvidenciaControlador;
import ec.edu.yavirac.instituto.modelo.Evidencia;
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
@Named(value = "evidenciaBean")
@ViewScoped
public class EvidenciaBean implements Imethods, Serializable{
    private static final long serialVersionUID = -2033941921967400870L;

    private Evidencia evidencia;
    @Inject
    private EvidenciaControlador evidenciaControl;

    @PostConstruct
    public void init() {
        this.evidencia = new Evidencia();
    }

        

    @Override
    public void add(ActionEvent evt) {
        if (this.evidencia != null) {
           Boolean exito = this.evidenciaControl.insert(evidencia);
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
    
     public Evidencia getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Evidencia evidencia) {
        this.evidencia = evidencia;
    }
}
