package data;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Meeting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "meeting", catalog = "meetingroom")

public class Meeting implements java.io.Serializable {

	// Fields

	private Integer meetingId;
	private String meetingName;
	private Integer peopleNum;
	private Timestamp startTime;
	private Timestamp endTime;
	private String meetingNotes;
	private Integer meetingroomId;
	private Integer status;
	private String bookName;

	// Constructors

	/** default constructor */
	public Meeting() {
	}

	/** minimal constructor */
	public Meeting(Integer meetingId) {
		this.meetingId = meetingId;
	}

	/** full constructor */
	public Meeting(Integer meetingId, String meetingName, Integer peopleNum, Timestamp startTime, Timestamp endTime,
			String meetingNotes, Integer meetingroomId, Integer status, String bookName) {
		this.meetingId = meetingId;
		this.meetingName = meetingName;
		this.peopleNum = peopleNum;
		this.startTime = startTime;
		this.endTime = endTime;
		this.meetingNotes = meetingNotes;
		this.meetingroomId = meetingroomId;
		this.status = status;
		this.bookName = bookName;
	}

	// Property accessors
	@Id

	@Column(name = "meeting_id", unique = true, nullable = false)

	public Integer getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "meeting_name", length = 128)

	public String getMeetingName() {
		return this.meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	@Column(name = "people_num")

	public Integer getPeopleNum() {
		return this.peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	@Column(name = "start_time", length = 19)

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 19)

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "meeting_notes", length = 128)

	public String getMeetingNotes() {
		return this.meetingNotes;
	}

	public void setMeetingNotes(String meetingNotes) {
		this.meetingNotes = meetingNotes;
	}

	@Column(name = "meetingroom_id")

	public Integer getMeetingroomId() {
		return this.meetingroomId;
	}

	public void setMeetingroomId(Integer meetingroomId) {
		this.meetingroomId = meetingroomId;
	}

	@Column(name = "status")

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "book_name", length = 128)

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

}