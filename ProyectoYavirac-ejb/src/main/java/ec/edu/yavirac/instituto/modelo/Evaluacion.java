package ec.edu.yavirac.instituto.modelo;
// Generated Sep 14, 2017 9:17:28 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Evaluacion generated by hbm2java
 */
public class Evaluacion  implements java.io.Serializable {


     private Integer idEvaluacion;
     private Actividad actividad;
     private BigDecimal calificaion;
     private Integer flag;

    public Evaluacion() {
    }

	
    public Evaluacion(Actividad actividad) {
        this.actividad = actividad;
    }
    public Evaluacion(Actividad actividad, BigDecimal calificaion, Integer flag) {
       this.actividad = actividad;
       this.calificaion = calificaion;
       this.flag = flag;
    }
   
    public Integer getIdEvaluacion() {
        return this.idEvaluacion;
    }
    
    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }
    public Actividad getActividad() {
        return this.actividad;
    }
    
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    public BigDecimal getCalificaion() {
        return this.calificaion;
    }
    
    public void setCalificaion(BigDecimal calificaion) {
        this.calificaion = calificaion;
    }
    public Integer getFlag() {
        return this.flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }




}

