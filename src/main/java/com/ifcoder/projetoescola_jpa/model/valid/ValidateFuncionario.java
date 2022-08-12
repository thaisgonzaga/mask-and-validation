
package com.ifcoder.projetoescola_jpa.model.valid;

import com.ifcoder.projetoescola_jpa.model.Aluno;
import com.ifcoder.projetoescola_jpa.model.Funcionario;
import model.exceptions.AlunoException;

/**
 *
 * @author jose
 */
public class ValidateFuncionario {
    
    public Funcionario validaCamposEntrada(String nome, String email){
        Funcionario funcionario = new Funcionario();
        if (nome.isEmpty())
            throw new AlunoException("Error - Campo vazio: 'nome'.");
        funcionario.setNome(nome);
        
        if (email.isEmpty()) 
            throw new AlunoException("Error - Campo vazio: 'email'.");                
        funcionario.setEmail(email);

        return funcionario;
    }
    
}
