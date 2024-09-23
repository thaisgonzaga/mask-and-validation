package com.ifcoder.projetodacc_lps.model.dao.file.filePersistence;

import java.util.List;

/**
 *
 * @author jose
 */
public interface ISerializador {
    String toFile(List<Object> lista);
    
    List<Object> fromFile(String data);
}
