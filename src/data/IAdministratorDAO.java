package data;

import java.util.List;

/**
 * Interface for AdministratorDAO.
 * @author MyEclipse Persistence Tools
 */

public interface IAdministratorDAO {
		/**
	 Perform an initial save of a previously unsaved Administrator entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   IAdministratorDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Administrator entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Administrator entity);
    /**
	 Delete a persistent Administrator entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   IAdministratorDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Administrator entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Administrator entity);
   /**
	 Persist a previously saved Administrator entity and return it or a copy of it to the sender. 
	 A copy of the Administrator entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = IAdministratorDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Administrator entity to update
	 @return Administrator the persisted Administrator entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
	public Administrator update(Administrator entity);
	public Administrator findById( Integer id);
	 /**
	 * Find all Administrator entities with a specific property value.  
	 
	  @param propertyName the name of the Administrator property to query
	  @param value the property value to match
	  	  @return List<Administrator> found by query
	 */
	public List<Administrator> findByProperty(String propertyName, Object value
		);
	public List<Administrator> findByAdmName(Object admName
		);
	/**
	 * Find all Administrator entities.
	  	  @return List<Administrator> all Administrator entities
	 */
	public List<Administrator> findAll(
		);	
}