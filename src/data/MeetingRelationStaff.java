package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MeetingRelationStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "meeting_relation_staff", catalog = "meetingroom")

public class MeetingRelationStaff implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer staffId;
	private Integer meetingId;

	// Constructors

	/** default constructor */
	public MeetingRelationStaff() {
	}

	/** minimal constructor */
	public MeetingRelationStaff(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public MeetingRelationStaff(Integer id, Integer staffId, Integer meetingId) {
		this.id = id;
		this.staffId = staffId;
		this.meetingId = meetingId;
	}

	// Property accessors
	@Id

	@Column(name = "ID", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "staff_id")

	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@Column(name = "meeting_id")

	public Integer getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

}