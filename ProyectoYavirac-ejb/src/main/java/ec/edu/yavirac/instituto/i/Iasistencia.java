/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.i;

import ec.edu.yavirac.instituto.modelo.Asistencia;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.util.List;

/**
 *
 * @author Labs-DECC
 */
public interface Iasistencia {
    public Boolean insert(Asistencia asistencia, HibernateUtil util);

    public Boolean update(Asistencia asistencia, HibernateUtil util);

    public Asistencia findById(Asistencia asistencia, HibernateUtil util);

    public List<Asistencia> listAsistencias(HibernateUtil util);
    
}
