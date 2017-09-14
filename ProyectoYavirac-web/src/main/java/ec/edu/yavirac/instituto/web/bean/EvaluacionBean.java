/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.web.bean;

import ec.edu.yavirac.instituto.controlador.EvaluacionControlador;
import ec.edu.yavirac.instituto.modelo.Evaluacion;
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
@Named(value = "evaluacionBean")
@ViewScoped
public class EvaluacionBean implements Imethods, Serializable {

    private static final long serialVersionUID = -2033941921967400870L;

    private Evaluacion evaluacion;
    @Inject
    private EvaluacionControlador evaluacionControl;

    @PostConstruct
    public void init() {
        this.evaluacion = new Evaluacion();
    }

    @Override
    public void add(ActionEvent evt) {
        if (this.evaluacion != null) {
           Boolean exito = this.evaluacionControl.insert(evaluacion);
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

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
    
    }


