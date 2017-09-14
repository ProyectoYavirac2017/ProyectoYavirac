
package ec.edu.yavirac.instituto.i;

import ec.edu.yavirac.instituto.modelo.Actividad;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.util.List;

public interface iActividad {
    public Boolean insert(Actividad actividad, HibernateUtil util);

    public Boolean update(Actividad actividad, HibernateUtil util);

    public Actividad findById(Actividad actividad, HibernateUtil util);

    public List<Actividad> listActividads(HibernateUtil util);
    
    
}
