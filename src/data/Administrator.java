package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Administrator entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="administrator"
    ,catalog="meetingroom"
)

public class Administrator  implements java.io.Serializable {


    // Fields    

     private Integer admId;
     private String admName;


    // Constructors

    /** default constructor */
    public Administrator() {
    }

	/** minimal constructor */
    public Administrator(Integer admId) {
        this.admId = admId;
    }
    
    /** full constructor */
    public Administrator(Integer admId, String admName) {
        this.admId = admId;
        this.admName = admName;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="adm_id", unique=true, nullable=false)

    public Integer getAdmId() {
        return this.admId;
    }
    
    public void setAdmId(Integer admId) {
        this.admId = admId;
    }
    
    @Column(name="adm_name", length=128)

    public String getAdmName() {
        return this.admName;
    }
    
    public void setAdmName(String admName) {
        this.admName = admName;
    }
   








}