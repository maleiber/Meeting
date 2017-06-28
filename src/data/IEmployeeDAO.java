package data;

import java.util.List;

/**
 * Interface for EmployeeDAO.
 * @author MyEclipse Persistence Tools
 */

public interface IEmployeeDAO {
		/**
	 Perform an initial save of a previously unsaved Employee entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   IEmployeeDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Employee entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Employee entity);
    /**
	 Delete a persistent Employee entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   IEmployeeDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Employee entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Employee entity);
   /**
	 Persist a previously saved Employee entity and return it or a copy of it to the sender. 
	 A copy of the Employee entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = IEmployeeDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Employee entity to update
	 @return Employee the persisted Employee entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
	public Employee update(Employee entity);
	public Employee findById( Integer id);
	 /**
	 * Find all Employee entities with a specific property value.  
	 
	  @param propertyName the name of the Employee property to query
	  @param value the property value to match
	  	  @return List<Employee> found by query
	 */
	public List<Employee> findByProperty(String propertyName, Object value
		);
	public List<Employee> findByName(Object name
		);
	public List<Employee> findByTelephone(Object telephone
		);
	public List<Employee> findByEmail(Object email
		);
	public List<Employee> findByPosition(Object position
		);
	public List<Employee> findByLevel(Object level
		);
	/**
	 * Find all Employee entities.
	  	  @return List<Employee> all Employee entities
	 */
	public List<Employee> findAll(
		);	
}