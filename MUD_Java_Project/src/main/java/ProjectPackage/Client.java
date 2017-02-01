/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Badr
 */
public class Client {
private String name;

    public void setName(String name) {
        System.out.println("ProjectPackage.Client.setName() OK");
        this.name = name;
    }

    public String getName() {
        return name;
    }
private int ConnectToServer(Client CL, GServerInt rmi) throws RemoteException {
    
            int rtr=2; // retour des fonctions interface
        
            //Naming.rebind("rmi://127.0.0.1:1099/server", reg);
            System.out.println("~WELCOME TO THE MUD !");
            System.out.println("~Please Enter your Nickname :");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (!str.isEmpty())
            {
            System.out.println("Connected to Server");
            rtr= rmi.ConnectToServ(str);
            if (rtr==1)
                {
                    System.out.println("~ "+str+ " You're connected 1 !");
                    CL.setName(str);
                    return 1;
                }
            while (rtr==2) {
                    rtr= rmi.ConnectToServ(str);
                    if (rtr==1)
                    {
                        System.out.println("~"+str+ " You're connected !");
                        CL.setName(str);
                        System.out.println("after the set name");
                        return 1;
                        //System.out.println("after the set name");
                    }
                    else if (rtr==2)
                    {
                        System.out.println("#ERROR#~"+str+ " Already exists Please choose an other nickname !");
                        str = sc.nextLine();
                    }
                    else 
                    {
                        System.out.println("##~PAS COOL "+rtr+ " " +str);
                    }
                }
            }
            
        
        return 0;
    }
  public static void main(String[] args) throws NotBoundException {
      GServerInt rmi;
      Scanner sc = new Scanner(System.in);
      try {
            Registry reg = LocateRegistry.getRegistry("127.0.0.78", 1010);
            
            //LocateRegistry.createRegistry(1099);
             rmi = (GServerInt) reg.lookup("rmi://127.0.0.78:1010/server");
      
        int choice=0;
        int rtr=0;
        int selection=0;
        Client CL= new Client();
        rtr=CL.ConnectToServer(CL, rmi);
        while (choice!=99)
        {
            System.out.println("~ORDERS~ To display Dungeon's Please press 1 :");
            System.out.println("~ORDERS~ To choose a Dungeon's Please press 2 :");
            System.out.println("~ORDERS~ To see players of a precise Dungeon press 3 :");
            System.out.println("~ORDERS~ To exit the game press 99 :");
            choice = sc.nextInt();
            if(choice==1)
            {
                System.out.println("~"+rmi.ShowDungs());
            }
            if(choice==2)
            {
                System.out.println("~ORDERS~ Please give the Dungeon number:");
                selection = sc.nextInt();
                rtr=rmi.ChooseDung(rtr, CL.getName());
                if (rtr==1)
                {
                    System.out.println("~OK~ You are on Dungeon number : "+selection);
                }
            }
            if(choice==3)
            {
                System.out.println("~ORDERS~ Please give the Dungeon number:");
                rtr = sc.nextInt();
                System.out.println("~" +rmi.ShowPofDung(rtr));
            }
          }
      

      } catch (RemoteException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
