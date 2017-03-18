/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.io.Serializable;

/**
 *
 * @author Badr
 */
public class Player implements Serializable{
    private String NomJ;
    private int Pvie;

    public void setPvie(int Pvie) {
        this.Pvie = Pvie;
    }

    public int getPvie() {
        return Pvie;
    }
    
    
    
    public Player(String NomJ) {
        this.NomJ = NomJ;
  
    }
    
    public String getNomJ() {
        return NomJ;
    }
    
    
}
