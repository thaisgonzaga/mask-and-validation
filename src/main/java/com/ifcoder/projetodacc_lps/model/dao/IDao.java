
package com.ifcoder.projetodacc_lps.model.dao;

import java.util.List;

/**
 *
 * @author jose
 */
public interface IDao {
    
    public void save(Object obj);
    
    public void update(Object obj);
    
    public boolean delete(Object obj);
            
    public Object find(Object obj);
        
    public List<Object> findAll();
}
