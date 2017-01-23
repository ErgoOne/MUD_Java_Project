/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Badr
 */

public class main {
    
public static void main(String[] args) throws java.net.UnknownHostException {
    
  try {
      LocateRegistry.createRegistry(1099);
    System.out.println("Mise en place du Security Manager ...");
    if (System.getSecurityManager() == null) {
      System.setSecurityManager(new RMISecurityManager());
    }
    Server serv = new Server();
      String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";
    System.out.println("Enregistrement de l'objet avec l'url : " + url);
    Naming.rebind(url, serv);

    System.out.println("Serveur lancé");
  } catch (RemoteException e) {
    e.printStackTrace();
  } catch (MalformedURLException e) {
    e.printStackTrace();
  }
}
}
