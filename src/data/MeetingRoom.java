package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * MeetingRoom entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="meeting_room"
    ,catalog="meetingroom"
)

public class MeetingRoom  implements java.io.Serializable {


    // Fields    

     private Integer meetingRoomId;
     private String meetingRoomName;
     private Long capacity;
     private String roomNumbler;
     private String remark;
     private String currentAtate;


    // Constructors

    /** default constructor */
    public MeetingRoom() {
    }

	/** minimal constructor */
    public MeetingRoom(Integer meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }
    
    /** full constructor */
    public MeetingRoom(Integer meetingRoomId, String meetingRoomName, Long capacity, String roomNumbler, String remark, String currentAtate) {
        this.meetingRoomId = meetingRoomId;
        this.meetingRoomName = meetingRoomName;
        this.capacity = capacity;
        this.roomNumbler = roomNumbler;
        this.remark = remark;
        this.currentAtate = currentAtate;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="meeting_room_id", unique=true, nullable=false)

    public Integer getMeetingRoomId() {
        return this.meetingRoomId;
    }
    
    public void setMeetingRoomId(Integer meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }
    
    @Column(name="meeting_room_name", length=128)

    public String getMeetingRoomName() {
        return this.meetingRoomName;
    }
    
    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }
    
    @Column(name="capacity")

    public Long getCapacity() {
        return this.capacity;
    }
    
    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
    
    @Column(name="room_numbler", length=128)

    public String getRoomNumbler() {
        return this.roomNumbler;
    }
    
    public void setRoomNumbler(String roomNumbler) {
        this.roomNumbler = roomNumbler;
    }
    
    @Column(name="remark", length=65535)

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="current_atate", length=128)

    public String getCurrentAtate() {
        return this.currentAtate;
    }
    
    public void setCurrentAtate(String currentAtate) {
        this.currentAtate = currentAtate;
    }
   








}