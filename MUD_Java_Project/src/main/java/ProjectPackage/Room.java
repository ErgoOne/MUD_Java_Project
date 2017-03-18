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
    
    public synchronized String GiveAllMsg()
    {
        String rtr="";
    for (int i=0; i<Msg.size(); i++)
    {
        rtr= rtr +" - "+Msg.get(i);
    }
    return rtr;
    }
    public void WrtieMsg(String msg)
    {
    Msg.add(msg);
    }
}
