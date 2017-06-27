package com;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HelloClient { 
    public static void main(String args[]){ 
        try { 
            //��RMI����ע����в�������ΪRHello�Ķ��󣬲��������ϵķ��� 
        	Registry registry = LocateRegistry.getRegistry("172.18.5.76",8890);
            Itest test =(Itest) registry.lookup("test");
           // Map<String, Object> map=new HashMap<String, Object>();
           // map.put("meeting_notes", "12");
            Timestamp time=new Timestamp(Timestamp.UTC(2017, 6, 23, 8, 30, 0));
            Timestamp time2=new Timestamp(Timestamp.UTC(2017, 6, 23, 10, 30, 0));
            List<Integer> staffid=new ArrayList<Integer>();
            staffid.add(1001);
           // test.searchMeeting(map);
            test.add_meeting("zyl", "�����ڵĻ���", staffid.size(), "�����ڵ�", 3, time, time2, staffid);
            //test.addvisit();
           // System.out.println(test.searchMeeting(map));
           // test.regist("asd", "","" , "", 4, "www", "22");
           // System.out.println(test.searchstaffbydepartment(1));
        } catch (NotBoundException e) { 
            e.printStackTrace(); 
        } catch (RemoteException e) { 
            e.printStackTrace();    
        } 
        return ;
    } 
}