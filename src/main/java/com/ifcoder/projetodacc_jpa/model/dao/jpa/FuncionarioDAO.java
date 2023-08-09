package com.ifcoder.projetodacc_jpa.model.dao.jpa;

import com.ifcoder.projetodacc_jpa.factory.DatabaseJPA;
import com.ifcoder.projetodacc_jpa.model.Funcionario;
import com.ifcoder.projetodacc_jpa.model.dao.IDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jose
 */
public class FuncionarioDAO implements IDao {

    private EntityManager entityManager;
    
    private Query qry;
    private String jpql;

    public FuncionarioDAO() {
        
    }

    @Override
    public void save(Object obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        
        this.entityManager.getTransaction().begin();       
        this.entityManager.persist(obj);                    
        this.entityManager.getTransaction().commit();        
        
        this.entityManager.close();
    }
    
    public void update(Object obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        
        this.entityManager.getTransaction().begin();       
        this.entityManager.merge(obj);                    
        this.entityManager.getTransaction().commit();     
        
        this.entityManager.close();
    }
    

    public boolean delete(Object obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
//        this.entityManager.getTransaction().begin();
//        this.entityManager.remove(obj);
//        this.entityManager.getTransaction().commit();                
        
        //outra forma usando JPQL. 
        Funcionario funcionario = (Funcionario) obj;
        this.entityManager.getTransaction().begin();
        qry = this.entityManager.createQuery("DELETE FROM Funcionario WHERE id=:id ");
        qry.setParameter("id", funcionario.getId());
        qry.executeUpdate(); //Obrigatorio o executeUpdate!
        this.entityManager.getTransaction().commit();
        
        this.entityManager.close();
        return true;
    }

    @Override
    public Object find(Object obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        
        Funcionario funcionario = (Funcionario) obj;
        
        //Lembrando que ao usar o find do JPA-Hibernate ele faz o cache pra gente automaticamente
        //E se estivéssemos utilizando algum tipo de relacionamento ele faria a estratégia LAZY e EAGER
        Funcionario f = this.entityManager.find(Funcionario.class, funcionario.getId());
        
        this.entityManager.close();
        
        return f;
    }

    public List<Object> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        
        jpql = " SELECT f "
             + " FROM Funcionario f ";

        qry = this.entityManager.createQuery(jpql);
        
        List lst = qry.getResultList();
        
        this.entityManager.close();
        return (List<Object>) lst;
                
    }

    public Funcionario findByEmail(String email) { 
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        
        jpql = " SELECT f "
             + " FROM Funcionario f "
             + " WHERE f.email like :email ";
        qry = this.entityManager.createQuery(jpql);
        qry.setParameter("email", email);
        
        List lst = qry.getResultList();

        this.entityManager.close();
        
        if (lst.isEmpty()) {
            return null;
        } else {
            return (Funcionario) lst.get(0);
        }                
    }    
   

}
