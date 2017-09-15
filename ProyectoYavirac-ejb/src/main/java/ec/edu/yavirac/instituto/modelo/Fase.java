package ec.edu.yavirac.instituto.modelo;
// Generated Sep 14, 2017 9:17:28 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Fase  implements java.io.Serializable {


     private Integer idFase;
     private Proyecto proyecto;
     private Integer numero;
     private Date fechaInit;
     private String objetivo;
     private String estado;
     private String flag;
     private Set actividads = new HashSet(0);

    public Fase() {
    }

	
    public Fase(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    public Fase(Proyecto proyecto, Integer numero, Date fechaInit, String objetivo, String estado, String flag, Set actividads) {
       this.proyecto = proyecto;
       this.numero = numero;
       this.fechaInit = fechaInit;
       this.objetivo = objetivo;
       this.estado = estado;
       this.flag = flag;
       this.actividads = actividads;
    }
   
    public Integer getIdFase() {
        return this.idFase;
    }
    
    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }
    public Proyecto getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    public Integer getNumero() {
        return this.numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public Date getFechaInit() {
        return this.fechaInit;
    }
    
    public void setFechaInit(Date fechaInit) {
        this.fechaInit = fechaInit;
    }
    public String getObjetivo() {
        return this.objetivo;
    }
    
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public Set getActividads() {
        return this.actividads;
    }
    
    public void setActividads(Set actividads) {
        this.actividads = actividads;
    }
}


