
package com.ifcoder.projetodacc_lps;

import com.ifcoder.projetodacc_lps.model.Secretaria;
import com.ifcoder.projetodacc_lps.view.FrMenuPrincipal;

/**
 *
 * @author jose
 */
public class ProjetoDACC_LPS {

    public static void main(String[] args) {
        FrMenuPrincipal tela = new FrMenuPrincipal();
        tela.setVisible(true);  
        
        Secretaria s = new Secretaria();
        s.toString();
        
    }
}
