package data;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 	* A data access object (DAO) providing persistence and search support for Employee entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see data.Employee
  * @author MyEclipse Persistence Tools 
 */
public class EmployeeDAO  implements IEmployeeDAO{
	//property constants
	public static final String NAME = "name";
	public static final String TELEPHONE = "telephone";
	public static final String EMAIL = "email";
	public static final String POSITION = "position";
	public static final String LEVEL = "level";
	public static final String USERID = "userid";




	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Employee entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   EmployeeDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Employee entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Employee entity) {
    				EntityManagerHelper.log("saving Employee instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent Employee entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   EmployeeDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Employee entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Employee entity) {
    	
    			EntityManagerHelper.log("deleting Employee instance", Level.INFO, null);
    			EntityManager em=getEntityManager();	
	        try {
	        em.getTransaction().begin(); 
        	//entity = em.getReference(Employee.class, entity.getStaffId());
            em.remove(em.merge(entity));
            em.getTransaction().commit();
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /*  public void delete(Departmentappointment entity) {
	EntityManagerHelper.log("deleting Departmentappointment instance", Level.INFO, null);
	EntityManager em=getEntityManager();
try {
em.getTransaction().begin();  
em.remove(em.merge(entity));
em.getTransaction().commit(); 
		EntityManagerHelper.log("delete successful", Level.INFO, null);
} catch (Exception re) {
		EntityManagerHelper.log("delete failed", Level.SEVERE, re);
// throw re;
}
}
*/
    
    /**
	 Persist a previously saved Employee entity and return it or a copy of it to the sender. 
	 A copy of the Employee entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = EmployeeDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Employee entity to update
	 @return Employee the persisted Employee entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Employee update(Employee entity) {
    	EntityManager em=getEntityManager();
    				EntityManagerHelper.log("updating Employee instance", Level.INFO, null);
	        try {
	        	Employee result;
				em.getTransaction().begin(); 
                result = getEntityManager().merge(entity);
                em.getTransaction().commit(); 
                
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Employee findById( Integer id) {
    				EntityManagerHelper.log("finding Employee instance with id: " + id, Level.INFO, null);
	        try {
            Employee instance = getEntityManager().find(Employee.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all Employee entities with a specific property value.  
	 
	  @param propertyName the name of the Employee property to query
	  @param value the property value to match
	  	  @return List<Employee> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Employee> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Employee instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Employee model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		}
	}			
	public List<Employee> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<Employee> findByUserid(Object name
	) {
		return findByProperty(USERID, name
		);
	}
			
	
	public List<Employee> findByTelephone(Object telephone
	) {
		return findByProperty(TELEPHONE, telephone
		);
	}
	
	public List<Employee> findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List<Employee> findByPosition(Object position
	) {
		return findByProperty(POSITION, position
		);
	}
	
	public List<Employee> findByLevel(Object level
	) {
		return findByProperty(LEVEL, level
		);
	}
	
	
	/**
	 * Find all Employee entities.
	  	  @return List<Employee> all Employee entities
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> findAll(
		) {
					EntityManagerHelper.log("finding all Employee instances", Level.INFO, null);
			try {
			final String queryString = "select model from Employee model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}

	
	
	public int getcount() 
	{
			EntityManagerHelper.log("finding all Employee count", Level.INFO, null);
			try {
			final String queryString = "select max( model."+"userid"+" )from Employee model";
						Query query = getEntityManager().createQuery(queryString);
					Object q1=query.getResultList().get(0);
					int j=Integer.parseInt(String.valueOf(q1)); 
					return j; 
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
}