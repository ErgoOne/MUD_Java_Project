/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.util.ArrayList;

/**
 *
 * @author Badr
 */
public class Dungeon {

    
    public String NomD;
    public ArrayList<Player> DungPlayers = new ArrayList<>();
    public ArrayList<String> Chat = new ArrayList<>();
    
    //Room DRooms[5][5];

    public Dungeon(String NomD) {
        this.NomD = NomD;
    }

    public String getNomD() {
        return NomD;
    }

    public void setNomD(String NomD) {
        this.NomD = NomD;
    }
    public int getNPlayers(){
       // System.out.println("size of dungplayers : "+DungPlayers.size() );
        return DungPlayers.size();
    }
    public void addPlayer(Player p) {
    DungPlayers.add(p);
    }
    public String showplayers()
    {
        String Newligne=System.getProperty("line.separator");
        String tmp = "";
        for (int i = 0; i < DungPlayers.size(); i++) {
                tmp = "~ " +DungPlayers.get(i).getNomJ()+ Newligne + tmp;
		}
        return tmp;
    }
}
