/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.i;

import ec.edu.yavirac.instituto.modelo.Fase;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.util.List;

/**
 *
 * @author Labs-DECC
 */
public interface Ifase {
  

    public Boolean insert(Fase fase, HibernateUtil util);

    public Boolean update(Fase fase, HibernateUtil util);

    public Fase findById(Fase fase, HibernateUtil util);

    public List<Fase> listFases(HibernateUtil util);
}

