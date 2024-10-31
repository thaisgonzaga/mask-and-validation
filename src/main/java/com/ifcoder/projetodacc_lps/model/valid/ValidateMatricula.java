/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifcoder.projetodacc_lps.model.valid;

/**
 *
 * @author thais
 */
public class ValidateMatricula {
    
    public ValidateMatricula() { }
    
    public boolean validaMatricula(String matricula){
        if(matricula.isEmpty() || !(matricula.length() == 7 || matricula.length() == 6)){
            return false;
        }
        
        try {
            int ano = Integer.parseInt(matricula.substring(0,4));
            int posicao = Integer.parseInt(matricula.substring(matricula.length() - 2, matricula.length()));
            return ano >= 1980 && ano <= 2124 && posicao >= 0 && posicao <= 99;
        } catch (NumberFormatException e){
            return false;
        }
    }
    
}
