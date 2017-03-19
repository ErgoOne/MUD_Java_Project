/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Badr
 */
public class MsgServer extends UnicastRemoteObject implements MServerInt {
    
      
       protected MsgServer() throws RemoteException {
            super();
       }

       
       public static void main (String args[]) throws NotBoundException {
         GServerInt rmi;
          
        try {
            Registry reg = LocateRegistry.createRegistry(2020);
            MsgServer MS = new MsgServer();
            
            reg.rebind("rmi://127.0.0.79:2020/server", MS);
            System.out.println("MSG Server started ! ");
        } catch (Exception e) {
            System.out.println(" Catch MS main  : "+ e);
            }
         try {
            Registry reg = LocateRegistry.getRegistry("127.0.0.78", 1010);
             rmi = (GServerInt) reg.lookup("rmi://127.0.0.78:1010/server");
       }
         catch (RemoteException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    public String GetNewMsgs(String nomJ, Room R) throws RemoteException {
        String rtr=null;
        System.out.println("ON EST AL");
        if(1==1)
        {
            return R.GiveAllMsg();
        }
        else
            
        
    return rtr;
    }

    @Override
    public int WriteMsg(String Msg, String NomJ) throws RemoteException {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
