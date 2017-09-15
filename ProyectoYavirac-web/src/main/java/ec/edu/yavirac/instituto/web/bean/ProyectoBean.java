/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.web.bean;

import ec.edu.yavirac.instituto.controlador.ProyectoControlador;
import ec.edu.yavirac.instituto.modelo.Proyecto;
import ec.edu.yavirac.instituto.web.bean.i.Imethods;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Erick viracocha, y sus amigos :V
 */
@Named(value = "proyectoBean")
@ViewScoped
public class ProyectoBean implements Imethods, Serializable {
    
    private Date date3;
   
    
    private Proyecto proyecto;
    @Inject
    private ProyectoControlador proyectoControl;

    @PostConstruct
    public void init() {
      this.proyecto= new Proyecto();
    }

    @Override
    public void add(ActionEvent evt) {
        if (this.proyecto != null) {
           Boolean exito = this.proyectoControl.insert(this.proyecto);
           if(exito){
               this.init();
           }
        }
    }
    //Código para calendario
     public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
    //Código para calendario
    @Override
    public void modify(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    
     public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }
    /**
     * Creates a new instance of ProyectoBean
     */
    
    
}
