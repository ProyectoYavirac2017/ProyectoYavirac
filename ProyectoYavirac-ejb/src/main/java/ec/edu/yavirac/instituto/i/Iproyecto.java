/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.i;

import ec.edu.yavirac.instituto.modelo.Proyecto;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.util.List;

/**
 *
 * @author Grupo proyecto
 */
public interface Iproyecto {
    
    public Boolean insert(Proyecto proyecto,HibernateUtil util);

    public Boolean update(Proyecto proyecto,HibernateUtil util);

    public Proyecto findById(Proyecto proyecto,HibernateUtil util);

    public List<Proyecto> listProyectos(HibernateUtil util);
}
