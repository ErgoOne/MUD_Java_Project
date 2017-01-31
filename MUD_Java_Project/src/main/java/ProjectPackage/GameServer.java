/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;
/**
 *
 * @author Badr
 * Code retour fonctions : 1 => OK 2 => NOK 3 => ERROR
 */
public class GameServer extends UnicastRemoteObject implements GServerInt {
    private static final long serialVersionUID = 2674880711467464646L;
     //private static Vector classes = new Vector();
    // Gestion des joueurs loggés 
    public ArrayList<Player> Players = new ArrayList<>();
    protected GameServer() throws RemoteException {

    super();

}

    public static void main (String args[]) {
        try {
            Registry reg = LocateRegistry.createRegistry(1010);
            //Naming.rebind("rmi://127.0.0.1:1099/server", reg);
            GameServer GS = new GameServer();
            reg.rebind("rmi://127.0.0.78:1010/server", GS);
            System.out.println("Server started ! ");
           
        } catch (Exception e) {
            System.out.println(" Catch GS main  : "+ e);
            }
    }

    // Demande de connexion d'un joueur avec son pseudo 
    // + Verif d'existance du pseudo
    public int ConnectToServ(String nomJ) throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    int rtr=0;
    rtr=Addplayer(nomJ);
        if (rtr==1) 
    {
        System.out.println("~Player "+nomJ +" is connected");
        return 1; // OK
    }
    else if (rtr==2) return 2; // NOK
    else 
        return 0;
    }
    
    private int Addplayer(String nomJ) {
        System.out.println("Dans le Addplayer");
        if (!Players.isEmpty())
        {
        for (int i = 0; i < Players.size(); i++) {
                if (nomJ.equals(Players.get(i).getNomJ()))
                        {
                        System.out.println("Dans le Addplayer + boucle "+i+ " nonj : "+nomJ);
                        Player tmp = new Player(nomJ);
                        Players.add(tmp);
                        return 1;
                        }
                else {
                    System.out.println("Dans le Addplayer ELSE");
                    return 2;} // Joueur existant
        }
        }
        else if (Players.isEmpty())
        {
            Player tmp = new Player(nomJ);
                        Players.add(tmp);
                           return 1;
        }
		
        return 0; // Erreur
    }
    
    /* IMPORTANT => Surrement à utiliser plus tard pour utiliser un ibjet joureur A MODIFIER OFC */
   /* public static Player getInstance(String attribut1, String attribut2) {
            
                System.out.println("ATTT 1 /" +attribut1 +" ATTT 2 / "+attribut2);
		Player tmp = new Room(attribut1, attribut2);
		if (classes.contains(tmp)) {
			// on doit retrouver l'element
			//Enumeration enume = classes.elements();
			while (enume.hasMoreElements()) {
				Room element = (Room) enume.nextElement();
				if (element.equals(tmp)) {
					return element;
				}
			}
		}
		else {
			classes.add(tmp);
			return tmp;
		}
                                // si on arrive là, c'est qu'il y à un problème
                                return null;
	}*/
 
   
    
}
