package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EmployeeCopy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee_copy", catalog = "meetingroom")

public class EmployeeCopy implements java.io.Serializable {

	// Fields

	private Integer staffId;
	private String name;
	private String telephone;
	private String email;
	private String position;
	private Integer level;
	private Integer departmentid;
	private Integer checkStatus;
	private String username;
	private String password;

	// Constructors

	/** default constructor */
	public EmployeeCopy() {
	}

	/** minimal constructor */
	public EmployeeCopy(Integer staffId, String username, String password) {
		this.staffId = staffId;
		this.username = username;
		this.password = password;
	}

	/** full constructor */
	public EmployeeCopy(Integer staffId, String name, String telephone, String email, String position, Integer level,
			Integer departmentid, Integer checkStatus, String username, String password) {
		this.staffId = staffId;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
		this.position = position;
		this.level = level;
		this.departmentid = departmentid;
		this.checkStatus = checkStatus;
		this.username = username;
		this.password = password;
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

	@Column(name = "departmentid")

	public Integer getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	@Column(name = "check_status")

	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "username", nullable = false, length = 128)

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 128)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}