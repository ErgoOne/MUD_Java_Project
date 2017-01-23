/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author Badr
 */
public class Server extends UnicastRemoteObject implements Int {
    private static final long serialVersionUID = 2674880711467464646L;
    
    protected Server() throws RemoteException {

    super();

}

public String getInformation() throws RemoteException {

System.out.println("Invocation de la méthode getInformation()");

return "bonjour";
}
    
}
