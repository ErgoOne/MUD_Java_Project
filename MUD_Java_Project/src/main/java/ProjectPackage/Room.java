/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Badr
 */
public class Room implements Serializable{
    String NomR;
    public ArrayList<Player> RoomPlayers = new ArrayList<>();
    public ArrayList<String> Msg = new ArrayList<>();

    public Room(String NomR) {
        this.NomR = NomR;
    }

    public String getNomR() {
        return NomR;
    }

    public void setNomR(String NomR) {
        this.NomR = NomR;
    }
    
    public int AddPlayer(Player P)
    {
    RoomPlayers.add(P);
    if(RoomPlayers.size()>1)
        return 1;
    else return 2;
    }
    public void DelPlayer (Player P)
    {
    for (int i=0; i<RoomPlayers.size(); i++)
    {
        if(RoomPlayers.get(i).getNomJ().equals(P.getNomJ()))
            RoomPlayers.remove(i);
    }
    }
    
    public synchronized String GiveAllMsg(String nomJ, int s)
    {
        String rtr="~New Messages~";
        int x=0;
    for (int i=s-1; i<Msg.size(); i++)
    {
        String[] parts = Msg.get(i).split(":");
            String part1 = parts[0]; // x
            System.out.println("=>Room Giveallmsg Nomj: "+part1+"i:"+i);
            if(!part1.equals(nomJ))
                //if(1==1)
            {
                rtr= rtr +"\n"+Msg.get(i);
                x++;
            }
            }
    rtr=rtr+"\n"+"~End Of Messages~";
    if(x!=0){return rtr;}
    else return null;
    }
    public void WrtieMsg(String msg)
    {
    Msg.add(msg);
    }
}
