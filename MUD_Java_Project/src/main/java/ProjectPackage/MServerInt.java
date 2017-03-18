/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Badr
 */
public interface MServerInt extends Remote{
    
    public String GetNewMsgs (String NomJ , String NomR) throws RemoteException;
    public int WriteMsg (String Msg, String NomJ) throws RemoteException;
}
