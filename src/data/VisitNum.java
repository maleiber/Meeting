package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VisitNum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "visit_num", catalog = "meetingroom")

public class VisitNum implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer num;

	// Constructors

	/** default constructor */
	public VisitNum() {
	}

	/** minimal constructor */
	public VisitNum(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public VisitNum(Integer id, Integer num) {
		this.id = id;
		this.num = num;
	}

	// Property accessors
	@Id

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "num")

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}