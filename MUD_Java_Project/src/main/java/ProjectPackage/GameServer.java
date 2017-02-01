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
    public ArrayList<Dungeon> Dungs = new ArrayList<>();
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
            GS.CreateDung();
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
        //System.out.println("le rtr !: "+rtr);
        if (rtr==1) 
    {
        System.out.println("~Player "+nomJ +" is connected");
        Player tmp =new Player(nomJ);
        Players.add(tmp);
        return 1; // OK
    }
    else if (rtr==2) return 2; // NOK
    else 
        return 0;
    }
    
    private int Addplayer(String nomJ) {
        int test=0;
        //System.out.println("Dans le Addplayer");
        if (!Players.isEmpty())
        {
        for (int i = 0; i < Players.size() ; i++) {
                if (nomJ.equals(Players.get(i).getNomJ()))
                        {
                        System.out.println("~Name already exists");
                        test=1;
                        return 2;
                        
                        }
        }
        if (test==0)
        {        System.out.println("New player is created : "+nomJ);
                        Player tmp = new Player(nomJ);
                        Players.add(tmp);
                        return 1;
        }
        }
        else if (Players.isEmpty())
        {
            System.out.println("Ajout du joueur : tableau empty");
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

    private void CreateDung () {
        int size;
        size= Dungs.size();
          
           Dungeon tmp =new Dungeon(Integer.toString(size+1));
           Dungs.add(tmp);
           System.out.println("Size : " + size +"Creation of a new Dungeon : " +tmp.getNomD());
           tmp.InitDungMap();
        
        }
    @Override
    public String ShowDungs() throws RemoteException {
        String tmp="";
        String Newligne=System.getProperty("line.separator");
        for (int i = 0; i < Dungs.size(); i++) {
                if (Dungs.get(i).getNPlayers()!=4)
                {
                    tmp = "--Dung "+Dungs.get(i).getNomD()+ " Number of players Wainting : "+Dungs.get(i).getNPlayers() +Newligne+ tmp;
                                    }
		}
        
       return tmp;
    }

    @Override
    public int ChooseDung(int NDung, String NomJ) throws RemoteException {
        //System.out.println("PLPLP : "+Dungs.get(NDung).getNPlayers());
                int np=Dungs.get(NDung-1).getNPlayers();
        System.out.println("NDung Given : "+NDung );
        System.out.println("ProjectPackage.GameServer.ChooseDung() Dungs size : "+Dungs.size() );
        if (NDung > Dungs.size()) 
        {return 2;}
        else if(np==4)
            return 2;
        else
        {
            for (int i = 0; i < Players.size(); i++) {
                if(Players.get(i).getNomJ().equals(NomJ)){
                    System.out.println("Adding Player"+Players.get(i)+ " to Ndung number :"+(NDung));
                    Dungs.get(NDung-1).addPlayer(Players.get(i));
                    int j = Dungs.get(NDung-1).getNPlayers();
                    if (j==3)
                    {
                        String convert = Integer.toString(Dungs.size()+1);
                        System.out.println("Creating new Dung Number : "+convert);
                        Dungeon tmp = new Dungeon(convert);
                        Dungs.add(tmp);
                         return 1;
                    }
                    return 1;
                   
                }
                System.out.println("Pas sensé etre là !!");
		}
             return 1;
            
        }

    }

    @Override
    
    public String ShowPofDung(int NDung) throws RemoteException {
        return Dungs.get(NDung-1).showplayers();
    }

    @Override
    public int ExitDung(int Ndung, String NomJ) throws RemoteException {
        System.out.println("Dans le ExitDung");
        for (int i = 0; i < Dungs.size(); i++) {
            System.out.println("Sur dung : "+Dungs.get(i).getNomD());
            if(Dungs.get(i).getNomD().equals(Integer.toString(Ndung)))
            {
                for (int j = 0; j < Dungs.size(); j++) {
                    System.out.println("NomJ du tab"+Dungs.get(i).DungPlayers.get(j).getNomJ());
                    if(Dungs.get(i).DungPlayers.get(j).getNomJ().equals(NomJ))
                    {
                        System.out.println("Second if nomj: "+Dungs.get(i).DungPlayers.get(j).getNomJ());
                       Dungs.get(i).DungPlayers.remove(j); 
                        System.out.println("Suppression du joueur du Dung");
                       return 1;
                    }
                    
                }
            }
        }
        return 0;
    }

    @Override
    public String GetDunMap(String NomJ, String NomD) {
        String rtr="";
        for (int i = 0; i < Dungs.size(); i++) {
            if(Dungs.get(i).getNomD().equals(NomD))
            rtr=Dungs.get(i).printRow(Dungs.get(i).DMap);
          return rtr;
        }
        return rtr;
    }
 
   
    
}
