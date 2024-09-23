/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetodacc_lps.model.dao.file;

import com.ifcoder.projetodacc_lps.model.Professor;
import com.ifcoder.projetodacc_lps.model.dao.IDao;
import java.util.ArrayList;
import java.util.List;
import com.ifcoder.projetodacc_lps.model.dao.file.filePersistence.FilePersistence;
import com.ifcoder.projetodacc_lps.model.dao.file.filePersistence.ISerializador;
import com.ifcoder.projetodacc_lps.model.dao.file.filePersistence.SerializadorCSVProfessor;

/**
 *
 * @author jose
 */
public class ProfessorDAO implements IDao {

    private List<Object> lst;
    private ISerializador serializador;
    private FilePersistence filePersistence = new FilePersistence();
    private String pathArquivo = "ListagemProfessores.csv";
    
    public ProfessorDAO() {   
        this.lst = new ArrayList<>();
        
        this.serializador = new SerializadorCSVProfessor();
        this.filePersistence = new FilePersistence();
    }

    public void save(Object obj) {
        Professor prof = (Professor) obj;
        this.lst.add(prof);
       
       String textoCSV = this.serializador.toFile(lst);
       this.filePersistence.saveToFile(textoCSV, pathArquivo);
    }

    /**
     * Procura um professor pelo CPF, que é o identificador único
     *
     * @param prof
     * @return Referencia para o professor na lstProfessores
     */
    public Professor findByCpf(String cpf){
        for(Object obj: this.lst){
            Professor prof = (Professor) obj;
            if(prof.getCpf().equals(cpf))
                return prof;
        }
        
        return null;
    }
   

    public Object find(Object obj) {
        Professor profProcurado = (Professor) obj;
        
        for(Object obj_i : this.lst) {
            Professor prof  = (Professor) obj_i;
            if (prof.equals(profProcurado)) {
                return prof;
            }
        }

        return null;
    }
    
    @Override
    public List<Object> findAll() {
        String textoLido = this.filePersistence.loadFromFile(pathArquivo);
        this.lst = serializador.fromFile(textoLido);
        
        return lst;
    }

    /**
     * Recebe um professor como parametro, procura o professor pelo CPF Se
     * encontrar remove ele da lstProfessores.
     *
     * @param prof
     * @return
     */
    @Override
    public boolean delete(Object obj) {
        Professor prof = (Professor) obj;
        Professor profProcurado = this.findByCpf(prof.getCpf());
        
        if(profProcurado == null)
            return false;
        else{
            this.lst.remove(profProcurado);
            
            String textoCSV = serializador.toFile(lst);
            this.filePersistence.saveToFile(textoCSV, pathArquivo);
            return true;
        }
        
//      Assim nao funciona, pois ja mostramos que nao podemos alterar a lista
//      no meio de uma busca
//        for (Professor prof: this.lst) {
//            if (prof.getCpf().equals(obj.getCpf())) {
//                this.lst.remove(i);
//                return true;
//            }
//        }
    }

    /**
     * Atenção, como o ID é CPF, logo não devemos permitir alterar CPF que é o ID
     * Se quiser fazer isso, é mais indicado remover e criar outro.
     * @param obj 
     */
    @Override
    public void update(Object obj) {
        Professor prof = (Professor) obj;
        for(Object obj_i: this.lst){
            Professor profCorrente = (Professor) obj_i;
            if(profCorrente.getCpf().equals(prof.getCpf())){
                profCorrente.copiar(prof);
                
                String textoCSV = this.serializador.toFile(lst);
                this.filePersistence.saveToFile(textoCSV, pathArquivo);
            }
        }
    }

}
