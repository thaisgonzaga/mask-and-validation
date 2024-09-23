
package com.ifcoder.projetodacc_lps.model.dao;

import java.util.List;

/**
 *
 * @author jose
 */
public interface IDao<T> {
    
    public void save(T obj);
    
    public void update(T obj);
    
    public boolean delete(T obj);
            
    public T find(T obj);
        
    public List<T> findAll();
}
