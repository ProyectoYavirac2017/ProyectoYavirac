package ec.edu.yavirac.instituto.modelo;
// Generated Sep 14, 2017 9:17:28 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Asistencia generated by hbm2java
 */
public class Asistencia  implements java.io.Serializable {


     private Integer idAsistencia;
     private Usuario usuario;
     private Date fecha;
     private Boolean presente;
     private Integer flag;

    public Asistencia() {
    }

	
    public Asistencia(Usuario usuario) {
        this.usuario = usuario;
    }
    public Asistencia(Usuario usuario, Date fecha, Boolean presente, Integer flag) {
       this.usuario = usuario;
       this.fecha = fecha;
       this.presente = presente;
       this.flag = flag;
    }
   
    public Integer getIdAsistencia() {
        return this.idAsistencia;
    }
    
    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Boolean getPresente() {
        return this.presente;
    }
    
    public void setPresente(Boolean presente) {
        this.presente = presente;
    }
    public Integer getFlag() {
        return this.flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }




}

