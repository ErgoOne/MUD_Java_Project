/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectPackage;

import java.net.MalformedURLException;
import java.rmi.AccessException;
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

class MsgThread extends Thread
{
    GServerInt rmi;
    Client CL;
 public MsgThread (Client C, GServerInt r)
 {
     rmi = r;
     CL = C;
 }
    @Override
    public void run() {
        System.out.println("DANS LE RUN");
        try {
            System.out.println("THREAD"+this.rmi.GetRoom(this.rmi.WhereIam(this.CL.getName()), Integer.toString(this.CL.getMyDung())).GiveAllMsg());
              
        } catch (RemoteException ex) {
            Logger.getLogger(MsgThread.class.getName()).log(Level.SEVERE, null, ex);
        }
          
       
      
       
	}


}
public class Client {
private String name;
private int RefChat;
private int MyDung;

public void WriteMsg()
{

}
    public int getMyDung() {
        return MyDung;
    }
static String Newligne=System.getProperty("line.separator");
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
    public void ShowOrders ()
    {
            System.out.println("~ORDERS~ To display Dungeon's Please press 1 :");
            System.out.println("~ORDERS~ To choose a Dungeon's Please press 2 :");
            System.out.println("~ORDERS~ To see players of a precise Dungeon press 3 :");
            System.out.println("~ORDERS~ To exit the game press 99 :");
    }
    public  void ShowDungOrders (GServerInt rmi, Client CL) throws RemoteException {
            String MyPosition = rmi.WhereIam(CL.getName());
            String[] parts = MyPosition.split(",");
            String part1 = parts[0]; // x
            String part2 = parts[1]; //y
            int x = Integer.parseInt(part1);
            int y = Integer.parseInt(part2);
            int r=rmi.IsRoomEmpty(x, y, Integer.toString(CL.getMyDung()));
            if(r==1)
            {
            System.out.println("~CAUTION~ You're not alone in this room ! ");
            }
            System.out.println("~ORDERS~ To display the Dungeon's Map Please press 1 :");
            System.out.println("~ORDERS~ To see the stat of the game Please press 2 :");
            System.out.println("~ORDERS~ To see Where I am press 3 :");
            System.out.println("~ORDERS~ To moove to other room press 4 :");
            System.out.println("~ORDERS~ MSG 5 :");
            /* System.out.println("~ORDERS~ To see players of a precise Dungeon press 3 :");*/
            System.out.println("~ORDERS~ To exit the Dung please press 98 :");
    }
    public void MyMooves (GServerInt rmi, Client CL) throws RemoteException{
    
            String MyPosition = rmi.WhereIam(CL.getName());
            String[] parts = MyPosition.split(",");
            int i=1;
            Scanner sc = new Scanner(System.in);
             int choice;
             int retour=5;
            String part1 = parts[0]; // x
            String part2 = parts[1]; // y
            int x = Integer.parseInt(part1);
            int y = Integer.parseInt(part2);
            int max = rmi.MaxValDungMap(Integer.toString(CL.MyDung));
            if((x-1)>=1) { // (x-1),y
                i=1;
                System.out.println("Press "+i+" to go to the '"+(x-1)+"','"+y+"' Room");
            }
            if ((y-1)>=1) // (y-1),x
            {
                i=2;
                System.out.println("Press "+i+" to go to the '"+x+"','"+(y-1)+"' Room");
             
            }
            if ((x+1<max)) // (x+1),y
                {
                i=3;
                System.out.println("Press "+i+" to go to the '"+(x+1)+"','"+y+"' Room");
                
            }
             if ((y+1<max)) // (x),(y+1)
                {
                 i=4;
                System.out.println("Press "+i+" to go to the '"+x+"','"+(y+1)+"' Room");
                i++;
            }
             
             choice = sc.nextInt();
             if(choice==1)
             {
             retour=rmi.SwitchRoom(Integer.toString(CL.MyDung), CL.getName(), (x-1), y);
                 System.out.println("Retour : "+ retour);
                 if(retour==1)
             {
                 System.out.println("D'autres joueurs sont dans la Room !");
             }
             }
             else if(choice==2)
             {
             retour=rmi.SwitchRoom(Integer.toString(CL.MyDung), CL.getName(), x, (y-1));
             System.out.println("Retour : "+ retour);
             if(retour==1)
             {
                 System.out.println("D'autres joueurs sont dans la Room !");
             }
             }
             else if(choice==3)
             {
             retour=rmi.SwitchRoom(Integer.toString(CL.MyDung), CL.getName(), (x+1), y);
             System.out.println("Retour : "+ retour);
             if(retour==1)
             {
                 System.out.println("D'autres joueurs sont dans la Room !");
             }
             }
             else if(choice==4)
             {
             retour=rmi.SwitchRoom(Integer.toString(CL.MyDung), CL.getName(), x, (y+1));
             System.out.println("Retour : "+ retour);
             if(retour==1)
             {
                 System.out.println("D'autres joueurs sont dans la Room !");
             }
             }
             
             
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
            System.out.println("~ORDERS~ To display Orders please press 8 :");
            choice = sc.nextInt();
            if(choice==1)
            {
                System.out.println("~~~ All Dung's ~~~"+Newligne+rmi.ShowDungs());
            }
            if(choice==2)
            {
                System.out.println("~ORDERS~ Please give the Dungeon number:");
                selection = sc.nextInt();
                rtr=rmi.ChooseDung(selection, CL.getName());
                if (rtr==1)
                {
                    System.out.println("~OK~ You are on Dungeon number : "+selection);
                    CL.MyDung=selection;
                    System.out.println("mydun :" +CL.MyDung);
                    while (choice != 8)
                    {
                        CL.ShowDungOrders(rmi, CL);
                        choice = sc.nextInt();
                        if(choice ==98)
                        {
                            System.out.println("in the if 98");
                            rtr=0;
                            rtr=rmi.ExitDung(CL.MyDung, CL.getName());
                            System.out.println("after the rmi. rtr =" +rtr);
                            if (rtr==1) {
                                System.out.println("~OK~ Back to Principal menu...");
                                choice=8;
                            }
                        }
                        if (choice==1)
                        {
                            String print;
                        print = rmi.GetDunMap(CL.getName(), Integer.toString(CL.MyDung));
                            System.out.println(print);
                        }
                        if (choice==2)
                        {
                            int i;
                            System.out.println(rmi.ShowPofDung(CL.MyDung));
                            i=rmi.IsDungComplete(Integer.toString(CL.MyDung));
                            if (i==2)
                                System.out.println("~OK~ Wainting for players");
                            else 
                                System.out.println("~OK~ Game is ready");
                        }
                        if (choice==3)
                        {
                            System.out.println(rmi.WhereIam(CL.getName()));
                        }
                         if (choice==4)
                        {
                            CL.MyMooves(rmi, CL);
                        }
                         if (choice==5)
                         {
                         MsgThread t1 = new MsgThread(CL,rmi);
                         t1.start();
                         }
                    }
                }
                else if(rtr==2)
                {
                    System.out.println("~ERROR~ Too much players, please choose another Dung");
                }
            }
            if(choice==3)
            {
                System.out.println("~ORDERS~ Please give the Dungeon number:");
                rtr = sc.nextInt();
                System.out.println("~" +rmi.ShowPofDung(rtr));
            }
            if (choice==8)
            {
                CL.ShowOrders();
            }
          }
          System.out.println("Good bye..");

      } catch (RemoteException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
