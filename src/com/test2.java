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

public class test2 {
	public static void main(String[] args) throws AlreadyBoundException, IOException{
     test test=new test();
     Timestamp time=new Timestamp(0);
     //test.add_meeting_room("�ڶ�������", 30, "301", "", "����");
     // test.show_meetingroom(1);
    // test.modify_meeting_room(2,"�ڶ�������", 40, "401", "", "����");
    // List<Integer> staffid=new ArrayList<Integer>();
     
    // staffid.add(1002);
    // test.add_meeting("zyl","��Ʒ����", staffid.size(), "��", 1,"2117/06/23 08:30:00","2117/06/23 08:30:00", staffid);
    // test.add_dep_relation_staff(1034, 1);
    // test.refuse_regisiton(3);
    // test.showregistall(1);
     
     //Map<String, Object> map=new HashMap<String, Object>();
     //map.put("meeting_notes", "12");
    // test.searchMeeting(map);
     
  //   test.regist("passl","18704559882", "2376234890@qq.com", "ְԱ", 6, "passl", "dsadfa");
  //   test.passregistion(4);
    // test.passregistion(4);
   //  test.adduser();
     server server=new server();
     server.start();
     System.in.read();
     server.stop();
	}     
}
