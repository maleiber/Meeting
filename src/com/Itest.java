package com;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public interface Itest extends Remote{
	
	public int addvisit()throws RemoteException;

	public boolean regist(
			String name,String telephone,String email,
			String position,int departmentid,
			String username,String password
			)throws RemoteException;
	
	public boolean regist(
			String jsonString
			)throws RemoteException;
	
	public String login(
			String name,String password
			)throws RemoteException;//JSONObject
	public String login(
			String jsonString
			)throws RemoteException;
	
	public boolean refuse_regisiton(int EmployeeCopyid)throws RemoteException;
	public boolean recover_regisiton(int EmployeeCopyid)throws RemoteException;
	public boolean passregistion(int EmployeeCopyid)throws RemoteException;
	public String showregistall(int status)throws RemoteException;//JSONArray
	public String showpassedall()throws RemoteException;//JSONArray
	
	public boolean deletestaff(int staffid)throws RemoteException;
	public boolean deletestaffcopy(int staffid)throws RemoteException;
	public boolean delete_personal_book_meeting(int meetingid)throws RemoteException;
	
	public String searchstaffbyname(String staff_name)throws RemoteException;//JSONArray
	public String searchstaffbyusername(String staff_name)throws RemoteException;//JSONArray
	public String searchstaffbydepartment(int departmentid)throws RemoteException;//JSONArray
	public String searchstaffbydepartment()throws RemoteException;//JSONArray
	public String searchstaff(String jsonString)throws RemoteException;//JSONArray
	
	public String searchMeeting(String jsonString)throws RemoteException;
	public String searchMeeting(Map<String, Object> map)throws RemoteException;//JSONArray
	public String searchMeeting(
			String meeting_name,String meeting_notes,
			String book_name,int people_num,
			int meetingroom_id,int status,
    		String start_time,String end_time,String book_time
    		)throws RemoteException;
			//JSONArray
	
	
	public boolean add_department(String departmentname)throws RemoteException;
	
	public boolean modify_department(int departmentid,String modifyname)throws RemoteException;
	public boolean modify_department(String jsonString)throws RemoteException;
	
	public boolean delect_department(int departmentid)throws RemoteException;
	public String show_department()throws RemoteException;//JSONArray
	
	public String add_meeting(
			int bookid,String meetingname,
			int meeting_num,String meeting_notes,
			int meetingroom_id,
			String startTime, String endTime,
			List<Integer>staffid
			)throws RemoteException;
	public String add_meeting(String jsonString)throws RemoteException;
	
	public boolean add_meeting_room(
			String meetingroom_name,int capacity,
			String room_numbler,String remark,
			String atate)throws RemoteException;
	public boolean add_meeting_room(String jString)throws RemoteException;
	
	public boolean delete_meeting_room(int meetingroom_id)throws RemoteException;
	public boolean modify_meeting_room(
			int meetingroomid,String meetingroom_name,
			int capacity,String room_numbler,
			String remark,String atate)throws RemoteException;
	public boolean modify_meeting_room(String jsonString)throws RemoteException;
	
	public String show_allmeetingroom()throws RemoteException;//JSONArray
	public String show_meetingroom(int meetingroomid)throws RemoteException;//JSONObject
	public String show_personal_meeting(int staffid)throws RemoteException;//JSONArray
	
	public String show_personal_book_meeting(int staffid,String username)throws RemoteException;
	
	public int checkusername(String username)throws RemoteException;
	
	public String search_meeting_room_by_name(String name)throws RemoteException;
	public String search_meeting_room_by_number(String number)throws RemoteException;
	
	public boolean reverse_staff(int staffid)throws RemoteException;
	
	public String search_avaliable_meetingroom(String json)throws RemoteException;
	public String search_avaliable_meetingroom(String starttime,String endtime,int nownumber)throws RemoteException;
	public String search_avaliable_staff(String starttime,String endtime)throws RemoteException;
	public String search_avaliable_staff(String json)throws RemoteException;
	public boolean checkbooker_time_conflict(String jsonString)throws RemoteException;
	public String search_future_7daysMeeting(String bookname)throws RemoteException;
	public String search_future_meeting(String bookname)throws RemoteException ;
	public String search_meeting_by_staff(int staffid)throws RemoteException;
	public boolean modify_staff(String jsonString)throws RemoteException;
	
	public boolean cancel_meeting(int meetingid)throws RemoteException;
	
	public boolean cancel_meeting(String jsonString)throws RemoteException;
	public String search_meeting_by_staff(String jsonString) throws RemoteException;
	
}
