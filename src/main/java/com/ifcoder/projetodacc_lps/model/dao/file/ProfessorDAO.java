/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetodacc_lps.model.dao.file;

import com.ifcoder.projetodacc_lps.model.Professor;
import java.util.ArrayList;
import java.util.List;
import com.ifcoder.projetodacc_lps.model.exceptions.ProfessorException;

/**
 *
 * @author jose
 */
public class ProfessorDAO extends DaoFile{

    private List<Object> lst;
    
    public ProfessorDAO() {
        super("ListagemProfessores.csv");
        this.lst = new ArrayList<>();
    }        
    
    
    public void save(Object obj){     
        Professor prof = (Professor) obj;
        
        this.lst.add(prof);
        
        //Criar o CSV
        String texto = this.lstProfessorToCSV();
        //Escrever no arquivo
        super.save(texto);        
    }
    
    /**
     * Procura um professor pelo CPF, que é o identificador único
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
    
    public Object find(Object obj){        
        Professor prof = (Professor) obj;
                
        for(Object o: this.lst){
            Professor p = (Professor) o;
            if(p.equals(prof))
                return p;
        }
        
        return null;
    }        
    
    /**
     * Recebe um professor como parametro, procura o professor pelo CPF
     * Se encontrar remove ele da lstProfessores.
     * @param prof
     * @return 
     */
    @Override
    public boolean delete(Object obj){
        /**
         * Este downCasting so funciona porque ao chamar o delte na assinatura
         * ja estamos passando (em runtime) um objeto professor.
         * Se nesta classe chamássemos passando um aluno a linha 80 nao funcionaria
         */
        Professor profProcurado = (Professor) obj;
        
        for(int i=0; i<= this.lst.size()-1; i++){
            Professor p = (Professor) this.lst.get(i);
            
            if(p.getCpf().equals(profProcurado.getCpf())){
                this.lst.remove(i);
                
                //Cria CSV e escreve no arquivo
                String texto = this.lstProfessorToCSV();
                super.save(texto);
                return true;
            }
        }
        
        return false;
    }
        
    /**
     * Não vamos usar a mesma chamada de SAVE porque estamos lidando com LISTA e nosso 
     * objeto professor não tem id. Se tivesse um ID, ja daria.
     * @param cpfOriginal
     * @param novoProfessor 
     */
    public void update(String cpfOriginal, Professor novoProfessor){        
        Professor professorOriginal = (Professor) this.findByCpf(cpfOriginal);
        if(professorOriginal == null)
            throw new ProfessorException("Error update - Professor inexistente.");
        
        //copiar os dados de prof para a referencia de professor na lista
        professorOriginal.copiar(novoProfessor);              
        
        //Criar o CSV e Escrever no arquivo
        String texto = this.lstProfessorToCSV();        
        super.save(texto);        
    }
    
    @Override
    public List<Object> findAll(){
        Professor p = new Professor();        
        lst = super.loadArquivo(p);
        
        return lst; 
    }
                
    private String lstProfessorToCSV() {
        String texto = "";
        Professor a = new Professor();
        texto = a.cabecalho();

        for (Object obj: this.lst) {           
            Professor p = (Professor) obj;
            texto = texto + p.atributoToCSV();
        }

        return texto;
    }    
    
//    public List<Object> loadArquivo() {
//        FileReader f = null;
//        try {
//            f = new FileReader(this.pathArquivo);//"ListagemProfessores.csv");
//            Scanner arquivoLido = new Scanner(f);
//            arquivoLido.useDelimiter("\n");
//            
//            List<Object> lista = new ArrayList<>();
//            String linhaLida = arquivoLido.next();
//            while (arquivoLido.hasNext()) {
//                linhaLida = arquivoLido.next();
//
//                Professor p = new Professor();
//                p.CSVToAtributo(linhaLida);
//                lista.add(p);
//            }
//            return lista;
//        } catch (FileNotFoundException ex) {
//            throw new ProfessorException("Error - Arquivo 'professor' não encontrado.");
//        } finally {
//            try {
//                f.close();
//            } catch (IOException ex) {
//                throw new ProfessorException("Error - fechar arquivo 'professor' falhou.");
//            }
//        }       
//    }
    
}
