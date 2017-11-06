/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_server;

import Rmi_Interface.MethodInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author apu
 */
public class Rmi_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            //Instantiating the implementation class
            ImplementSecurity obj=new ImplementSecurity("new.txt");
            //Exporting the object of implementation class
            //(here we are exporting the remote object to the stub)
            MethodInterface stub=(MethodInterface) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            //Binding the remote object (stub)
            //Registry registry=LocateRegistry.getRegistry();
            
            registry.bind("Hello", stub);
            //registry.bind("VigenereCipher", stub);
            System.err.println("Server ready");
            
            /*
            A4_RMI_Server obj = new A4_RMI_Server();
            RMI_Interface stub = (RMI_Interface) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Hello", stub);
            //   Registry registry = LocateRegistry.createRegistry(1099);
            //   registry.bind("Hello", stub);

            // Naming.rebind("//localhost/MyServer", new A4_RMI_Server());
            System.err.println("Server ready");
            
            */
            
        }
        catch(Exception e){
            System.err.println("Server exception");
            e.printStackTrace();
        }
        
        
    }
    
}
