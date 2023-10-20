/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ifcoder.projetodacc_lps.model.dao.file;

import com.ifcoder.projetodacc_lps.model.Professor;

/**
 *
 * @author jose
 */
public interface Csv {
    public String atributoToCSV();
    
    public Object CSVToAtributo(String linhaCSV);
}
