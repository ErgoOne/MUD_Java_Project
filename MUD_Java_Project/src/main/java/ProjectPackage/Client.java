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
/**
 *
 * @author Badr
 */
public class Client {

private void ConnectToServer() {
    
            int rtr=0; // retour des fonctions interface
        try {
            Registry reg = LocateRegistry.getRegistry("127.0.0.78", 1010);
            
            //LocateRegistry.createRegistry(1099);
            GServerInt rmi = (GServerInt) reg.lookup("rmi://127.0.0.78:1010/server");
            //Naming.rebind("rmi://127.0.0.1:1099/server", reg);
            System.out.println("~WELCOME TO THE MUD !");
            System.out.println("~Please Enter your Nickname :");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (!str.isEmpty())
            {
            rtr= rmi.ConnectToServ(str);
            System.out.println("Connected to Server");
            if (rtr==1)
            {
                System.out.println("~ "+str+ " You'r connected !");
            }
            else if (rtr==2)
            {
                System.out.println("##~ "+str+ " Already exists !");
            }
            else 
            {
                System.out.println("##~PAS COOL "+rtr+ " " +str);
            }
            }
            
        } catch (Exception e) {
            System.out.println(" CATCH ConnectToServer Method " +e);
        }
    }
  public static void main(String[] args) {
    
      Client CL= new Client();
      CL.ConnectToServer();
      
}
}

