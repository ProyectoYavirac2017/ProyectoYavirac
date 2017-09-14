/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.web.bean;

import ec.edu.yavirac.instituto.modelo.Actividad;
import ec.edu.yavirac.instituto.controlador.ActividadControlador;
import ec.edu.yavirac.instituto.controlador.FaseControlador;
import ec.edu.yavirac.instituto.controlador.ProyectoControlador;
import ec.edu.yavirac.instituto.modelo.Fase;
import ec.edu.yavirac.instituto.modelo.Proyecto;
import ec.edu.yavirac.instituto.web.bean.i.Imethods;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Labs-DECC
 */
@Named(value = "actividadBean")
@ViewScoped
public class ActividadBean implements Imethods, Serializable {

    private static final long serialVersionUID = -2033941921967400870L;

    private Actividad actividad;
    private List<Proyecto> proyectos;
    private Proyecto selectProy;
    private List<Fase> fases;
    private Fase slectFase;
    private List<Actividad> atividades;
            
    @Inject
    private ActividadControlador actividadControl;
    @Inject
    private ProyectoControlador proyectoControl;
    @Inject
    private FaseControlador faseControlador;

    @PostConstruct
    public void init() {
        this.actividad = new Actividad();
        this.proyectos = this.proyectoControl.listProyectos();
        if(this.proyectos == null){
            this.proyectos = new ArrayList<>();
        }
    }
    

    @Override
    public void add(ActionEvent evt) {
        if (this.actividad != null) {
            Boolean exito = this.actividadControl.insert(this.actividad);
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

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

}
