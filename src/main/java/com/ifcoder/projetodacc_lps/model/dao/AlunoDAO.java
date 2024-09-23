/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetodacc_lps.model.dao;

import com.ifcoder.projetodacc_lps.factory.Persistencia;
import com.ifcoder.projetodacc_lps.model.Aluno;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jose
 */
public class AlunoDAO implements IDao<Aluno> {
    
    protected Connection connection;
    private PreparedStatement statement;
    private String sql;

    public AlunoDAO() {
        this.sql = "";
    }

    @Override
    public void save(Aluno obj) {
        Aluno aluno = (Aluno) obj;

        sql = " INSERT INTO "
                + " aluno(nome, sexo, idade, matricula, anoIngresso) "
                + " VALUES(?,?,?,?,?) ";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);

            //preencher cada ? com o campo adequado
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getSexo() + "");
            statement.setInt(3, aluno.getIdade());
            statement.setString(4, aluno.getMatricula());
            statement.setInt(5, aluno.getAnoIngresso());

            statement.execute();
            statement.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        } finally {
            Persistencia.closeConnection();
        }
    }

    @Override
    public void update(Aluno aluno) {
        //Aluno aluno = (Aluno) obj;

        sql = " UPDATE aluno "
                + " SET nome=?, sexo=?, idade=?, matricula=?, anoIngresso=? "
                + " WHERE id = ?";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);

            //preencher cada ? com o campo adequado
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getSexo() + "");
            statement.setInt(3, aluno.getIdade());
            statement.setString(4, aluno.getMatricula());
            statement.setInt(5, aluno.getAnoIngresso());

            //preenche a condição do WHERE
            statement.setInt(6, aluno.getId());

            statement.execute();
            statement.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        } finally {
            Persistencia.closeConnection();
        }
    }

    @Override
    public List<Aluno> findAll() {
        List<Aluno> list = new ArrayList<>();

        sql = " SELECT * FROM aluno ORDER BY upper(nome) ";
        try {
            statement = Persistencia.getConnection().prepareStatement(sql);
            ResultSet resultset = statement.executeQuery();
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
            statement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Persistencia.closeConnection();
        }

        return list;
    }

    @Override
    public Aluno find(Aluno obj) {
        Aluno aluno = (Aluno) obj;

        sql = " SELECT * FROM aluno WHERE id = ? ";
        try {

            statement = Persistencia.getConnection().prepareStatement(sql);
            statement.setInt(1, aluno.getId());

            ResultSet resultset = statement.executeQuery();

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
            statement.close();
            return a;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        } finally {
            Persistencia.closeConnection();
        }

    }

    /**
     * Procura um professor pelo CPF, que é o identificador único
     *
     * @param matricula do aluno
     * @return Referencia para o aluno na lstAluno
     */
    public Object findByMatricula(String matricula) {
        sql = " Select * FROM aluno as a WHERE a.matricula = ? ";

        Aluno aluno = null;
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            //preenche a condição
            statement.setString(1, matricula);

            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                aluno = new Aluno(
                        resultset.getInt(1),
                        resultset.getString(2),
                        resultset.getString(3).charAt(0),
                        resultset.getInt(4),
                        resultset.getString(5),
                        resultset.getInt(6));
            }
            statement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Persistencia.closeConnection();
        }
        return aluno;
    }

    /**
     * Recebe um Aluno como parametro, procura o Aluno pela Matricula Se
     * encontrar remove ele da lstAlunos.
     *
     * @param aluno
     * @param Aluno
     * @return
     */
    @Override
    public boolean delete(Aluno aluno) {
        //Aluno aluno = (Aluno) obj;

        sql = " DELETE FROM aluno WHERE id = ? ";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            //preenche a condição
            statement.setLong(1, aluno.getId());

            statement.execute();
            statement.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Persistencia.closeConnection();
        }
    }

}
