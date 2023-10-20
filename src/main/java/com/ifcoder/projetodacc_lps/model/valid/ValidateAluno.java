/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifcoder.projetodacc_lps.model.valid;

import com.ifcoder.projetodacc_lps.model.Aluno;
import com.ifcoder.projetodacc_lps.model.exceptions.AlunoException;

/**
 *
 * @author jose
 */
public class ValidateAluno {
    
    public Aluno validaCamposEntrada(String nome, String sexo, String idade, String matricula, String anoIngresso){
        Aluno aluno = new Aluno();
        if (nome.isEmpty())
            throw new AlunoException("Error - Campo vazio: 'nome'.");
        aluno.setNome(nome);
        
        if (sexo.isEmpty()) 
            throw new AlunoException("Error - Campo vazio: 'sexo'.");                
        aluno.setSexo(sexo.charAt(0));        

        int idadeInt = 0;
        if (idade.isEmpty())
            throw new AlunoException("Error - Campo vazio: 'idade'.");
        
        if(!idade.matches("[0-9]*"))
            throw new AlunoException("Error - Valor inválido no campo 'idade'.");
        
        idadeInt = Integer.parseInt(idade);
        aluno.setIdade(idadeInt);        

        if(matricula.isEmpty())
            throw new AlunoException("Error - Campo vazio: 'matricula'.");
        aluno.setMatricula(matricula);
        
        int anoInt = 0;
        if (anoIngresso.isEmpty())
            throw new AlunoException("Error - Campo vazio: 'anoIngresso'.");
        
        if(!anoIngresso.matches("[0-9]*"))
            throw new AlunoException("Error - Valor inválido no campo 'anoIngresso'.");
        
        anoInt = Integer.parseInt(anoIngresso);
        aluno.setAnoIngresso(anoInt);  
                
        return aluno;
    }
    
}
