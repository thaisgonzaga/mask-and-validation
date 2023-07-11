
package com.ifcoder.projetoescola_jpa;

import com.ifcoder.projetoescola_jpa.model.Secretaria;
import com.ifcoder.projetoescola_jpa.view.FrMenuPrincipal;

/**
 *
 * @author jose
 */
public class ProjetoEscola_JPA {

    public static void main(String[] args) {
        FrMenuPrincipal tela = new FrMenuPrincipal();
        tela.setVisible(true);  
        
        Secretaria s = new Secretaria();
        s.toString();
        
    }
}
