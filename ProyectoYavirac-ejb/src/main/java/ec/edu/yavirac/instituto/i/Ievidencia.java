/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.i;

import ec.edu.yavirac.instituto.modelo.Evidencia;
import java.util.List;

/**
 *
 * @author Labs-DECC
 */
public interface Ievidencia {
    public Boolean insert(Evidencia evidencia);

    public Boolean update(Evidencia evidencia);

    public Evidencia findByCode(Evidencia evidencia);

    public List<Evidencia> findByFacNum(Evidencia evidencia);
    
}
