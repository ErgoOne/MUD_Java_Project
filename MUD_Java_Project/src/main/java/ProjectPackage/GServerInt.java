/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;
import java.rmi.*;
/**
 *
 * @author Badr
 */
public interface GServerInt extends Remote{
    public int ConnectToServ (String nomJ) throws RemoteException;
    public String ShowDungs () throws RemoteException;
    public int ChooseDung (int NDung, String NomJ) throws RemoteException;
    public String ShowPofDung (int NDung) throws RemoteException;
    public int ExitDung (int Ndung, String NomJ) throws RemoteException;
    public String GetDunMap (String NomJ, String NomD) throws RemoteException;
}
