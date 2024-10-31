/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetodacc_lps.controller;

import com.ifcoder.projetodacc_lps.model.Professor;
import com.ifcoder.projetodacc_lps.model.dao.file.ProfessorDAO;
import com.ifcoder.projetodacc_lps.model.valid.ValidateProfessor;
import com.ifcoder.projetodacc_lps.model.exceptions.ProfessorException;
import com.ifcoder.projetodacc_lps.model.valid.ValidatePessoaFisica;

/**
 *
 * @author jose
 */
public class ProfessorController {

    private ProfessorDAO repositorio;

    public ProfessorController() {
        repositorio = new ProfessorDAO();
    }

    public void cadastrarProfessor(String nome, String sexo, String idade, String cpf) {
        ValidateProfessor valid = new ValidateProfessor();
        Professor novoProfessor = valid.validacao(nome, sexo, idade, cpf);
        ValidatePessoaFisica validCPF = new ValidatePessoaFisica();
        if(!validCPF.validaCPF(cpf))
            throw new ProfessorException("Error - CPF inválido.");
        

        if (repositorio.findByCpf(cpf) == null) {
            repositorio.save(novoProfessor);
        } else {
            throw new ProfessorException("Error - Já existe um professor com este 'CPF'.");
        }
    }

    public void atualizarProfessor(String cpfOriginal, String nome, String sexo, String idade, String cpf) {
        ValidateProfessor valid = new ValidateProfessor();
        Professor novoProfessor = valid.validacao(nome, sexo, idade, cpf);
        novoProfessor.setCpf(cpfOriginal);
        
        repositorio.update(novoProfessor);
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
    
    

}
