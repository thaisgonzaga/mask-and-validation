package com.ifcoder.projetoescola_jpa.model.dao.jpa;

import com.ifcoder.projetoescola_jpa.factory.Database;
import com.ifcoder.projetoescola_jpa.model.Funcionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jose
 */
public class FuncionarioDAO {

    EntityManager entityManager;
    
    Query qry;
    String sql;

    public FuncionarioDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public void save(Funcionario funcionario) {
        this.entityManager.getTransaction().begin();
        if (funcionario != null && funcionario.getId() > 0) {
            this.entityManager.merge(funcionario);
        } else {
            this.entityManager.persist(funcionario);
        }
        this.entityManager.getTransaction().commit();
    }

    public void delete(Funcionario funcionario) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(funcionario);
        this.entityManager.getTransaction().commit();
    }

    public Funcionario find(int id) {
        //Está é um HQL (Hibernate Query Language)
        sql = " SELECT f "
                + " FROM Funcionario "
                + " WHERE id = :id ";

        qry = this.entityManager.createQuery(sql);
        qry.setParameter("id", id);
        
        List lst = qry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return (Funcionario) lst.get(0);
        }
    }

    public List<Funcionario> findAll() {
        //Está é um HQL (Hibernate Query Language)
        sql = " SELECT f "
                + " FROM Funcionario f ";

        qry = this.entityManager.createQuery(sql);
        
        List lst = qry.getResultList();
        return (List<Funcionario>) lst;
    }

    public Funcionario findByEmail(String email) {
        //Está é um HQL (Hibernate Query Language)
        sql = " SELECT f "
                + " FROM Funcionario "
                + " WHERE email like :email ";
        qry = this.entityManager.createQuery(sql);
        qry.setParameter("email", email);
        
        List lst = qry.getResultList();

        if (lst.isEmpty()) {
            return null;
        } else {
            return (Funcionario) lst.get(0);
        }
    }

}
