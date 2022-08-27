/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetoescola_jpa.model.dao.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.exceptions.ProfessorException;
import com.ifcoder.projetoescola_jpa.model.dao.IDao;

/**
 *
 * @author jose
 */
public abstract class DaoFile implements IDao{
    
    protected String pathArquivo;

    public DaoFile() {
        this.pathArquivo = "file.csv";
    }    

    public DaoFile(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }         
    
    /**
     * Escreve uma lista de objetos no arquivo de texto
     * @param obj
     * @param texto 
     */    
    public void save(Object obj) {
        String texto = (String) obj;
        FileWriter arq = null;
        try {
            arq = new FileWriter(this.pathArquivo);
            PrintWriter gravaArq = new PrintWriter(arq);
            gravaArq.print(texto);
            arq.close();
        } catch (IOException ex) {
           //nao possso "furar fila" das camadas fazendo SOUT aqui, devemos usar throw
           throw new RuntimeException("Error -  salvar arquivo falhou." + ex.getMessage());           
        } finally {
            try {
                arq.close();
            } catch (IOException ex) {
                throw new RuntimeException("Error -  fechar arquivo falhou." + ex.getMessage()); 
            }
        }
    }        
    
    protected List<Object> loadArquivo(Csv object) {
        FileReader f = null;
        try {
            f = new FileReader(this.pathArquivo);//"ListagemProfessores.csv");
            Scanner arquivoLido = new Scanner(f);
            arquivoLido.useDelimiter("\n");
            
            List<Object> lista = new ArrayList<>();
            String linhaLida = arquivoLido.next();

            while (arquivoLido.hasNext()) {
                linhaLida = arquivoLido.next();
                
                
                Object objetoLido = this.CSVToObjeto(object, linhaLida);                
                lista.add(objetoLido);
                //Professor p = new Professor();                
                //p.CSVToAtributo(linhaLida);
                //lista.add(p);
                
            }
            return lista;
        } catch (FileNotFoundException ex) {
            throw new ProfessorException("Error - Arquivo 'professor' não encontrado.");
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                throw new ProfessorException("Error - fechar arquivo 'professor' falhou.");
            }
        }       
    }
    
    /**
     * - Trouxemos o metodo LOAD_ARQUIVO() para cá
     * - Precisamo trocar o retorno. Para isso a lista criada dentro do método mudou para List<Object>
     * >> Polimorfismo
     * - criamos um metodo privado 'Object CSVToObjeto(Csv obj, String linhaLidaCSV)'
     * - criamos uma interface CSV para conseguir gerenciar a entrada neste parametro e disparar o metodo CSVToAtributo nas
     * classes que implementarem CSV
     * - Assim, somente as classes ou entidades que quiserem trabalhar com arquivo deverão implementar e CSVToAtributo e poderão
     * entrar no metodo 'Object CSVToObjeto(Csv obj, String linhaLidaCSV)'
     * - Com isso, trouxemos o metodo LOAD_ARQUIVO para cá
     * 
     * @param obj
     * @param linhaLidaCSV
     * @return 
     */
    private Object CSVToObjeto(Csv obj, String linhaLidaCSV){        
        Object novo = obj.CSVToAtributo(linhaLidaCSV);
        return novo;
    }
        
}
