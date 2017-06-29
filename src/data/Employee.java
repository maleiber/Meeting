package data;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee", catalog = "meetingroom")

public class Employee implements java.io.Serializable {

	// Fields

	private Integer staffId;
	private String name;
	private String telephone;
	private String email;
	private String position;
	private Integer level;
	private Integer userid;
	private Timestamp passtime;
	private Integer state;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(Integer staffId, Integer userid) {
		this.staffId = staffId;
		this.userid = userid;
	}

	/** full constructor */
	public Employee(Integer staffId, String name, String telephone, String email, String position, Integer level,
			Integer userid, Timestamp passtime, Integer state) {
		this.staffId = staffId;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
		this.position = position;
		this.level = level;
		this.userid = userid;
		this.passtime = passtime;
		this.state = state;
	}

	// Property accessors
	@Id

	@Column(name = "staff_id", unique = true, nullable = false)

	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@Column(name = "name", length = 128)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "telephone", length = 128)

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "email", length = 128)

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "position", length = 128)

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "level")

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "userid", nullable = false)

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "passtime", length = 19)

	public Timestamp getPasstime() {
		return this.passtime;
	}

	public void setPasstime(Timestamp passtime) {
		this.passtime = passtime;
	}

	@Column(name = "state")

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}