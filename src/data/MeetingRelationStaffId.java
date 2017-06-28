package data;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * MeetingRelationStaffId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class MeetingRelationStaffId  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer staffId;
     private Integer meetingId;


    // Constructors

    /** default constructor */
    public MeetingRelationStaffId() {
    }

	/** minimal constructor */
    public MeetingRelationStaffId(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public MeetingRelationStaffId(Integer id, Integer staffId, Integer meetingId) {
        this.id = id;
        this.staffId = staffId;
        this.meetingId = meetingId;
    }

   
    // Property accessors

    @Column(name="ID", nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="staff_id")

    public Integer getStaffId() {
        return this.staffId;
    }
    
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Column(name="meeting_id")

    public Integer getMeetingId() {
        return this.meetingId;
    }
    
    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MeetingRelationStaffId) ) return false;
		 MeetingRelationStaffId castOther = ( MeetingRelationStaffId ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getStaffId()==castOther.getStaffId()) || ( this.getStaffId()!=null && castOther.getStaffId()!=null && this.getStaffId().equals(castOther.getStaffId()) ) )
 && ( (this.getMeetingId()==castOther.getMeetingId()) || ( this.getMeetingId()!=null && castOther.getMeetingId()!=null && this.getMeetingId().equals(castOther.getMeetingId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getStaffId() == null ? 0 : this.getStaffId().hashCode() );
         result = 37 * result + ( getMeetingId() == null ? 0 : this.getMeetingId().hashCode() );
         return result;
   }   





}