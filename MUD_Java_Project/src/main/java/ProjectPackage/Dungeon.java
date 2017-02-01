/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Badr
 */
public class Dungeon {

    
    public String NomD;
    public ArrayList<Player> DungPlayers = new ArrayList<>();
    public ArrayList<String> Chat = new ArrayList<>();
    public Room[][] DMap= new  Room[5][5];
    //Room DRooms[5][5];
    public void InitDungMap()
    {
        String Rname;
        System.out.println("Trying to create the map");
        for (int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                Rname=Integer.toString(i);
                Rname=Rname+","+Integer.toString(j);
                System.out.println("Rname : "+Rname);
                DMap[i][j]= new Room(Rname);
            }
        }
        System.out.println("#Creation of the Map OK for the Dung:");
       // System.out.println(Arrays.deepToString(DMap));
        /* for(Room[] row : DMap) {
            printRow(row);
        }*/
         
         
    }
    
    public  String printRow(Room[][] map) {
      int j=0;
      String Newligne=System.getProperty("line.separator");
        String rtr ="";
        for (Room[] row : map)
        {     
            for (Room i : row) {
                System.out.print("\t");
                rtr=rtr+"\t";
                System.out.print("[");
                rtr=rtr+"[";
                System.out.print(i.getNomR());
                rtr=rtr+i.getNomR();
                System.out.print("]");
                rtr=rtr+"]";
                System.out.print("\t");
                rtr=rtr+"\t";
                if(j==4||j==9||j==14||j==19||j==24)
                {
                    System.out.println("");
                    rtr=rtr+Newligne;
                }
                j++;
            }
        }
        System.out.println();
        return rtr;
    }

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
