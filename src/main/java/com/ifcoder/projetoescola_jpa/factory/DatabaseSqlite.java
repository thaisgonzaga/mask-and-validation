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
            System.err.println("Conexao criada.");
        }else{
            System.err.println("Conexao ja existe apenas retornei.");
        }

        return INSTANCE;
    }

    private DatabaseSqlite() {
        try {        
            Class.forName("org.sqlite.JDBC");
            this.conexao = DriverManager.getConnection("jdbc:sqlite:dbEscola.sqlite");
        
        } catch (ClassNotFoundException ex) {
            System.err.println("Error - Abrir conexão." + ex.toString());            
        } catch (SQLException ex) {            
            System.err.println("Error - Ao abrir conexão." + ex.toString());
        }  
    }    
       
    public static Connection conectar(){
        return DatabaseSqlite.getInstance().getConexao();
    }
      
    
    public Connection getConexao() {
        return this.conexao;
    }
    
    


}
