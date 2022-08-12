/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetoescola_jpa.controller;

import com.ifcoder.projetoescola_jpa.model.Professor;
import com.ifcoder.projetoescola_jpa.model.dao.file.ProfessorDAO;
import java.util.List;
import model.exceptions.ProfessorException;

/**
 *
 * @author jose
 */
public class ProfessorController {

    private ProfessorDAO repositorio;
    //List<Professor> lista; //enquanto nao usamos bd ou arquivo

    public ProfessorController() {
        repositorio = new ProfessorDAO();
    }

    public void cadastrarProfessor(String nome, String sexo, String idade, String cpf) {
        Professor novoProfessor = validacaoVazio(nome, sexo, idade, cpf);

        if (repositorio.findByCpf(cpf) == null) {
            repositorio.save(novoProfessor);
        } else {
            throw new ProfessorException("Error - Já existe um professor com este 'CPF'.");
        }
    }

    public void atualizarProfessor(String cpfOriginal, String nome, String sexo, String idade, String cpf) {
        Professor novoProfessor = validacaoVazio(nome, sexo, idade, cpf);  
        repositorio.update(cpfOriginal, novoProfessor);
    }

    public Professor buscarProfessor(String cpf) {
        return this.repositorio.findByCpf(cpf);
    }

    public void excluirProfessor(String cpf) {
        //Encontrar professor e exlcui                   
        Professor prof = repositorio.findByCpf(cpf);
        if(prof != null){
            repositorio.delete(prof);                    
        } else {
            throw new ProfessorException("Error - Professor inexistente.");
        }        
    }

    public String imprimirListaProfessores() {
        String listagemProfessores = "";

        for (Object obj : this.repositorio.findAll()) {
            Professor prof = (Professor) obj;
            listagemProfessores = listagemProfessores + prof.toString();
        }
                
        return listagemProfessores;
    }
    
    private Professor validacaoVazio(String nome, String sexo, String idade, String cpf){
        Professor p = new Professor();
        if (nome.isEmpty())
            throw new ProfessorException("Error - Campo vazio: 'nome'.");
        p.setNome(nome);
        
        if (sexo.isEmpty()) 
            throw new ProfessorException("Error - Campo vazio: 'sexo'.");                
        p.setSexo(sexo.charAt(0));        

        int idadeInt = 0;
        if (idade.isEmpty())
            throw new ProfessorException("Error - Campo vazio: 'idade'.");
        
        if(!idade.matches("[0-9]*"))
            throw new ProfessorException("Error - Valor inválido no campo 'idade'.");
        
        idadeInt = Integer.parseInt(idade);
        p.setIdade(idadeInt);        

        if(cpf.isEmpty())
            throw new ProfessorException("Error - Campo vazio: 'cpf'.");
        p.setCpf(cpf);
        
        return p;
    }

}
