package data;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * DepartmentRelationStaffId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class DepartmentRelationStaffId  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer staffId;
     private Integer departmentId;


    // Constructors

    /** default constructor */
    public DepartmentRelationStaffId() {
    }

	/** minimal constructor */
    public DepartmentRelationStaffId(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public DepartmentRelationStaffId(Integer id, Integer staffId, Integer departmentId) {
        this.id = id;
        this.staffId = staffId;
        this.departmentId = departmentId;
    }

   
    // Property accessors

    @Column(name="id", nullable=false)

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

    @Column(name="department_id")

    public Integer getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DepartmentRelationStaffId) ) return false;
		 DepartmentRelationStaffId castOther = ( DepartmentRelationStaffId ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getStaffId()==castOther.getStaffId()) || ( this.getStaffId()!=null && castOther.getStaffId()!=null && this.getStaffId().equals(castOther.getStaffId()) ) )
 && ( (this.getDepartmentId()==castOther.getDepartmentId()) || ( this.getDepartmentId()!=null && castOther.getDepartmentId()!=null && this.getDepartmentId().equals(castOther.getDepartmentId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getStaffId() == null ? 0 : this.getStaffId().hashCode() );
         result = 37 * result + ( getDepartmentId() == null ? 0 : this.getDepartmentId().hashCode() );
         return result;
   }   





}