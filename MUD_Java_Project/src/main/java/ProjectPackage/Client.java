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
/**
 *
 * @author Badr
 */
public class Client {


  public static void main(String[] args) {
    System.out.println("Lancement du client");
    if (System.getSecurityManager() == null) {
      System.setSecurityManager(new RMISecurityManager());
    }
    try {
      Remote r = Naming.lookup("rmi://127.0.0.1/TestRMI");
      System.out.println(r);
      if (r instanceof Int) {
        String s = ((Int) r).getInformation();
        System.out.println("chaine renvoyee = " + s);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (NotBoundException e) {
      e.printStackTrace();
    }
    System.out.println("Fin du client");
  }
}

