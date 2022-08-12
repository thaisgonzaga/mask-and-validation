
package com.ifcoder.projetoescola_jpa.model.dao;

import java.util.List;

/**
 *
 * @author jose
 */
public interface IDao {
    
    public void save(Object obj);
    
    public boolean delete(Object obj);
            
    public Object find(Object obj);
        
    public List<Object> findAll();
}
