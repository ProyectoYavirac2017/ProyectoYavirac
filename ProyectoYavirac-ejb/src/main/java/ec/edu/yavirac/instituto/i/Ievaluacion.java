/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.i;

import ec.edu.yavirac.instituto.modelo.Evaluacion;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.util.List;

/**
 *
 * @author Labs-DECC
 */
public interface Ievaluacion {
    public Boolean insert(Evaluacion evaluacion ,HibernateUtil util);

    public Boolean update(Evaluacion evaluacion,HibernateUtil util);

    public Evaluacion findById (Evaluacion evaluacion,HibernateUtil util);

    public List<Evaluacion> listEvaluacion (HibernateUtil util);
}
