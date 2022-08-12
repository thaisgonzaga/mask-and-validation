// situa em qual package ou “pacote” está a classe
package com.ifcoder.projetoescola_jpa.factory;
// faz as importações de classes necessárias para o funcionamento do programa

import java.sql.Connection; // conexão SQL para Java
import java.sql.DriverManager; // driver de conexão SQL para Java
import java.sql.SQLException; // classe para tratamento de exceções

public class DatabaseSqlite {

    private Connection conexao = null;
    private static DatabaseSqlite INSTANCE = null;

    public static DatabaseSqlite getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseSqlite();
        }

        return INSTANCE;
    }

    private DatabaseSqlite() {
        this.getConnection();
    }

    public void getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection("jdbc:sqlite:dbEscola.sqlite");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error - Abrir conexão." + ex.toString());            
        } catch (SQLException ex) {            
            System.err.println("Error - Ao abrir conexão." + ex.toString());
        }        
    }
       
    /**
     * @return the conexao
     */
    public Connection getConexao() {
        return conexao;
    }
    
     public void fechar() throws SQLException {
        if (getConexao() != null) {
            getConexao().close();
        }
    }

}
