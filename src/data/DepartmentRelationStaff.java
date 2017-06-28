package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DepartmentRelationStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "department_relation_staff", catalog = "meetingroom")

public class DepartmentRelationStaff implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer staffId;
	private Integer departmentId;

	// Constructors

	/** default constructor */
	public DepartmentRelationStaff() {
	}

	/** full constructor */
	public DepartmentRelationStaff(Integer staffId, Integer departmentId) {
		this.staffId = staffId;
		this.departmentId = departmentId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

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

	@Column(name = "department_id")

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}