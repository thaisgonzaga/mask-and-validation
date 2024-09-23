package com.ifcoder.projetodacc_lps.model.dao.file.filePersistence;

import com.ifcoder.projetodacc_lps.model.Professor;
import com.ifcoder.projetodacc_lps.model.dao.file.filePersistence.ISerializador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class SerializadorCSVProfessor implements ISerializador<Professor> {
    
    // Serializa = Salvar no formato texto
    //Serializa a lista de produtos para uma String no formato CSV
    public String toFile(List<Professor> lista) {
        String csv = "CPF;Nome;Sexo;Idade;\n";
        for (Professor professor : lista) {
            csv += professor.getCpf() + ";" 
                + professor.getNome()+ ";"
                + professor.getSexo() + ";"
                + professor.getIdade() + ";\n";
        }
        return csv;
    }

    // Deserializa uma String no formato CSV para uma lista de produtos
    public List<Professor> fromFile(String data) {
        List<Professor> lista = new ArrayList<>();
        
        String[] linhas = data.split("\n");
        // Ignora o cabeçalho
        for (int i = 1; i < linhas.length; i++) {
            String[] partes = linhas[i].split(";");
            if (partes.length >= 4) {
                Professor professor = new Professor();
                professor.setCpf(partes[0]);
                professor.setNome(partes[1]);
                professor.setSexo(partes[2].charAt(0));
                professor.setIdade(Integer.parseInt(partes[3]));
                
                //apos montar um produto, adiciono na lista
                lista.add(professor);
            }
        }
        return lista;
    }
}
