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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;


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
		
		if(checkusername(username)){
		EmployeeCopyDAO b=new EmployeeCopyDAO();
		int max;
		max=b.getcount();
		while(b.findById(max+1)!=null){max++;}
		
		String tmpString="tryto regist no="+(max+1)+"name="+name;
		EntityManagerHelper.log(tmpString, Level.INFO, null);
		EmployeeCopy a=new EmployeeCopy(max+1,name,telephone,email,position,1,departmentid,0,username,password);	
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
	
	public boolean checkusername(String username){
		UserDAO mUserDAO=new UserDAO();
		EmployeeCopyDAO mEmployeeCOPYDAO=new EmployeeCopyDAO();
		if(mUserDAO.findByProperty("username", username).size()>0){
			EntityManagerHelper.log("username conflict in user data!", Level.INFO, null);
			return false;
			
		}
		else{
			if(mEmployeeCOPYDAO.findByProperty("username", username).size()>0){
				EntityManagerHelper.log("username confilct in employ data", Level.INFO, null);
				return false;
			}
			else{
			EntityManagerHelper.log("check staff username success", Level.INFO, null);
		return true;
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
	//	if(checkusername())
		int max;
		//int id=assign();
		//max=b.getcount();
		UserDAO mUserDAO=new UserDAO();
		Employee a=new Employee(staffid,name,telephone,email,position,1,mUserDAO.getcount());
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
		Employee employee;
		a=b.findByProperty("username", name);
		if(a.size()==0)return ""; 
		//a=b.findByUsername(name);
		
		if(password.equals(a.get(0).getPassword())){
			employee=ed.findByUserid(a.get(0).getUserId()).get(0);
			
			ret_val= JSONObject.fromObject(employee);
			EntityManagerHelper.log("login success", Level.INFO, null);
			return ret_val.toString();
		}
		else
		{
			EntityManagerHelper.log("login failed", Level.INFO, null);
			return "";
		}
		
		
	}
	
	public String login(String jsonString) throws RemoteException{
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		String username=(String)tempJson.get("username");
		String password=(String)tempJson.get("password");
		return login(username,password);
	}
	
	
	public void add_department(String departmentname)throws RemoteException{
//		check
		if(checkdepartmentname(departmentname)){
		Department department=new Department();
		DepartmentDAO mDepartmentDAO=new DepartmentDAO();
		department.setDepartmentId(mDepartmentDAO.getcount()+1);
		department.setDepartmentName(departmentname);
		department.setDepartmentNum(0);
		mDepartmentDAO.update(department);
		EntityManagerHelper.log("add department success", Level.INFO, null);
		}
		else{
			EntityManagerHelper.log("add department failure", Level.INFO, null);
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
	
	
	
	boolean checktimeconflict(Timestamp starttime,Timestamp endtime,int meetingroom_id){
		
		if(starttime.getTime()>endtime.getTime())return false;
		
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
				return false;
			}
			lastendtime=m.getEndTime().getTime();
		}
		
		return true;
	}
	
	public boolean add_meeting(String jsonString)throws RemoteException{
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		JSONArray tempJsonStaff;
		String bookname,meetingname,meeting_notes;
		int meeting_num,meetingroom_id;
		String sTime,eTime;
		List<Integer>staffid=new ArrayList<Integer>();
		
		bookname=(String)tempJson.get("bookname");
		meetingname=(String)tempJson.get("meetingname");
		meeting_notes=(String)tempJson.get("meetingnotes");
		meeting_num=(int)tempJson.get("meetingnum");
		meetingroom_id=(int)tempJson.getInt("meetingroomid");
		sTime=(String)tempJson.get("starttime");
		eTime=(String)tempJson.get("endtime");
		tempJsonStaff=(JSONArray)tempJson.get("staffid");
		
		staffid=JSONArray.toList(tempJsonStaff);
		
		return add_meeting(bookname, meetingname, meeting_num, meeting_notes, meetingroom_id, sTime, eTime, staffid);
	}
	
	public boolean add_meeting(String bookname,String meetingname,int meeting_num,String meeting_notes,int meetingroom_id,
	String sTime, String eTime,List<Integer>staffid)throws RemoteException{
		
		Timestamp startTime=convertDate(sTime);
		Timestamp endTime=convertDate(eTime);
		
		UserDAO uDao=new UserDAO();
		List<User> userList= new ArrayList<User>();
		EmployeeDAO eDao=new EmployeeDAO();
		List<Employee> tempe;
		Meeting meeting=new Meeting();
		MeetingDAO meetingDAO= new MeetingDAO();
		MeetingRelationStaff mRelationStaff=new MeetingRelationStaff();
		MeetingRelationStaffDAO mRelationStaffDAO=new MeetingRelationStaffDAO();
	//	MeetingRelationStaffId mRelationStaffId=new MeetingRelationStaffId();
		
		userList=uDao.findByProperty("username", bookname);
		if(userList.size()>1)
		{
			EntityManagerHelper.log("add meeting failure: have several book name", Level.INFO, null);
			return false;
		}else if(userList.size()<1)
		{
			EntityManagerHelper.log("add meeting failure: cannot find book name", Level.INFO, null);
			return false;
		}
		
		tempe=eDao.findByName(userList.get(0).getUsername());
		if(tempe.size()>1)
		{
			EntityManagerHelper.log("add meeting failure: the book name have several staff id", Level.INFO, null);
			return false;
		}else if(tempe.size()<1)
		{
			EntityManagerHelper.log("add meeting failure: the book name have no staff id", Level.INFO, null);
			return false;
		}
		
		staffid.add(tempe.get(0).getStaffId());
		if(!checktimeconflict(startTime,endTime,meetingroom_id)){
			EntityManagerHelper.log("add meeting failure: meeting date conflict", Level.INFO, null);
			return false;
		}
		else if(!checkStaffMeetingConflict(startTime,endTime,staffid))
		{
			EntityManagerHelper.log("add meeting failure: participant's date conflict", Level.INFO, null);
			return false;
		}
		
		
		int meetingid=meetingDAO.getcount()+1;
		
		if(staffid.size()==0){
			EntityManagerHelper.log("add meeting failure", Level.INFO, null);
			return false;
		}
		else{
			meeting.setMeetingName(meetingname);
			meeting.setMeetingNotes(meeting_notes);
			meeting.setMeetingroomId(meetingroom_id);
			meeting.setPeopleNum(meeting_num);
			meeting.setStartTime(startTime);
			meeting.setEndTime(endTime);
			meeting.setMeetingId(meetingid);
			meeting.setBookName(bookname);
			
			//System.out.println(meeting.getMeetingId());
			meetingDAO.update(meeting);
			
		for(int i=0;i<staffid.size();i++){
				mRelationStaff.setId(mRelationStaffDAO.getcount()+1);	
				
				System.out.println(mRelationStaffDAO.getcount());
				
				mRelationStaff.setStaffId(staffid.get(i));
				System.out.println(staffid.get(i));
				
				mRelationStaff.setMeetingId(meetingid);
				//mRelationStaff.setId(mRelationStaffId);
				//System.out.println(mRelationStaff.getId().getId());
				mRelationStaffDAO.update(mRelationStaff);
		}
		EntityManagerHelper.log("add meeting success", Level.INFO, null);
		return true;
		}
	}
	
	
	boolean checkStaffMeetingConflict(Timestamp startTime, Timestamp endTime,List<Integer>staffid)
	{
		MeetingRelationStaffDAO mrsDao=new MeetingRelationStaffDAO();
		MeetingDAO mDao=new MeetingDAO();
		List<MeetingRelationStaff> mrsList=new ArrayList<MeetingRelationStaff>();
		List<Meeting> meetingList=new ArrayList<Meeting>();
		Meeting nowMeeting=new Meeting();
		Set<Integer> meetingSet=new HashSet<Integer>() ;
		
		nowMeeting.setStartTime(startTime);
		nowMeeting.setEndTime(endTime);
		meetingList.add(nowMeeting);
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
		
		long lastendtime=Long.MIN_VALUE;
		for(Meeting m:meetingList)
		{
			if(m.getStartTime().getTime()<lastendtime)
			{
				return false;
			}
			lastendtime=m.getEndTime().getTime();
		}
		
		return true;
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
    	List<EmployeeCopy> empCopyList=new ArrayList<EmployeeCopy>();
    	EmployeeDAO empDao=new EmployeeDAO();
    	EmployeeCopyDAO empCopyDAO=new EmployeeCopyDAO();
    	
    	
    	
    	if(state==0||state==1)//in employcopy
    	{
    		empCopyList=empCopyDAO.findByMultiProperty(name, username,state);
    		for(EmployeeCopy e:empCopyList)
    		{
    			ret_val.add(JSONObject.fromObject(e));
    		}
    		
    	}
    	else if(state==2) {//in employee
    		empList=empDao.findByMultiProperty(name, username);
    		for(Employee ec:empList)
    		{
    			ret_val.add(JSONObject.fromObject(ec));
    		}
		}else {				//in both employee and employee
			empCopyList=empCopyDAO.findByMultiProperty(name, username,state);
    		for(EmployeeCopy e:empCopyList)
    		{
    			ret_val.add(JSONObject.fromObject(e));
    		}
    		empList=empDao.findByMultiProperty(name, username);
    		for(Employee ec:empList)
    		{
    			ret_val.add(JSONObject.fromObject(ec));
    		}
		}
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
    	
    	List<Department> mDepartments=new ArrayList<Department>();
		DepartmentDAO mDepartmentDAO=new DepartmentDAO();
		mDepartments=mDepartmentDAO.findAll();
		String tempString=new String();
    	
		for(Department depart:mDepartments)
		{
			tempString=JSONObject.fromObject(depart).toString();
			
			drsList=drsDAO.findByProperty("departmentId", depart.getDepartmentId());
			
			if(drsList.size()==0){
	    		EntityManagerHelper.log("search staff by department empty.", Level.INFO, null);
	    		
	    	}
	    	else
	    	{
	    		for(DepartmentRelationStaff d:drsList)
	    		{
	    			empList.add(ed.findById(d.getStaffId()));
	    			
	    		}
	    		EntityManagerHelper.log("search staff by department id: "+depart.getDepartmentId()+" information.", Level.INFO, null);
	    		
	    		tempString+=JSONObject.fromObject(empList).toString();
	    		empList.clear();
	    	}
			ret_val.add(tempString);
		}
    	
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
		int people_num,meetingroom_id,status;
		String sTime,eTime;
		JSONObject tempJson=JSONObject.fromObject(jsonString);
		
		meeting_name=(String)tempJson.get("meetingname");
		meeting_notes=(String)tempJson.get("meetingnotes");
		book_name=(String)tempJson.get("bookname");
		people_num=(int)tempJson.get("peoplenum");
		meetingroom_id=(int)tempJson.get("meetingroomid");
		status=(int)tempJson.get("status");
		sTime=(String)tempJson.get("starttime");
		eTime=(String)tempJson.get("endtime");
		
		return searchMeeting(meeting_name, meeting_notes, book_name, people_num, meetingroom_id, status, sTime, eTime);
    }
    
    //multiple
    public String searchMeeting(Map<String, Object> map)throws RemoteException
    {
    	String meeting_name=null,meeting_notes=null, book_name=null;
		int people_num=-1, meetingroom_id=-1, status=-1;
		String start_time, end_time;
    	
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
		
		
		people_num=map.get("people_num")==null?-1:(int)map.get("people_num");
		meetingroom_id=map.get("meetingroom_id")==null?-1:(int)map.get("meetingroom_id");
		status=map.get("status")==null?-1:(int)map.get("status");
		return searchMeeting(meeting_name, meeting_notes, book_name, 
				people_num, meetingroom_id, status, 
				start_time, end_time);
    }
    
    //multiple
    public String searchMeeting(String meeting_name,String meeting_notes,String book_name,
    		int people_num,int meetingroom_id,int status,
    		String sTime,String eTime)throws RemoteException
    {
    	
    	Timestamp start_time=convertDate(sTime);
    	Timestamp end_time=convertDate(eTime);
    	List<Meeting> meetings=new ArrayList<Meeting>();
    	MeetingDAO meetingDAO=new MeetingDAO();
    	Meeting goalmeeting=new Meeting();
    	JSONArray ret_val= new JSONArray();
    	if(meeting_notes!=null&&meeting_notes.length()>0){
    		meeting_notes="%"+meeting_notes;
    		meeting_notes+="%";	
    	}
    	
    	meetings=meetingDAO.findByMultiProperty(meeting_name,people_num,start_time,end_time,meeting_notes,meetingroom_id,status,book_name);
    	
    	System.out.println(meetings.get(0).getMeetingName()+" "+meetings.get(0).getBookName());
    	
    	
    	if(meetings.size()>0)
    	{
    		//DO sth
    		for(Meeting m:meetings)
    		{
    			ret_val.add(JSONObject.fromObject(m));
    		}
    	}
    	return ret_val.toString();
    	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Timestamp timestamp=Timestamp.valueOf(newDateString);
    	return timestamp;
    }
	@Override
	public String search_meeting_room_by_name(String name) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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

	
}

