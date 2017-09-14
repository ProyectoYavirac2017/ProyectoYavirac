/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.controlador;


import ec.edu.yavirac.instituto.i.Ievidencia;
import ec.edu.yavirac.instituto.modelo.Evidencia;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Labs-DECC
 */
public class EvidenciaControlador implements Ievidencia ,Serializable {

    private EvidenciaControlador evidenciaControlador = new EvidenciaControlador();
    private HibernateUtil util = new HibernateUtil();

    @PostConstruct
    public void init() {
        util.init();
    }

    @Override
    public Boolean insert(Evidencia evidencia, HibernateUtil util) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean update(Evidencia evidencia, HibernateUtil util) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Evidencia findById(Evidencia evidencia, HibernateUtil util) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evidencia> listEvidencias(HibernateUtil util) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
