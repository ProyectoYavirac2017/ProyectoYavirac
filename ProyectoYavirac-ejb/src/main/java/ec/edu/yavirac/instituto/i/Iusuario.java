/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.i;

import ec.edu.yavirac.instituto.modelo.Usuario;
import ec.edu.yavirac.instituto.util.HibernateUtil;
import java.util.List;

/**
 *
 * @author Labs-DECC
 */
public interface Iusuario {
  public Boolean insert(Iusuario usuario, HibernateUtil util);

    public Boolean update(Iusuario usuario, HibernateUtil util);

    public Usuario findById(Iusuario usuario, HibernateUtil util);

    public List<Usuario> listUsuario(HibernateUtil util);  
}
