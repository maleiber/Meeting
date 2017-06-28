package com;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

import net.sf.json.JSONObject;

public class test2 {
	public static void main(String[] args) throws AlreadyBoundException, IOException{
     test test=new test();
     Timestamp time=new Timestamp(0);
     JSONObject jsonString=new JSONObject();
     jsonString.put("name", "");
     jsonString.put("username", "");
     jsonString.put("state", 3);
    // System.out.println(test.searchstaff(jsonString.toString()));
     //test.add_meeting_room("第二会议室", 30, "301", "", "空闲");
     // test.show_meetingroom(1);
    // test.modify_meeting_room(2,"第二会议室", 40, "401", "", "空闲");
    // List<Integer> staffid=new ArrayList<Integer>();
     
    // staffid.add(1002);
    // test.add_meeting("zyl","商品会议", staffid.size(), "无", 1,"2117/06/23 08:30:00","2117/06/23 08:30:00", staffid);
    // test.add_dep_relation_staff(1034, 1);
    // test.refuse_regisiton(3);
    // test.showregistall(1);
     
     //Map<String, Object> map=new HashMap<String, Object>();
     //map.put("meeting_notes", "12");
    // test.searchMeeting(map);
     
  //   test.regist("passl","18704559882", "2376234890@qq.com", "职员", 6, "passl", "dsadfa");
  //   test.passregistion(4);
    // test.passregistion(4);
   //  test.adduser();
     server server=new server();
     server.start();
     System.in.read();
     server.stop();
	}     
}
