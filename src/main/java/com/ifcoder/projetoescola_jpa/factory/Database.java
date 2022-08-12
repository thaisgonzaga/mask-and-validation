
package com.ifcoder.projetoescola_jpa.factory;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

    private EntityManager entityManager;

    private static Database INSTANCE = null;

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }

        return INSTANCE;
    }

    /**
     * Construtor da classe. 
     * - No padrão SINGLETON ele deve ser PRIVADO
     * - Só pode ser chamado pelo método getInstance()
     * - O método getInstance() gerencia a regra SINGLETON, que permite apenas 
     * uma instancia do objeto Database
     */
    private Database() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("exemplo-jpa");
        this.entityManager = factory.createEntityManager();
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}
