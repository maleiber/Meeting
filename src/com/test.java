package com;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.persistence.jaxb.javamodel.JavaAnnotation;

import com.sun.javafx.util.TempState;
import com.sun.org.apache.bcel.internal.generic.I2F;

//import org.eclipse.persistence.internal.oxm.record.json.JSONParser.pair_return;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.webkit.ContextMenu.ShowContext;

import data.*;
import javafx.util.Pair;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class test extends UnicastRemoteObject implements Itest,Serializable{  
	protected test() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 112233445566L;
	public int visit;
	public int addvisit()throws RemoteException{
		List<VisitNum> a=new ArrayList<VisitNum>();
		VisitNumDAO b=new VisitNumDAO();
		a=b.findAll();
		a.get(0).setNum(a.get(0).getNum()+1);
        System.out.println(a.get(0).getNum());
        b.update(a.get(0));
        return a.get(0).getNum();
	}
	public boolean regist(String name,String telephone,String email,String position,int departmentid,String username,String password)throws RemoteException{
	//	List<EmployeeCopy> a=new ArrayList<EmployeeCopy>();
		
		if(checkusername(username)==3){
		EmployeeCopyDAO b=new EmployeeCopyDAO();
		int max;
		max=b.getcount();
		while(b.findById(max+1)!=null){max++;}
		
		String tmpString="tryto regist no="+(max+1)+"name="+name;
		EntityManagerHelper.log(tmpString, Level.INFO, null);
		EmployeeCopy a=new EmployeeCopy(max+1,name,telephone,email,position,1,departmentid,0,username,password,null);	
	//	a.setEmail(email);a.setLevel(1);a.setName(name);a.setPosition(position);a.setTelephone(telephone);
		b.update(a);
		EntityManagerHelper.log("add username success", Level.INFO, null);
		return true;
		}
		else{
			EntityManagerHelper.log("add username failure", Level.INFO, null);
			return false;
			
		}
	}
	
	public boolean regist(String jsonString) throws RemoteException
	{
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		
		String name,telephone,email,position;
		int departmentid;
		String username,password;
		name=(String)tempJson.get("name");
		telephone=(String)tempJson.get("telephone");
		email=(String)tempJson.getString("email");
		position=(String)tempJson.get("position");
		departmentid=(int)tempJson.get("departmentid");
		username=(String)tempJson.get("username");
		password=(String)tempJson.get("password");
		//(String name,String telephone,String email,String position,int departmentid,String username,String password)
		return regist(name,telephone,email,position,departmentid,username,password);
	}
	
	public int checkusername(String username){
		UserDAO mUserDAO=new UserDAO();
		EmployeeCopyDAO mEmployeeCOPYDAO=new EmployeeCopyDAO();
		List<EmployeeCopy> ecList=new ArrayList<EmployeeCopy>();
		if(mUserDAO.findByProperty("username", username).size()>0){
			EntityManagerHelper.log("username conflict in user data!", Level.INFO, null);
			//duo user name already exist in user
			return 0;
			
		}
		else{
			ecList=mEmployeeCOPYDAO.findByProperty("username", username);
			if(ecList!=null&&ecList.size()>0){
				for(EmployeeCopy ec:ecList)
				{
					if(ec.getCheckStatus()==0)
					{
						EntityManagerHelper.log("username confilct in employcopy data:still wait pass", Level.INFO, null);
						//dai tongguo
						return 1;
					}
				}
				EntityManagerHelper.log("username confilct in employcopy data: not pass", Level.INFO, null);
				//wei tongguo
				return 2;
			}
			else{
				EntityManagerHelper.log("check staff username success: can use", Level.INFO, null);
				return 3;
			}
		}
	}
	
	public String showregistall(int status)throws RemoteException{
		JSONArray ret_val=new JSONArray();
		List<EmployeeCopy> a=new ArrayList<EmployeeCopy>();
		EmployeeCopyDAO b=new EmployeeCopyDAO();
		a=b.findAll();
		
		String tempString;
		
		for(EmployeeCopy ec:a)
		{
			
			if(ec.getCheckStatus()==status)
			{
				tempString="name:"+ec.getName()+" email:"+ec.getEmail();
				EntityManagerHelper.log(tempString, Level.INFO, null);
				ret_val.add(JSONObject.fromObject(ec));
			}
		}
		return ret_val.toString();
		
	}
	
	public String showpassedall()throws RemoteException{
		JSONArray ret_val=new JSONArray();
		List<Employee> a=new ArrayList<Employee>();
		EmployeeDAO b=new EmployeeDAO();
		a=b.findAll();
		
		for(Employee ec:a)
		{
			
			ret_val.add(JSONObject.fromObject(ec));
		
		}
		return ret_val.toString();
		
	}
	
	
	
	
	
	
	public  boolean deletestaffcopy(int staffid)throws RemoteException{
		EmployeeCopy a=new EmployeeCopy();
		EmployeeCopyDAO b=new EmployeeCopyDAO();
		a=b.findById(staffid);
		if(a==null){return false;}
		else{
		b.delete(a);
		return true;
		}
		
	}
	
	
	
	public boolean delete_personal_book_meeting(int meetingid)throws RemoteException{
		MeetingDAO meetingDAO=new MeetingDAO();
		Meeting meetings=new Meeting();
		MeetingRelationStaffDAO mRelationStaffDAO= new MeetingRelationStaffDAO();
		List<MeetingRelationStaff> meetingRelationStaffs=new ArrayList<MeetingRelationStaff>();
        meetings= meetingDAO.findById(meetingid);
        meetingRelationStaffs=mRelationStaffDAO.findByProperty("meetingId",meetingid);
        meetingDAO.delete(meetings);
        for(int i=0;i<meetingRelationStaffs.size();i++){
        mRelationStaffDAO.delete(meetingRelationStaffs.get(i));
        }
		return true;
	}
	
	
	public boolean deletestaff(int staffid)throws RemoteException{
		List<Employee> a=new ArrayList<Employee>();
		EmployeeDAO b=new EmployeeDAO();
		UserDAO ud=new UserDAO();
		User u;
		a=b.findByProperty("staffId", staffid);
		if(a.size()>0){
			b.delete(a.get(0));
			//delete user
			u=ud.findByProperty("userId", a.get(0).getUserid()).get(0);
			ud.delete(u);
			return true;
			}
		else return false;
	}
	
	
	public boolean refuse_regisiton(int EmployeeCopyid)throws RemoteException{
		EmployeeCopy a=new EmployeeCopy();
		EmployeeCopyDAO b=new EmployeeCopyDAO();
		a=b.findById(EmployeeCopyid);
		if(a==null){
			return false;
		}
		else{
			a.setCheckStatus(1);
			b.update(a);
			return true;
		}	
	}
	
	public boolean recover_regisiton(int EmployeeCopyid)throws RemoteException{
		EmployeeCopy a=new EmployeeCopy();
		EmployeeCopyDAO b=new EmployeeCopyDAO();
		a=b.findById(EmployeeCopyid);
		if(a==null){
			return false;
		}
		else{
			a.setCheckStatus(0);
			b.update(a);
			return true;
		}	
	}
	
	
	public boolean passregistion(int EmployeeCopyid)throws RemoteException{	
		EmployeeCopy a=new EmployeeCopy();
		EmployeeCopyDAO b=new EmployeeCopyDAO();
		
		Department depart=new Department();
		DepartmentDAO depDAO=new DepartmentDAO();
		
		DepartmentRelationStaff departmentRelationStaff=new DepartmentRelationStaff();
		DepartmentRelationStaffDAO departmentRelationStaffDAO=new DepartmentRelationStaffDAO();
		
		int departmentid=b.findById(EmployeeCopyid).getDepartmentid();
		//max=b.getcount();
		
		int max=depDAO.findById(departmentid).getDepartmentNum();
		int staff_id=assign(departmentid,max);
		
	//	departmentRelationStaff.getId().setStaffId(staff_id);
	//	departmentRelationStaff.getId().setDepartmentId(departmentid);
		adduser(b.findById(EmployeeCopyid).getUsername(),b.findById(EmployeeCopyid).getPassword());
	//	UserDAO ud = new UserDAO();
	//	User u=new User();
		
	//	UserId ui =new UserId();
	//	u.setUserId(ud.getcount()+1);
	//	u.setPassword(b.findById(EmployeeCopyid).getPassword());
	//	u.setUsername(b.findById(EmployeeCopyid).getUsername());
	//	u.setUserId(ui);
	//	ud.update(u);
		
		
		a=b.findById(EmployeeCopyid);
		deletestaffcopy(EmployeeCopyid);
		try {
			addstaff(staff_id,a.getName(),a.getTelephone(),a.getEmail(),a.getPosition());
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}		
		
		depart=depDAO.findById(departmentid);
		if(depart==null){
			return false;
		}
		else{
		depart.setDepartmentNum(depart.getDepartmentNum()+1);
		add_dep_relation_staff(staff_id,departmentid);
		depDAO.update(depart);
		return true;
		}
		
		
	}
	
	  void add_dep_relation_staff(int staff_id,int departmentid)throws RemoteException{
		DepartmentRelationStaff departmentRelationStaff=new DepartmentRelationStaff();
	//	DepartmentRelationStaffId departmentRelationStaffId=new DepartmentRelationStaffId();
		DepartmentRelationStaffDAO departmentRelationStaffDAO=new DepartmentRelationStaffDAO();
		
		departmentRelationStaff.setStaffId(staff_id);
		departmentRelationStaff.setDepartmentId(departmentid);
		departmentRelationStaff.setId(departmentRelationStaffDAO.getcount()+1);
		
		
		
		departmentRelationStaffDAO.update(departmentRelationStaff);
	}
	
	void addstaff(int staffid,String name,String telephone,String email,String position) throws RemoteException{
		EmployeeDAO b=new EmployeeDAO();
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis()); 
	//	if(checkusername())
		int max;
		//int id=assign();
		//max=b.getcount();
		UserDAO mUserDAO=new UserDAO();
		Employee a=new Employee(staffid,name,telephone,email,position,1,mUserDAO.getcount(),nowTimestamp,0);
	//    adduser();
	//	a.setEmail(email);a.setLevel(1);a.setName(name);a.setPosition(position);a.setTelephone(telephone);
		b.update(a);
		
	}
	
	void adduser(String username,String password){
		UserDAO ud = new UserDAO();
		User u=new User();
		
	//	UserId ui =new UserId();
		int userid=ud.getcount()+1;
		u.setUserId(userid);
		u.setPassword(password);
		u.setUsername(username);
	//	u.setUserId(ui);
		ud.update(u);
	}
	
	public int assign(int departmentid,int max){
		return 1000*departmentid+max+1;
	}
	
	
	public String login(String name,String password)throws RemoteException{
		
		EntityManagerHelper.log("start login", Level.INFO, null);
		 JSONObject ret_val = null;
		List<User> a =new ArrayList<User>();
		UserDAO b=new UserDAO();
		EmployeeDAO ed=new EmployeeDAO();
		EmployeeCopyDAO ecDao=new EmployeeCopyDAO();
		List<EmployeeCopy> ecList=new ArrayList<EmployeeCopy>();
		Employee employee;
		JSONObject tempJsonObject;
		a=b.findByProperty("username", name);
		if(a==null||a.size()==0)  //can't found username in user
		{
			EntityManagerHelper.log("login failed.tryto fin in copy", Level.INFO, null);
			ecList=ecDao.findByProperty("username", name);
			if(ecList==null||ecList.size()==0)
			{ //wrong username
				EntityManagerHelper.log("login failed: wrong password or username", Level.INFO, null);
				ret_val=new JSONObject();
				ret_val.put("ifsuccess",false);
				ret_val.put("err", 3);
				return ret_val.toString();
			}
			for(EmployeeCopy ec:ecList)//for exist user in employee copy
			{
				if(ec.getCheckStatus()==0)//if wait for pass
				{
					EntityManagerHelper.log("login failed: wait for pass", Level.INFO, null);
					ret_val=new JSONObject();
					ret_val.put("ifsuccess", false);
					ret_val.put("err", 1);
					return ret_val.toString();
				}
				
			}
			//if has been refused
			EntityManagerHelper.log("login failed: refuse pass", Level.INFO, null);
			ret_val=new JSONObject();
			ret_val.put("ifsuccess",false);
			ret_val.put("err", 2);
			return ret_val.toString();
		}
		else if(password.equals(a.get(0).getPassword())){
			employee=ed.findByUserid(a.get(0).getUserId()).get(0);
			if(employee.getState()==1)
			{
				EntityManagerHelper.log("login failed: frozen account", Level.INFO, null);
				ret_val=new JSONObject();
				ret_val.put("ifsuccess", false);
				ret_val.put("err", 0);
				return ret_val.toString();
			}
			tempJsonObject=JSONObject.fromObject(employee);
			tempJsonObject.put("username", name);
			tempJsonObject.put("password", password);
			tempJsonObject.put("ifsuccess", true);
			ret_val= tempJsonObject;
			EntityManagerHelper.log("login success", Level.INFO, null);
			return ret_val.toString();
		}
		else
		{ //wrong password
			EntityManagerHelper.log("login failed: wrong password or username", Level.INFO, null);
			ret_val=new JSONObject();
			ret_val.put("ifsuccess",false);
			ret_val.put("err", 3);
			return ret_val.toString();
		}
		
		
	}
	
	public String login(String jsonString) throws RemoteException{
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		String username=(String)tempJson.get("username");
		String password=(String)tempJson.get("password");
		
		return login(username,password);
	}
	
	
	public boolean add_department(String departmentname)throws RemoteException{
//		check
		if(checkdepartmentname(departmentname)){
		Department department=new Department();
		DepartmentDAO mDepartmentDAO=new DepartmentDAO();
		department.setDepartmentId(mDepartmentDAO.getcount()+1);
		department.setDepartmentName(departmentname);
		department.setDepartmentNum(0);
		mDepartmentDAO.update(department);
		EntityManagerHelper.log("add department success", Level.INFO, null);
		return true;
		}
		else{
			EntityManagerHelper.log("add department failure", Level.INFO, null);
			return false;
		}
	}
	
	boolean checkdepartmentname(String departmentname){
		DepartmentDAO mDepartmentDAO=new DepartmentDAO();
		if(mDepartmentDAO.findByDepartmentName(departmentname).size()>0)
			return false;
		return true;
	}
	
	public String show_department()throws RemoteException{
		List<Department> mDepartments=new ArrayList<Department>();
		DepartmentDAO mDepartmentDAO=new DepartmentDAO();
		JSONArray ret_val=new JSONArray();
		mDepartments=mDepartmentDAO.findAll();
		 for(int i=0;i<mDepartments.size();i++){
			 ret_val.add(JSONObject.fromObject(mDepartments.get(i)));
			 
			 //System.out.println(mDepartments.get(i).getDepartmentName());
		 }
		 return ret_val.toString();
	}
	
	public boolean modify_department(String jsonString)throws RemoteException{
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		int departmentid=(int)tempJson.get("departmentid");
		String modifyname=(String)tempJson.get("modifyname");
		return modify_department(departmentid, modifyname);
		
	}
	
	public boolean modify_department(int departmentid,String modifyname)throws RemoteException{
		if(checkdepartmentname(modifyname)){
		Department department=new Department();
		DepartmentDAO mDepartmentDAO=new DepartmentDAO();
		department=mDepartmentDAO.findById(departmentid);
		if(department==null){
			return false;
		}
		else
		{
		department.setDepartmentName(modifyname);
		mDepartmentDAO.update(department);
		return true;
		}
		}
		else{
			EntityManagerHelper.log("add department failure", Level.INFO, null);
			return false;
		}
	}
	
	public boolean delect_department(int departmentid)throws RemoteException{
		List<DepartmentRelationStaff> mRelationStaffs=new ArrayList<DepartmentRelationStaff>();
		DepartmentRelationStaffDAO mRelationStaffDAO =new DepartmentRelationStaffDAO();
		Department department=new Department();
		DepartmentDAO mDepartmentDAO=new DepartmentDAO();
		department=mDepartmentDAO.findById(departmentid);
		if(department==null){
			return false;
		}
		else{
			if(department.getDepartmentNum()==0){
		mRelationStaffs=mRelationStaffDAO.findByProperty("departmentId", departmentid);
		
		for(int i=0;i<mRelationStaffs.size();i++){
		//	deletestaff(mRelationStaffs.get(i).getId().getStaffId());
			mRelationStaffDAO.delete(mRelationStaffs.get(i));	
		}
		mDepartmentDAO.delete(department);
		return true;
			}
			else{
				return false;
			}
		}
		
	}
	@Deprecated
	public String show_personal_meeting(int staffid)throws RemoteException{
		JSONArray ret_val=new JSONArray();
		List<MeetingRelationStaff> mRelationStaff=new ArrayList<MeetingRelationStaff>();
		
		MeetingRelationStaffDAO meetingRelationStaffDAO=new MeetingRelationStaffDAO();
		
		Meeting tempMeeting;
		MeetingDAO mDao=new MeetingDAO();
		
		MeetingRoom tempMeetingRoom;
		MeetingRoomDAO mrd=new MeetingRoomDAO();
				
		mRelationStaff=meetingRelationStaffDAO.findByProperty("staffId",staffid);
		String tmp=null;
        for(int i=0;i<mRelationStaff.size();i++){
        	
        	tempMeeting=mDao.findById(mRelationStaff.get(i).getMeetingId());
        	tmp=JSONObject.fromObject(tempMeeting).toString();
        	
        	tempMeetingRoom=mrd.findById(tempMeeting.getMeetingroomId());
        	tmp+=JSONObject.fromObject(tempMeetingRoom).toString();
        	
        	ret_val.add(JSONObject.fromObject(tmp) );
        	//System.out.println(mRelationStaff.get(i).getStaffId());
        	//System.out.println(mRelationStaff.get(i).getMeetingId());
        }
        return ret_val.toString();
	}
	@Deprecated
	public String show_personal_book_meeting(int staffid,String username)throws RemoteException{
		JSONArray ret_val=new JSONArray();
		List<MeetingRelationStaff> mRelationStaff=new ArrayList<MeetingRelationStaff>();
		
		MeetingRelationStaffDAO meetingRelationStaffDAO=new MeetingRelationStaffDAO();
		
		Meeting tempMeeting;
		MeetingDAO mDao=new MeetingDAO();
		
		MeetingRoom tempMeetingRoom;
		MeetingRoomDAO mrd=new MeetingRoomDAO();
				
		mRelationStaff=meetingRelationStaffDAO.findByProperty("staffId",staffid);
		String tmp=null;
        for(int i=0;i<mRelationStaff.size();i++){
        //	if(username==mDao.findById(mRelationStaff.get(i).getMeetingId()))
        	tempMeeting=mDao.findById(mRelationStaff.get(i).getMeetingId());
        	if(tempMeeting.getBookName()==username)
        	{
        	tmp=JSONObject.fromObject(tempMeeting).toString();
        	
        	tempMeetingRoom=mrd.findById(tempMeeting.getMeetingroomId());
        	tmp+=JSONObject.fromObject(tempMeetingRoom).toString();
        	
        	ret_val.add(JSONObject.fromObject(tmp) );
        	}
        	//System.out.println(mRelationStaff.get(i).getStaffId());
        	//System.out.println(mRelationStaff.get(i).getMeetingId());
        }
        return ret_val.toString();
	}
	
	
	public boolean add_meeting_room(String jString)throws RemoteException{
		String meetingroom_name,room_number,remark,atate;
		int capacity;
		JSONObject tempJson=JSONObject.fromObject(jString);
		meetingroom_name=(String)tempJson.get("meetingroomname");
		room_number=(String)tempJson.get("roomnumber");
		remark=(String)tempJson.get("remark");
		atate=(String)tempJson.get("state");
		capacity=(int)tempJson.get("capacity");
		return add_meeting_room(meetingroom_name, capacity, room_number, remark, atate);
	
	}
	public boolean add_meeting_room(String meetingroom_name,int capacity,String room_numbler,String remark,String atate)throws RemoteException{
		MeetingRoom mRoom=new MeetingRoom();
		MeetingRoomDAO mRoomDAO=new MeetingRoomDAO();
	
		mRoom.setRoomNumbler(room_numbler);
		mRoom.setCapacity((long)capacity);
		mRoom.setMeetingRoomName(meetingroom_name);
		mRoom.setRemark(remark);
		mRoom.setCurrentAtate(atate);
		mRoom.setMeetingRoomId(mRoomDAO.getcount()+1);
		mRoomDAO.update(mRoom);
		
		return true;
	}
	
	public boolean delete_meeting_room(int meetingroom_id)throws RemoteException{
		MeetingRoom mRoom=new MeetingRoom();
		MeetingRoomDAO mRoomDAO=new MeetingRoomDAO();
		mRoom=mRoomDAO.findById(meetingroom_id);
		if(mRoom==null){return false;}
		else{
		mRoomDAO.delete(mRoom);
		return true;
		}
	}
	
	
	
	public String show_allmeetingroom()throws RemoteException{
		JSONArray ret_val=new JSONArray();
		MeetingRoomDAO mRoomDAO=new MeetingRoomDAO();
		List<MeetingRoom> meetingRooms=new ArrayList<MeetingRoom>();
		meetingRooms=mRoomDAO.findAll();
		if(meetingRooms.size()==0){
			System.out.println("search error");
		}
		else{
	        for(int i=0;i<meetingRooms.size();i++){ 
	        	ret_val.add(JSONObject.fromObject(meetingRooms.get(i)));
	        }	
		}
		return ret_val.toString();
	}
	
	public String show_meetingroom(int meetingroomid)throws RemoteException{
		MeetingRoom mRoom=new MeetingRoom();
		MeetingRoomDAO mRoomDAO=new MeetingRoomDAO();
		mRoom=mRoomDAO.findById(meetingroomid);
		if(mRoom==null){
			System.out.println("search error");
			return "";
		}
		else{
			return JSONObject.fromObject(mRoom).toString();
		}
		
	}
	
	public boolean modify_meeting_room(String jsonString)throws RemoteException{
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		int meetingroomid,capacity;
		String meetingroom_name, room_numbler,remark,atate;
		
		meetingroomid=(int)tempJson.get("meetingroomid");
		capacity=(int)tempJson.get("capacity");
		meetingroom_name=(String)tempJson.get("meetingroomname");
		room_numbler=(String)tempJson.get("roomnumber");
		remark=(String)tempJson.get("remark");
		atate=(String)tempJson.get("state");
		return modify_meeting_room(meetingroomid, meetingroom_name, capacity, room_numbler, remark, atate);
	}
	
	public boolean modify_meeting_room(int meetingroomid,String meetingroom_name,int capacity,String room_numbler,String remark,String atate)throws RemoteException{
		MeetingRoom mRoom=new MeetingRoom();
		MeetingRoomDAO mRoomDAO=new MeetingRoomDAO();
		mRoom=mRoomDAO.findById(meetingroomid);
		if(mRoom==null){
			System.out.println("search error");
			return false;
		}
		else{	
			mRoom.setRoomNumbler(room_numbler);
			mRoom.setCapacity((long)capacity);
			mRoom.setMeetingRoomName(meetingroom_name);
			mRoom.setRemark(remark);
			mRoom.setCurrentAtate(atate);
			mRoomDAO.update(mRoom);
			return true;
		}
	}
	
	
	
	String checktimeconflict(Timestamp starttime,Timestamp endtime,int meetingroom_id){
		
		JSONArray ret_val=new JSONArray();
		JSONObject tempJson;
		if(starttime.getTime()>endtime.getTime())
		{
			//ret_val.add()
			//return ret_val.toString();
		}
		
		Meeting nowMeeting=new Meeting();
		nowMeeting.setStartTime(starttime);
		nowMeeting.setEndTime(endtime);
		
		List<Meeting> meetings=new ArrayList<Meeting>();
		MeetingDAO meetingDAO=new MeetingDAO();
		meetings=meetingDAO.findByMeetingroomId(meetingroom_id);
		meetings.add(nowMeeting);
		//List<Timestamp> starttimes=new ArrayList<Timestamp>();
		//List<Timestamp> endtimes=new ArrayList<Timestamp>();
		//Pair<Timestamp, Timestamp> mPair=new Pair<Timestamp, Timestamp>(key, value)
		
		meetings.sort(new Comparator<Meeting>(){
			@Override
			public int compare(Meeting o1, Meeting o2) {
				long s1time=o1.getStartTime().getTime();
				long s2time=o2.getStartTime().getTime();
				if(s1time<s2time)return -1;
				else if(s1time>s2time)return 1;
				
				return 0;
			}
		});
		long lastendtime=Long.MIN_VALUE;
		for(Meeting m:meetings)
		{
			if(m.getStartTime().getTime()<lastendtime)
			{
				tempJson=new JSONObject();
				tempJson.put("meetingname", m.getMeetingName());
				tempJson.put("starttime", m.getStartTime().toString());
				tempJson.put("endtime", m.getEndTime().toString());
				//return false;
				ret_val.add(tempJson);
			}
			lastendtime=m.getEndTime().getTime();
		}
		
		return ret_val.toString();
		//return true;
	}
	
	public String add_meeting(String jsonString)throws RemoteException{
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		JSONArray tempJsonStaff;
		String meetingname,meeting_notes;
		int meeting_num,meetingroom_id,bookid;
		String sTime,eTime;
		List<Integer>staffid=new ArrayList<Integer>();
		List<String> staffidstrList= new ArrayList<String>() ;
		EntityManagerHelper.log("add meeting ", Level.INFO, null);
		
		bookid=(int)tempJson.get("bookname");
		meetingname=(String)tempJson.get("meetingname");
		meeting_notes=(String)tempJson.get("meetingnotes");
		meeting_num=(int)tempJson.get("meetingnum");
		meetingroom_id=(int)tempJson.getInt("meetingroomid");
		sTime=(String)tempJson.get("starttime");
		eTime=(String)tempJson.get("endtime");
		tempJsonStaff=(JSONArray)tempJson.get("staffid");
		
		try {
			staffidstrList=JSONArray.toList(tempJsonStaff);
			for(int i=0;i<staffidstrList.size();i++)
			{
				staffid.add(Integer.parseInt(staffidstrList.get(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return add_meeting(bookid, meetingname, meeting_num, meeting_notes, meetingroom_id, sTime, eTime, staffid);
	}
	
	public String add_meeting(int bookid,String meetingname,int meeting_num,String meeting_notes,int meetingroom_id,
	String sTime, String eTime,List<Integer>staffid)throws RemoteException{
		
		Timestamp startTime=convertDate(sTime);
		Timestamp endTime=convertDate(eTime);
		
		UserDAO uDao=new UserDAO();
		User tempUser=null;
		String bookname = null;
		EmployeeDAO eDao=new EmployeeDAO();
		List<Employee> tempe=null;
		Meeting meeting=new Meeting();
		MeetingDAO meetingDAO= new MeetingDAO();
		MeetingRelationStaff mRelationStaff=new MeetingRelationStaff();
		MeetingRelationStaffDAO mRelationStaffDAO=new MeetingRelationStaffDAO();
	//	MeetingRelationStaffId mRelationStaffId=new MeetingRelationStaffId();
		JSONObject retJson = new JSONObject(),tempJson;
		JSONArray mistakeListArray=new JSONArray();
		JSONArray checkJson;
		tempUser=uDao.findById(bookid);
		if(tempUser==null)
		{
			EntityManagerHelper.log("add meeting failure: cannot find book name", Level.INFO, null);
			tempJson=new JSONObject();
			tempJson.put("errortype", "0");
			tempJson.put("errormsg", "add meeting failure: cannot find book name");
			mistakeListArray.add(tempJson);
			retJson.put("ifsuccess", false);
			
			//return false;
		}
		
		tempe=eDao.findByUserid(tempUser.getUserId());
		if(tempe.size()>1)
		{
			EntityManagerHelper.log("add meeting failure: the book name have several staff id", Level.INFO, null);
			tempJson=new JSONObject();
			tempJson.put("errortype", "0");
			tempJson.put("errormsg", "add meeting failure: the book name have several staff id");
			mistakeListArray.add(tempJson);
			
			retJson.put("ifsuccess", false);
			//return false;
		}
		else if(tempe.size()<1)
		{
			EntityManagerHelper.log("add meeting failure: the book name have no staff id", Level.INFO, null);
			tempJson=new JSONObject();
			tempJson.put("errortype", "0");
			tempJson.put("errormsg", "add meeting failure: the book name have no staff id");
			mistakeListArray.add(tempJson);
			
			retJson.put("ifsuccess", false);
			//return false;
		}
		bookname=tempe.get(0).getName();
		staffid.add(tempe.get(0).getStaffId());
		checkJson=new JSONArray();
		checkJson=JSONArray.fromObject(checktimeconflict(startTime,endTime,meetingroom_id));
		if(checkJson.size()>0){
			EntityManagerHelper.log("add meeting failure: meeting date conflict", Level.INFO, null);
			
			tempJson=new JSONObject();
			tempJson.put("errortype", "1");
			tempJson.put("errormsg", "add meeting failure: meeting date conflict");
			tempJson.put("conflictdata", checkJson);
			mistakeListArray.add(tempJson);
			retJson.put("ifsuccess", false);
			//return false;
		}
		checkJson=new JSONArray();
		checkJson=JSONArray.fromObject(checkStaffMeetingConflict(startTime,endTime,staffid));
		if(checkJson.size()>0){
			EntityManagerHelper.log("add meeting failure: participant's date conflict", Level.INFO, null);
			
			tempJson=new JSONObject();
			tempJson.put("errortype", "2");
			tempJson.put("errormsg", "add meeting failure: participant's date conflict");
			tempJson.put("conflictdata", checkJson);
			mistakeListArray.add(tempJson);
			retJson.put("ifsuccess", false);
			//return false;
		}
		
		
		int meetingid=meetingDAO.getcount()+1;
		
		if(staffid.size()==0){
			EntityManagerHelper.log("add meeting failure no staff included.", Level.INFO, null);
			tempJson=new JSONObject();
			tempJson.put("errortype", "3");
			tempJson.put("errormsg", "add meeting failure no staff included");
			mistakeListArray.add(tempJson);
			retJson.put("ifsuccess", false);
			//return false;
		}
		else
		{
			
			if(retJson.has("ifsuccess")==false)
			{
				meeting.setMeetingName(meetingname);
				meeting.setMeetingNotes(meeting_notes);
				meeting.setMeetingroomId(meetingroom_id);
				meeting.setPeopleNum(meeting_num);
				meeting.setStartTime(startTime);
				meeting.setEndTime(endTime);
				meeting.setMeetingId(meetingid);
				meeting.setBookName(tempUser.getUsername());
				Timestamp tempTimestamp= new Timestamp(System.currentTimeMillis()); 
				meeting.setBookTime(tempTimestamp);
				meeting.setStatus(0);
				//System.out.println(meeting.getMeetingId());
				meetingDAO.update(meeting);
					
				for(int i=0;i<staffid.size();i++){
					mRelationStaff.setId(mRelationStaffDAO.getcount()+1);	
					
					//System.out.println(mRelationStaffDAO.getcount());
					
					mRelationStaff.setStaffId(staffid.get(i));
				//	System.out.println(staffid.get(i));
					
					mRelationStaff.setMeetingId(meetingid);
					//mRelationStaff.setId(mRelationStaffId);
					//System.out.println(mRelationStaff.getId().getId());
					mRelationStaffDAO.update(mRelationStaff);
				}
				EntityManagerHelper.log("add meeting success", Level.INFO, null);
				retJson.put("ifsuccess", true);
			}else {
				EntityManagerHelper.log("add meeting fail", Level.INFO, null);
				retJson.put("faillist", mistakeListArray);
			}
			//return true;
		}
		return retJson.toString();
	}
	
	
	
	String checkStaffMeetingConflict(Timestamp startTime, Timestamp endTime,List<Integer>staffid)
	{
		JSONArray ret_val=new JSONArray();
		JSONObject tempJson;
		
		MeetingRelationStaffDAO mrsDao=new MeetingRelationStaffDAO();
		MeetingDAO mDao=new MeetingDAO();
		List<MeetingRelationStaff> mrsList=new ArrayList<MeetingRelationStaff>();
		List<Meeting> meetingList=new ArrayList<Meeting>();
		Meeting nowMeeting=new Meeting();
		Set<Integer> meetingSet=new HashSet<Integer>() ;
		
		Long startT=startTime.getTime(),endT=endTime.getTime();
		//nowMeeting.setStartTime(startTime);
		//nowMeeting.setEndTime(endTime);
		//meetingList.add(nowMeeting);
		for(int i:staffid)
		{
			mrsList=mrsDao.findBystaffid(i);
			for(MeetingRelationStaff mrs:mrsList)
			{
				meetingSet.add(mrs.getMeetingId());
			}
		}
		for(int i:meetingSet)
		{
			meetingList.add(mDao.findById(i));
		}
		
		meetingList.sort(new Comparator<Meeting>(){
			@Override
			public int compare(Meeting o1, Meeting o2) {
				long s1time=o1.getStartTime().getTime();
				long s2time=o2.getStartTime().getTime();
				if(s1time<s2time)return -1;
				else if(s1time>s2time)return 1;
				
				return 0;
			}
		});
	
		int i=0;
		Meeting tempm;
		//System.out.println("end time:"+endT);
		for(;i<meetingList.size();i++)
		{
			
			tempm=meetingList.get(i);
			//System.out.println("s:"+tempm.getStartTime().getTime()+" e:"+tempm.getEndTime().getTime());
			if(tempm.getStartTime().getTime()>=endT)
			{
				break;
			}
		}
		if(i>=meetingList.size())
		{
			//System.out.println("no conflict");
			i=meetingList.size()-1;
		}
		
		//System.out.println("may have conflict");
			
		for(;i>=0;i--)
		{
			if(meetingList.get(i).getEndTime().getTime()>startT)
			{
				//need to delete staff from emplist who joined this meeting
				Meeting goalMeeting=meetingList.get(i);
				List<MeetingRelationStaff> tempmrsList;
				//System.out.println("people in meeting "+goalMeeting.getMeetingName()+" have conflict");
				tempmrsList=mrsDao.findByProperty("meetingId", goalMeeting.getMeetingId());
				for(MeetingRelationStaff mrs:tempmrsList)
				{
					
					//mrs.getStaffId();
					//can_use_employeeMap.remove(mrs.getStaffId());
					tempJson=new JSONObject();
					tempJson.put("meetingname", goalMeeting.getMeetingName());
					tempJson.put("starttime",goalMeeting.getStartTime().toString());
					tempJson.put("endtime", goalMeeting.getEndTime().toString());
					//return false;
					ret_val.add(tempJson);
				}
					
			}
		}
		
		
		
//		int i=0;
//		long lastendtime=Long.MIN_VALUE;
//		for(Meeting m:meetingList)
//		{
//			System.out.println("s:"+m.getStartTime().getTime()+" e:"+m.getEndTime().getTime());
//			if(m.getStartTime().getTime()<lastendtime)
//			{
//				tempJson=new JSONObject();
//				tempJson.put("meetingname", m.getMeetingName());
//				tempJson.put("starttime", m.getStartTime().toString());
//				tempJson.put("endtime", m.getEndTime().toString());
//				//return false;
//				ret_val.add(tempJson);
//			}
//			lastendtime=m.getEndTime().getTime();
//		}
		
		return ret_val.toString();
	}
	
	

    public String searchstaffbyname(String staff_name)throws RemoteException
    {
    	List<Employee> employee=new ArrayList<Employee>();
    	EmployeeDAO employeeDAO=new EmployeeDAO();
    	JSONArray ret_val=new JSONArray();
    	employee=employeeDAO.findByName(staff_name);
    	if(employee.size()==0){
    		return "";
    	}
    	else
    	{
    		for(Employee e:employee)
    		{
    			ret_val.add(JSONObject.fromObject(e));
    		}
    		return ret_val.toString();
    	}
    }
    
    //JSONArray
    public String searchstaffbydepartment(int departmentid)throws RemoteException
    {
    	List<DepartmentRelationStaff> drsList=new ArrayList<DepartmentRelationStaff>();
    	DepartmentRelationStaffDAO drsDAO= new DepartmentRelationStaffDAO();
    	EmployeeDAO ed=new EmployeeDAO();
    	JSONArray ret_val = new JSONArray();
    	
    	drsList=drsDAO.findByProperty("departmentId", departmentid);
    	
    			
    	if(drsList.size()==0){
    		EntityManagerHelper.log("search staff by department empty.", Level.INFO, null);
    		return "";
    	}
    	else
    	{
    		for(DepartmentRelationStaff d:drsList)
    		{
    			ret_val.add(JSONObject.fromObject(ed.findById(d.getStaffId())));
    		}
    		EntityManagerHelper.log("search staff by department with "+drsList.size()+" information.", Level.INFO, null);
    		return ret_val.toString();
    	}
    }
    
    public String searchstaff(String jsonString)throws RemoteException
    {
    	String name="",username="";
    	int state=-1;
    	JSONObject tempJson=JSONObject.fromObject(jsonString);
    	JSONArray ret_val=new JSONArray();
    	
    	name=(String)tempJson.get("name");
    	username=(String)tempJson.get("username");
    	state=(int)tempJson.get("state");
    	
    	List<Employee> empList=new ArrayList<Employee>();
    	//List<EmployeeCopy> empCopyList=new ArrayList<EmployeeCopy>();
    	EmployeeDAO empDao=new EmployeeDAO();
    	EmployeeCopyDAO empCopyDAO=new EmployeeCopyDAO();
    	User tempUser=null;
    	UserDAO uDao=new UserDAO();
    	JSONObject nowJson=new JSONObject();
    	
    	String output="multi search staff name:"+name;
    	output+=" username:"+username;
    	output+=" state:"+(state);
    	//EntityManagerHelper.log(output, Level.INFO, null);
		
    	
    	
    	if(state==1||state==2)//in employ status=0
    	{
   
    		try{
    			empList=empDao.findByMultiProperty(name, username,state-1);
    		}catch (Exception e)
    		{
    			e.printStackTrace();
    		}
    		for(Employee ec:empList)
    		{
    			tempUser=null;
    			tempUser=uDao.findById(ec.getUserid());
    			if(tempUser==null)continue;
    			nowJson=JSONObject.fromObject(ec);
    			nowJson.put("username", tempUser.getUsername());
    			ret_val.add(nowJson);
    		}
    		
    		//empCopyList=empCopyDAO.findByMultiProperty(name, username,state);
    		//for(EmployeeCopy e:empCopyList)
    		//{
    		//	ret_val.add(JSONObject.fromObject(e));
    		//}
    		
    	}
    	else {				//in both employee and employee
			//empCopyList=empCopyDAO.findByMultiProperty(name, username,state);
    		//for(EmployeeCopy e:empCopyList)
    		//{
    		//	ret_val.add(JSONObject.fromObject(e));
    		//}
    		try{
    			empList=empDao.findByMultiProperty(name, username,state-1);
    		}catch (Exception e)
    		{
    			e.printStackTrace();
    		}
    		
    		for(Employee ec:empList)
    		{
    			tempUser=null;
    			tempUser=uDao.findById(ec.getUserid());
    			if(tempUser==null)continue;
    			nowJson=JSONObject.fromObject(ec);
    			nowJson.put("username", tempUser.getUsername());
    			ret_val.add(nowJson);
    		}
		}
    	EntityManagerHelper.log("staff multisearch done with member:"+ret_val.size(), Level.INFO, null);
    	return  ret_val.toString();
    }
    
    
  //JSONArray
    public String searchstaffbydepartment()throws RemoteException
    {
    	List<DepartmentRelationStaff> drsList=new ArrayList<DepartmentRelationStaff>();
    	DepartmentRelationStaffDAO drsDAO= new DepartmentRelationStaffDAO();
    	EmployeeDAO ed=new EmployeeDAO();
    	List<Employee> empList=new ArrayList<Employee>();
    	JSONArray ret_val = new JSONArray();
    	JSONArray tempVAl=new JSONArray();
    	JSONObject tempJson;
    	List<Department> mDepartments=new ArrayList<Department>();
		DepartmentDAO mDepartmentDAO=new DepartmentDAO();
		mDepartments=mDepartmentDAO.findAll();
		String tempString=new String();
    	
		for(Department depart:mDepartments)
		{
			tempString=JSONObject.fromObject(depart).toString();
			
			drsList=drsDAO.findByProperty("departmentId", depart.getDepartmentId());
			
			if(drsList.size()==0){
	    	//	EntityManagerHelper.log("search staff by department empty.", Level.INFO, null);
	    		
	    	}
	    	else
	    	{
	    		tempVAl=new JSONArray();
	    		for(DepartmentRelationStaff d:drsList)
	    		{
	    			//empList.add(ed.findById(d.getStaffId()));
	    			tempVAl.add(ed.findById(d.getStaffId()));
	    		}
	    		//EntityManagerHelper.log("search staff by department id: "+depart.getDepartmentId()+" information.", Level.INFO, null);
	    		
	    		//tempString+=tempVAl.toString();
	    		//empList.clear();
	    		tempJson=new JSONObject();
	    		tempJson.put("stafflist", tempVAl);
	    		tempJson.put("department", depart);
	    		ret_val.add(tempJson);
	    	}
			
		}
    	//System.out.println(ret_val.toString());
    	return ret_val.toString();
 
    }
    
    
    public String searchstaffbyusername(String username)throws RemoteException{
    	List<Employee> employee=new ArrayList<Employee>();
    	EmployeeDAO employeeDAO=new EmployeeDAO();
    	UserDAO uDao=new UserDAO();
    	List<User> user=new ArrayList<User>();
    	JSONObject ret_val=new JSONObject();
    	user=uDao.findByProperty("username", username);
    	if(user.size()>0){
    	employee=employeeDAO.findByUserid(uDao.findByProperty(username, username).get(0).getUserId());
    	if(employee.size()==0){
    		return "";
    	}
    	else
    	{   	
    		ret_val=JSONObject.fromObject(employee.get(0));
    		return ret_val.toString();
    	} 
    	}
    	else {
			return "";
		}
    }
    
    
    
    //multiple
    public String searchMeeting(String jsonString)throws RemoteException
    {
    	String meeting_name,meeting_notes,book_name;
		int people_num=-1,meetingroom_id=-1,status=-1;
		String sTime,eTime,bTime;
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		
		EntityManagerHelper.log("start search meeting", Level.INFO, null);
		meeting_name=(String)tempJson.get("meetingname");
		meeting_notes=(String)tempJson.get("meetingnotes");
		book_name=(String)tempJson.get("bookname");
		if(tempJson.get("peoplenum")!=null)people_num=(int)tempJson.get("peoplenum");
		if(tempJson.get("meetingroomid")!=null)meetingroom_id=(int)tempJson.get("meetingroomid");
		if(tempJson.get("status")!=null)status=(int)tempJson.get("status");
		sTime=(String)tempJson.get("starttime");
		eTime=(String)tempJson.get("endtime");
		bTime=(String)tempJson.get("booktime");
		System.out.println(book_name);
		return searchMeeting(meeting_name, meeting_notes, book_name, people_num, meetingroom_id, status, sTime, eTime,bTime);
    }
    
    //multiple
    @Deprecated
    public String searchMeeting(Map<String, Object> map)throws RemoteException
    {
    	String meeting_name=null,meeting_notes=null, book_name=null;
		int people_num=-1, meetingroom_id=-1, status=-1;
		String start_time, end_time , book_time = null;
    	
		meeting_name=map.get("meeting_name")==null?null:(String)map.get("meeting_name");
		meeting_notes=map.get("meeting_notes")==null?null:(String)map.get("meeting_notes");
		book_name=map.get("book_name")==null?null:(String)map.get("book_name");
		
		if(map.get("start_time")==null)
		{
			start_time="";
		}else {
			start_time=(String)map.get("start_time");
		}
		if(map.get("end_time")==null)
		{
			end_time="";
		}else {
			end_time=(String)map.get("end_time");
		}
		if(map.get("book_time")==null)
		{
			book_time="";
		}else {
			book_time=(String)map.get("book_time");
		}
		
		
		people_num=map.get("people_num")==null?-1:(int)map.get("people_num");
		meetingroom_id=map.get("meetingroom_id")==null?-1:(int)map.get("meetingroom_id");
		status=map.get("status")==null?-1:(int)map.get("status");
		return searchMeeting(meeting_name, meeting_notes, book_name, 
				people_num, meetingroom_id, status, 
				start_time, end_time, book_time);
    }
    
    //multiple
    public String searchMeeting(String meeting_name,String meeting_notes,String book_name,
    		int people_num,int meetingroom_id,int status,
    		String sTime,String eTime,String bTime)throws RemoteException
    {
    	
    	Timestamp start_time=null;
    	Timestamp end_time=null;
    	Timestamp book_time=null;
    	List<Meeting> meetings=new ArrayList<Meeting>();
    	MeetingDAO meetingDAO=new MeetingDAO();
    	Meeting goalmeeting=new Meeting();
    	JSONArray ret_val= new JSONArray();
    	if(meeting_notes!=null&&meeting_notes.length()>0){
    		meeting_notes="%"+meeting_notes;
    		meeting_notes+="%";	
    	}
    	if(sTime!=null&&sTime.length()>0)start_time=convertDate(sTime);
    	if(eTime!=null&&eTime.length()>0)end_time=convertDate(eTime);
    	if(bTime!=null&&bTime.length()>0)book_time=convertDate(bTime);
    	try {
			meetings=meetingDAO.findByMultiProperty(meeting_name,people_num,start_time,end_time,book_time,meeting_notes,meetingroom_id,status,book_name);
    	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	JSONObject tempJsonObject;
    	
    	MeetingRoomDAO mrDao=new MeetingRoomDAO();
    	MeetingRoom tempmr=null;
    	if(meetings.size()>0)
    	{
    		//sort meeting 
    		meetings.sort(new Comparator<Meeting>() {
				@Override
				public int compare(Meeting o1, Meeting o2) {
					long s1time=o1.getStartTime().getTime();
					long s2time=o2.getStartTime().getTime();
					if(o1.getStatus()<o2.getStatus()){
						return -1;
					}else if(o1.getStatus()>o2.getStatus()){
						return 1;
					}else{
						if(s1time<s2time)return -1;
						else if(s1time>s2time)return 1;
						return 0;
					}
					
				}
			});
    		
    		
    		
    		System.out.println(meetings.get(0).getMeetingName()+" "+meetings.get(0).getBookName());
    		for(Meeting m:meetings)
    		{
    			tempJsonObject=JSONObject.fromObject(m);
    			tempJsonObject.put("starttime", deconvertDate(m.getStartTime()));
    			tempJsonObject.put("endtime", deconvertDate(m.getEndTime()));
    			if(m.getStatus()==0)tempJsonObject.put("status", "Õý³£");
    			else tempJsonObject.put("status", "ÒÑÈ¡Ïû");
    			if(m.getBookTime()!=null)tempJsonObject.put("booktime", deconvertDate(m.getBookTime()));
    			tempmr=mrDao.findById(m.getMeetingroomId());
    			if(tempmr!=null)
    			{
    				tempJsonObject.put("meetingroomname",tempmr.getMeetingRoomName());
    			}
    			tempJsonObject.put("data", searchstaff_by_meetingid(m.getMeetingId()));
    			ret_val.add(tempJsonObject);
    		}
    	}
    	EntityManagerHelper.log("finished search meeting", Level.INFO, null);
    	return ret_val.toString();
    	
    }
    
    
    
    JSONArray searchstaff_by_meetingid(int meetingid)
    {
    	JSONArray ret_val=new JSONArray();
    	MeetingRelationStaffDAO msrDao=new MeetingRelationStaffDAO();
    	List<MeetingRelationStaff> msrList=new ArrayList<MeetingRelationStaff>();
    	EmployeeDAO eDao=new EmployeeDAO();
    	Employee tempe=null;
    	msrList=msrDao.findByProperty("meetingId", meetingid);
    	int tempstaffid;
    	for(MeetingRelationStaff msr:msrList)
    	{
    		tempstaffid=msr.getStaffId();
    		tempe=eDao.findById(tempstaffid);
    		if(tempe!=null)
    		{
    			ret_val.add(JSONObject.fromObject(tempe));
    		}
    	}
		return ret_val;
    	
    }
    
    String deconvertDate(Timestamp tsa)
    {
    	
    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date a = new Date();
    	a=tsa;
    	Date tempDate = new Date();
    	String newDateString = null;
		try {
			tempDate = sdf.parse(a.toString());
			((SimpleDateFormat) sdf).applyPattern("yyyy/MM/dd HH:mm:ss");
			newDateString=sdf.format(tempDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
    	return newDateString;
    }
    
    Timestamp convertDate(String a)
    {
    	DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date tempDate = new Date();
    	String newDateString = null;
		try {
			tempDate = sdf.parse(a);
			((SimpleDateFormat) sdf).applyPattern("yyyy-MM-dd HH:mm:ss");
			newDateString=sdf.format(tempDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
    	Timestamp timestamp=Timestamp.valueOf(newDateString);
    	return timestamp;
    }
	@Override
	public String search_meeting_room_by_name(String name) {
		
		JSONArray ret_val=new JSONArray();
		MeetingRoomDAO mrd=new MeetingRoomDAO();
		List<MeetingRoom> mrList=new ArrayList<MeetingRoom>();
		
		mrList=mrd.findByMeetingRoomName(name);
		for(MeetingRoom mr:mrList)
		{
			ret_val.add(JSONObject.fromObject(mr));
		}
		return ret_val.toString();
	}
	@Override
	public String search_meeting_room_by_number(String number) {
		
		JSONArray ret_val=new JSONArray();
		MeetingRoomDAO mrd=new MeetingRoomDAO();
		List<MeetingRoom> mrList=new ArrayList<MeetingRoom>();
		
		EntityManagerHelper.log("search_meeting_room_by_number."+number, Level.INFO, null);
		
		mrList=mrd.findByRoomNumbler(number);
		for(MeetingRoom mr:mrList)
		{
			ret_val.add(JSONObject.fromObject(mr));
		}
		
		return ret_val.toString();
		
	}
	@Override
	public boolean reverse_staff(int staffid) throws RemoteException {
		
		EmployeeDAO eDao=new EmployeeDAO();
		Employee tempee=null;
		
		tempee=eDao.findById(staffid);
		if(tempee==null)return false;
		else {
			tempee.setState(1-tempee.getState());
		}
		eDao.update(tempee);
		return true;
	}
	
	public String search_avaliable_meetingroom(String json)throws RemoteException
	{
		JSONObject tempJsonObject=JSONObject.fromObject(json);
		String starttime=(String)tempJsonObject.get("starttime");
		String endtime=(String)tempJsonObject.get("endtime");
		int nownumber=(int)tempJsonObject.get("nownumber");
		return search_avaliable_meetingroom(starttime, endtime, nownumber);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String search_avaliable_meetingroom(String starttime,String endtime,int nownumber) throws RemoteException {

		Timestamp startTimestamp=convertDate(starttime);
		Timestamp endTimestamp=convertDate(endtime);
		
		String rawMeetingRoomString=show_allmeetingroom();
		
		
		MeetingDAO mDao=new MeetingDAO();
		
		List<MeetingRoom> mrList=new ArrayList<MeetingRoom>();
		List<Meeting>	mList=new ArrayList<Meeting>();
		boolean add_this_mr=true;
		Meeting tempMeeting=new Meeting();
		tempMeeting.setStartTime(startTimestamp);
		tempMeeting.setEndTime(endTimestamp);
		tempMeeting.setStatus(0);
		JSONArray raw_val=JSONArray.fromObject(rawMeetingRoomString);
		JSONArray ret_val=new JSONArray();
		mrList=JSONArray.toList(raw_val,MeetingRoom.class);
		
		for(MeetingRoom mr:mrList)
		{
			add_this_mr=true;
			mList=mDao.findByMeetingroomId(mr.getMeetingRoomId());
			
			mList.add(tempMeeting);
			mList.sort(new Comparator<Meeting>() {
				@Override
				public int compare(Meeting o1, Meeting o2) {
					long s1time=o1.getStartTime().getTime();
					long s2time=o2.getStartTime().getTime();
					if(s1time<s2time)return -1;
					else if(s1time>s2time)return 1;

					return 0;
				}
			});
			long lastendtime=Long.MIN_VALUE;
			for(Meeting m:mList)
			{
				if(m.getStartTime().getTime()<lastendtime&&m.getStatus()==0)
				{
					add_this_mr=false;
				}
				lastendtime=m.getEndTime().getTime();
			}
			if(add_this_mr==true)
			{
				if(mr.getCurrentAtate().equals("ÆôÓÃ")&&mr.getCapacity()>=nownumber)
				{
					ret_val.add(JSONObject.fromObject(mr));
				}
				
			}
		}
		
		return ret_val.toString();
	}
	
	public String search_avaliable_staff(String json)throws RemoteException
	{
		JSONObject tempJsonObject=JSONObject.fromObject(json);
		String starttime=(String)tempJsonObject.get("starttime");
		String endtime=(String)tempJsonObject.get("endtime");

		return search_avaliable_staff(starttime, endtime);
	}
	
	@Override
	public String search_avaliable_staff(String starttime,String endtime) throws RemoteException {

		Timestamp startTimestamp=convertDate(starttime);
		Timestamp endTimestamp=convertDate(endtime);
		long endT=endTimestamp.getTime();
		long startT=startTimestamp.getTime();
		MeetingDAO mDao=new MeetingDAO();
		EmployeeDAO eDao=new EmployeeDAO();
		MeetingRelationStaffDAO mrsDao=new MeetingRelationStaffDAO();
		//get all meeting
		List<Meeting> mList=mDao.findByStatus(0);
		//get all normal staff
		List<Employee> empList=eDao.findByStatus(0);
		Map<Integer, Employee> can_use_employeeMap=new HashMap<Integer, Employee>();
		
		JSONArray ret_val=new JSONArray();
		
		//put all staff in map,then can we can use this map to search employee by staff id
		for(Employee e:empList)
		{
			can_use_employeeMap.put(e.getStaffId(), e);
		}
		
		//sort all meeting by start time
		mList.sort(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				long s1time=o1.getStartTime().getTime();
				long s2time=o2.getStartTime().getTime();
				if(s1time<s2time)return -1;
				else if(s1time>s2time)return 1;

				return 0;
			}
		});
		int i=0;
		Meeting tempm;
		//System.out.println("end time:"+endT);
		for(;i<mList.size();i++)
		{
			
			tempm=mList.get(i);
			//System.out.println("s:"+tempm.getStartTime().getTime()+" e:"+tempm.getEndTime().getTime());
			if(tempm.getStartTime().getTime()>=endT)
			{
				break;
			}
		}
		if(i>=mList.size())
		{
			//System.out.println("no conflict");
			i=mList.size()-1;
		}
		
		//System.out.println("may have conflict");
			
		for(;i>=0;i--)
		{
			if(mList.get(i).getEndTime().getTime()>startT)
			{
				//need to delete staff from emplist who joined this meeting
				Meeting goalMeeting=mList.get(i);
				List<MeetingRelationStaff> mrsList;
				//System.out.println("people in meeting "+goalMeeting.getMeetingName()+" have conflict");
				mrsList=mrsDao.findByProperty("meetingId", goalMeeting.getMeetingId());
				for(MeetingRelationStaff mrs:mrsList)
				{
					//mrs.getStaffId();
					can_use_employeeMap.remove(mrs.getStaffId());
				}
					
			}
		}
		
		
		
		Map<String, ArrayList<Employee>> departmentemployeeMap= new HashMap<String, ArrayList<Employee>>();
		Map<String, Department> namedepartmentMap=new HashMap<String, Department>();
		List<DepartmentRelationStaff> drsList=new ArrayList<DepartmentRelationStaff>();
		ArrayList<Employee> tempEmpList;
		DepartmentRelationStaffDAO drsDAO=new DepartmentRelationStaffDAO();
		DepartmentDAO departDAO=new DepartmentDAO();
		Department department;
		Employee tempe;
		JSONObject tempJsonObject;
		String tempKey;
		
		Set<Map.Entry<Integer,Employee>> allSet=can_use_employeeMap.entrySet();
		Iterator<Map.Entry<Integer, Employee>> iterator=allSet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.Integer, data.Employee> entry = (Map.Entry<java.lang.Integer, data.Employee>) iterator
					.next();
			//temp employee who can join meeting
			tempe= entry.getValue();
			//find its department
			drsList=drsDAO.findByProperty("staffId", entry.getKey());
			if(drsList.size()!=1)
			{
				//have no department
			}else {
				int departmentid=drsList.get(0).getDepartmentId();
				
				department=departDAO.findById(departmentid);
				tempKey=department.getDepartmentName();
				//department name to department
				namedepartmentMap.put(tempKey, department);
				//if already have that department in map
				if(departmentemployeeMap.containsKey(tempKey)==true)
				{
					//get its data and add employee in that department
					tempEmpList=departmentemployeeMap.get(tempKey);
					tempEmpList.add(tempe);
				}else {
					//create new in it
					tempEmpList=new ArrayList<Employee>();
					tempEmpList.add(tempe);	
				}
				departmentemployeeMap.put(tempKey, tempEmpList);
			}
		}
		
		Set<Map.Entry<String, ArrayList<Employee>>> departmentset=departmentemployeeMap.entrySet();
		Iterator<Map.Entry<String, ArrayList<Employee>>> setiterator=departmentset.iterator();
		while (setiterator.hasNext()) {
			Map.Entry<java.lang.String, ArrayList<data.Employee>> entry = (Map.Entry<java.lang.String,ArrayList<data.Employee>>) setiterator
					.next();
			tempJsonObject=new JSONObject();
			tempJsonObject.put("department", namedepartmentMap.get(entry.getKey()));
			tempJsonObject.put("stafflist", entry.getValue());
			ret_val.add(tempJsonObject);
		}
		return ret_val.toString();
	}
	@Override
	public boolean checkbooker_time_conflict(String jsonString) throws RemoteException {
		// TODO Auto-generated method stub
		JSONObject jsonObject=JSONObject.fromObject(jsonString);
		String rawstarttime=(String)jsonObject.get("starttime");
		String rawendtime=(String)jsonObject.get("endtime");
		int staffid=(int)jsonObject.get("staffid");
		Timestamp startT=convertDate(rawstarttime);
		Timestamp endT=convertDate(rawendtime);
		List<Integer> tempList=new ArrayList<Integer>();
		tempList.add(staffid);
		JSONArray tempArray=JSONArray.fromObject(checkStaffMeetingConflict(startT, endT, tempList));
		if(tempArray.size()>0)return false;
		return true;
	}
	@Deprecated
	@Override
	public String search_meeting_by_staff(int staffid) throws RemoteException {
		// TODO Auto-generated method stub
		JSONArray ret_val=new JSONArray();
		JSONObject tempJsonObject;
		MeetingRelationStaffDAO mrsDao= new MeetingRelationStaffDAO();
		MeetingDAO mDAO=new MeetingDAO();
		Meeting tempMeeting=null;
		List<MeetingRelationStaff> mrsList=new ArrayList<MeetingRelationStaff>();
		List<Meeting> meetingList=new ArrayList<Meeting>();
		MeetingRoomDAO mrDao=new MeetingRoomDAO();
    	MeetingRoom tempmr=null;
    	
		mrsList=mrsDao.findBystaffid(staffid);
		for(MeetingRelationStaff mrs:mrsList)
		{
			tempMeeting=mDAO.findById(mrs.getMeetingId());
	    	if(tempMeeting!=null)
	    	{
	    		System.out.println(tempMeeting.getMeetingName()+" "+tempMeeting.getBookName());
	    		meetingList.add(tempMeeting);
	    		
	    	}
	    }
		//sort meeting list by start time
		meetingList.sort(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				long s1time=o1.getStartTime().getTime();
				long s2time=o2.getStartTime().getTime();
				if(s1time<s2time)return -1;
				else if(s1time>s2time)return 1;
				return 0;
			}
		});
		//search finished
		for(Meeting m:meetingList)
		{
			tempJsonObject=JSONObject.fromObject(m);
    		if(m.getBookTime()!=null)tempJsonObject.put("booktime", deconvertDate(m.getBookTime()));
    		tempJsonObject.put("starttime",deconvertDate(m.getStartTime()));
    		tempJsonObject.put("endtime", deconvertDate(m.getEndTime()));
    		tempmr=mrDao.findById(m.getMeetingroomId());
    		if(tempmr!=null)
    		{
    			tempJsonObject.put("meetingroomname",tempmr.getMeetingRoomName());
    		}
    		tempJsonObject.put("data", searchstaff_by_meetingid(m.getMeetingId()));
    		ret_val.add(tempJsonObject);
		}
		
		return ret_val.toString();
	}
	@Override
	public String search_future_7daysMeeting(String bookname) throws RemoteException {
		// TODO Auto-generated method stub
		Timestamp startTimestamp=new Timestamp(System.currentTimeMillis());
		Timestamp endTimestamp=new Timestamp(System.currentTimeMillis()+604800000);
		
		return search_future_meeting(bookname,endTimestamp);
	}
	public String search_future_meeting(String bookname)throws RemoteException {
		Timestamp endTimestamp=new Timestamp(253402271999000L);
		return search_future_meeting(bookname,endTimestamp);
	}
	
	
	public String search_future_meeting(String bookname,Timestamp endTimestamp)throws RemoteException {
		
		Timestamp startTimestamp=new Timestamp(System.currentTimeMillis());
		JSONObject tempJson=new JSONObject();
		tempJson.put("bookname", bookname);
		tempJson.put("starttime", deconvertDate(startTimestamp));
		tempJson.put("endtime", deconvertDate(endTimestamp));
		tempJson.put("status", 0);
		return searchMeeting(tempJson.toString());
	}
	
	
	@Override
	public boolean modify_staff(String jsonString) throws RemoteException {
		// TODO Auto-generated method stub
		JSONObject tempJsonObject=JSONObject.fromObject(jsonString);
		
		int staffid=(int)tempJsonObject.get("staffid");
		String telepnone=(String)tempJsonObject.get("telephone");
		String email=(String)tempJsonObject.get("email");
		String password=(String)tempJsonObject.get("password");
		User tempUser;
		UserDAO uDAO=new UserDAO();
		Employee tempEmployee;
		EmployeeDAO employeeDAO=new EmployeeDAO();
		if((telepnone==null||telepnone.length()==0)&&(email.length()==0||email==null)){}
		else {
			tempEmployee=employeeDAO.findById(staffid);
			if(tempEmployee==null){return false;}
			else {
				if(telepnone.length()>0)tempEmployee.setTelephone(telepnone);
				if(email.length()>0)tempEmployee.setEmail(email);
				employeeDAO.update(tempEmployee);
			}
		}
		
		
		if(password==null||password.length()==0)return true;
		tempUser=uDAO.findById(staffid);
		if(tempUser==null)
		{
			return false;
			//not found that user
		}else {
			tempUser.setPassword(password);
			uDAO.update(tempUser);
			
		}
		return true;
	}
	@Override
	public boolean cancel_meeting(int meetingid) throws RemoteException {
		// TODO Auto-generated method stub
		MeetingDAO meetingDAO=new MeetingDAO();
		MeetingRelationStaffDAO mrsDao=new MeetingRelationStaffDAO();
		Meeting tempm=null;
		List<MeetingRelationStaff> mrsList=new ArrayList<MeetingRelationStaff>();
		tempm=meetingDAO.findById(meetingid);
		if(tempm==null){return false;}
		else {
			tempm.setStatus(1);
			meetingDAO.update(tempm);
			mrsList=mrsDao.findByProperty("meetingId", meetingid);
			if(mrsList.size()==0){return true;}
			for(MeetingRelationStaff mrs:mrsList)
			{
				mrsDao.delete(mrs);
			}
		}
		return true;
	}
	@Override
	public boolean cancel_meeting(String jsonString) throws RemoteException {
		// TODO Auto-generated method stub
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		int meetingid=(int)tempJson.get("meetingid");
		String cancel_note=(String)tempJson.get("cancelnotes");
		MeetingDAO meetingDAO=new MeetingDAO();
		MeetingRelationStaffDAO mrsDao=new MeetingRelationStaffDAO();
		Meeting tempm=null;
		List<MeetingRelationStaff> mrsList=new ArrayList<MeetingRelationStaff>();
		tempm=meetingDAO.findById(meetingid);
		if(tempm==null){return false;}
		else {
			tempm.setStatus(1);
			tempm.setCancelNotes(cancel_note);
			meetingDAO.update(tempm);
//			mrsList=mrsDao.findByProperty("meetingId", meetingid);
//			if(mrsList.size()==0){return true;}
//			for(MeetingRelationStaff mrs:mrsList)
//			{
//				mrsDao.delete(mrs);
//			}
		}
		return true;
	}
	@Override
	public String search_meeting_by_staff(String jsonString) throws RemoteException {
		// TODO Auto-generated method stub
		JSONObject tempJsonObject=JSONObject.fromObject(jsonString);
		int staffid=(int)tempJsonObject.get("staffid");
		int status=(int)tempJsonObject.get("status");
		JSONArray ret_val=new JSONArray();
		
		MeetingRelationStaffDAO mrsDao= new MeetingRelationStaffDAO();
		MeetingDAO mDAO=new MeetingDAO();
		Meeting tempMeeting=null;
		List<MeetingRelationStaff> mrsList=new ArrayList<MeetingRelationStaff>();
		List<Meeting> meetingList=new ArrayList<Meeting>();
		MeetingRoomDAO mrDao=new MeetingRoomDAO();
    	MeetingRoom tempmr=null;
    	
		mrsList=mrsDao.findBystaffid(staffid);
		for(MeetingRelationStaff mrs:mrsList)
		{
			tempMeeting=mDAO.findById(mrs.getMeetingId());
	    	if(tempMeeting!=null&&tempMeeting.getStatus()==status)
	    	{
	    		System.out.println(tempMeeting.getMeetingName()+" "+tempMeeting.getBookName());
	    		meetingList.add(tempMeeting);
	    		
	    	}
	    }
		//sort meeting list by start time
		meetingList.sort(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				long s1time=o1.getStartTime().getTime();
				long s2time=o2.getStartTime().getTime();
				if(s1time<s2time)return -1;
				else if(s1time>s2time)return 1;
				return 0;
			}
		});
		//search finished
		for(Meeting m:meetingList)
		{
			tempJsonObject=JSONObject.fromObject(m);
    		if(m.getBookTime()!=null)tempJsonObject.put("booktime", deconvertDate(m.getBookTime()));
    		tempJsonObject.put("starttime",deconvertDate(m.getStartTime()));
    		tempJsonObject.put("endtime", deconvertDate(m.getEndTime()));
    		tempmr=mrDao.findById(m.getMeetingroomId());
    		if(tempmr!=null)
    		{
    			tempJsonObject.put("meetingroomname",tempmr.getMeetingRoomName());
    		}
    		tempJsonObject.put("data", searchstaff_by_meetingid(m.getMeetingId()));
    		ret_val.add(tempJsonObject);
		}
		
		return ret_val.toString();
	}
	
}

