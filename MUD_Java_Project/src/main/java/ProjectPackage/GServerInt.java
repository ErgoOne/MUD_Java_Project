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
    public int IsDungComplete (String NomD) throws RemoteException;
    public String WhereIam (String NomJ) throws RemoteException;
    public int MaxValDungMap (String NomD) throws RemoteException;
    public int SwitchRoom (String NomD, String NomJ, int x, int y) throws RemoteException;
    public int IsRoomEmpty(int x, int y, String NomD) throws RemoteException;
}
