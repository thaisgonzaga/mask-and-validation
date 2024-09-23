/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetodacc_lps.controller;

import com.ifcoder.projetodacc_lps.model.Aluno;
import com.ifcoder.projetodacc_lps.model.dao.AlunoDAO;
import com.ifcoder.projetodacc_lps.model.valid.ValidateAluno;
import java.util.List;
import javax.swing.JTable;
import com.ifcoder.projetodacc_lps.model.exceptions.AlunoException;

/**
 *
 * @author jose
 */
public class AlunoController {

    private AlunoDAO repositorio;

    public AlunoController() {
        repositorio = new AlunoDAO();
    }

    public void cadastrarAluno(String nome, String sexo, String idade, String matricula, String anoIngresso) {
        ValidateAluno valid = new ValidateAluno();
        Aluno novoAluno = valid.validaCamposEntrada(nome, sexo, idade, matricula, anoIngresso);

        if (repositorio.findByMatricula(matricula) == null) {
            repositorio.save(novoAluno);
        } else {
            throw new AlunoException("Error - Já existe um aluno com esta 'Matricula'.");
        }
    }

    public void atualizarAluno(int idAluno, String nome, String sexo, String idade, String matricula, String anoIngresso) {
        ValidateAluno valid = new ValidateAluno();
        Aluno novoAluno = valid.validaCamposEntrada(nome, sexo, idade, matricula, anoIngresso);
        novoAluno.setId(idAluno);
        
        repositorio.update(novoAluno);
    }

    public Aluno buscarAluno(String matricula) {
        return (Aluno) this.repositorio.findByMatricula(matricula);
    }

    public void atualizarTabela(JTable grd) {
        List<Aluno> lst = repositorio.findAll();
        
        TMCadAluno tmAluno = new TMCadAluno(lst);
        grd.setModel(tmAluno);        
    }

    public void excluirAluno(Aluno aluno) {
        if (aluno != null) {
            repositorio.delete(aluno);
        } else {
            throw new AlunoException("Error - Aluno inexistente.");
        }
    }    

}
