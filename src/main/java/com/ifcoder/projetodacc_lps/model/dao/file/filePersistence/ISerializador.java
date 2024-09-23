package com.ifcoder.projetodacc_lps.model.dao.file.filePersistence;

import java.util.List;

/**
 *
 * @author jose
 */
public interface ISerializador<T> {
    String toFile(List<T> produtos);
    
    List<T> fromFile(String data);
}
