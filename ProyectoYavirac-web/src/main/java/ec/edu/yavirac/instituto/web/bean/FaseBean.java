/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.web.bean;

import ec.edu.yavirac.instituto.controlador.FaseControlador;
import ec.edu.yavirac.instituto.modelo.Fase;
import ec.edu.yavirac.instituto.web.bean.i.Imethods;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Labs-DECC
 */
@Named(value = "faseBean")
@ViewScoped
public class FaseBean implements Imethods, Serializable{
    
    private static final long serialVersionUID = -1285664479708365060L;

    private Fase fase;
    @Inject
    private FaseControlador faseControl;

    @PostConstruct
    public void init() {
      this.fase= new Fase();
    }
    @Override
    public void add(ActionEvent evt) {
        
        if (this.fase != null) {
           Boolean exito = this.faseControl.insert(this.fase);
           if(exito){
               this.init();
           }
        }
    }

    @Override
    public void modify(ActionEvent evt) {
        
    }

    @Override
    public void remove(ActionEvent evt) {
        
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
    
}
