/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetoescola_jpa.model.dao.file;

import com.ifcoder.projetoescola_jpa.factory.DatabaseSqlite;
import com.ifcoder.projetoescola_jpa.model.Aluno;
import java.util.ArrayList;
import java.util.List;
import com.ifcoder.projetoescola_jpa.model.dao.IDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jose
 */
public class AlunoDAO implements IDao{
    
    //private List<Object> lst; //só é usado quando salvamos no arquivo texto
    protected Connection connection;   

    public AlunoDAO() {
        this.connection = DatabaseSqlite.getInstance().getConexao();        
    }            
    
    @Override
    public void save(Object obj){     
        Aluno aluno = (Aluno) obj;
        
        String sql = "INSERT INTO "
                + " aluno(nome, sexo, idade, matricula, anoIngresso) "
                + " VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            //preencher cada ? com o campo adequado
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSexo()+"");
            stmt.setInt(3, aluno.getIdade());
            stmt.setString(4, aluno.getMatricula());
            stmt.setInt(5, aluno.getAnoIngresso());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }      
    }            
    
    public void update(Object obj) {
        Aluno aluno = (Aluno) obj;

        String sql = " UPDATE aluno "
                + " SET nome=?, sexo=?, idade=?, matricula=?, anoIngresso=? "
                + " WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            //preencher cada ? com o campo adequado
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSexo()+"");
            stmt.setInt(3, aluno.getIdade());
            stmt.setString(4, aluno.getMatricula());
            stmt.setInt(5, aluno.getAnoIngresso());
            
            //preenche a condição do WHERE
            stmt.setInt(6, aluno.getId());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }     
    }
    
    @Override
    public List<Object> findAll() {
        List<Object> list = new ArrayList<>();

        String sql = "SELECT * FROM aluno ORDER BY upper(nome)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultset = stmt.executeQuery();
            while (resultset.next()) {
                Aluno aluno = new Aluno(
                        resultset.getInt(1),
                        resultset.getString(2),
                        resultset.getString(3).charAt(0),
                        resultset.getInt(4),
                        resultset.getString(5),
                        resultset.getInt(6));

                list.add(aluno);
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return list;
    }
    
    
    @Override
    public Object find(Object obj) {
        Aluno aluno = (Aluno) obj;
        
        String sql = "SELECT * FROM aluno WHERE id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getId());
            
            ResultSet resultset = stmt.executeQuery();
            
            Aluno a = null;
            while (resultset.next()) {
                a = new Aluno(
                        resultset.getInt(1),
                        resultset.getString(2),
                        resultset.getString(3).charAt(0),
                        resultset.getInt(4),
                        resultset.getString(5),
                        resultset.getInt(6));               
            }
            stmt.close();
            return a;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        
    }
    
    /**
     * Procura um professor pelo CPF, que é o identificador único
     * @param matricula do aluno
     * @return Referencia para o aluno na lstAluno
     */
    public Object findByMatricula(String matricula) {
        String sql = "Select * FROM aluno as a WHERE a.matricula = ? ";

        Aluno aluno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //preenche a condição
            stmt.setString(1, matricula);
            
            ResultSet resultset = stmt.executeQuery();
            while (resultset.next()) {
                aluno = new Aluno(
                        resultset.getInt(1),
                        resultset.getString(2),
                        resultset.getString(3).charAt(0),
                        resultset.getInt(4),
                        resultset.getString(5),
                        resultset.getInt(6));
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return aluno;
    }
    
          
    
    /**
     * Recebe um Aluno como parametro, procura o Aluno pela Matricula
     * Se encontrar remove ele da lstAlunos.
     * @param obj
     * @param Aluno
     * @return 
     */
    @Override
    public boolean delete(Object obj) {
        Aluno aluno = (Aluno) obj;
        
        String sql = "DELETE FROM aluno WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //preenche a condição
            stmt.setLong(1, aluno.getId());
            
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
        
}
