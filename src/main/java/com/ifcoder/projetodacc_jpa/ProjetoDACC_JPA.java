
package com.ifcoder.projetodacc_jpa;

import com.ifcoder.projetodacc_jpa.model.Secretaria;
import com.ifcoder.projetodacc_jpa.view.FrMenuPrincipal;

/**
 *
 * @author jose
 */
public class ProjetoDACC_JPA {

    public static void main(String[] args) {
        FrMenuPrincipal tela = new FrMenuPrincipal();
        tela.setVisible(true);  
        
        Secretaria s = new Secretaria();
        s.toString();
        
    }
}
