package com;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.eclipse.persistence.internal.oxm.record.deferred.StartCDATAEvent;

public class server extends test{
	
	protected server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	private static Registry reg;
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException{
	//		start();
	
   }
	
	public boolean start() throws RemoteException, MalformedURLException, AlreadyBoundException {
		Itest test = null;
		
		reg=LocateRegistry.createRegistry(8890);
		reg.rebind("test", new test());
		//Naming.bind("rmi://172.18.5.76:8890/test", test);
		//reg.rebind("rmi://localhost:8888/test", test);
		System.out.println("start");
		return true;
	}
	public boolean stop() {
		 
		try {
			String[] lNames = reg.list();
			for (String lName : lNames) {
            Remote lRemoteObj = reg.lookup(lName);
            reg.unbind(lName);
            UnicastRemoteObject.unexportObject(this, true);
			}
			UnicastRemoteObject.unexportObject(reg, true);
			System.out.println("stop");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
}

