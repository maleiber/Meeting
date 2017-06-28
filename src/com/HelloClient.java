package com;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HelloClient {
    Itest test;
    public HelloClient(){

    }
    public Itest gettest(){
        try {
            Registry registry = LocateRegistry.getRegistry("172.18.5.76",8890);
            test =(Itest) registry.lookup("test");

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return test;
    }

}