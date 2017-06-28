package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "department", catalog = "meetingroom")

public class Department implements java.io.Serializable {

	// Fields

	private Integer departmentId;
	private String departmentName;
	private Integer departmentNum;

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/** full constructor */
	public Department(Integer departmentId, String departmentName, Integer departmentNum) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentNum = departmentNum;
	}

	// Property accessors
	@Id

	@Column(name = "department_id", unique = true, nullable = false)

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "department_name", length = 128)

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "department_num")

	public Integer getDepartmentNum() {
		return this.departmentNum;
	}

	public void setDepartmentNum(Integer departmentNum) {
		this.departmentNum = departmentNum;
	}

}