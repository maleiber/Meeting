package data;

import java.util.List;

/**
 * Interface for DepartmentRelationStaffDAO.
 * @author MyEclipse Persistence Tools
 */

public interface IDepartmentRelationStaffDAO {
		/**
	 Perform an initial save of a previously unsaved DepartmentRelationStaff entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   IDepartmentRelationStaffDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity DepartmentRelationStaff entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(DepartmentRelationStaff entity);
    /**
	 Delete a persistent DepartmentRelationStaff entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   IDepartmentRelationStaffDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity DepartmentRelationStaff entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(DepartmentRelationStaff entity);
   /**
	 Persist a previously saved DepartmentRelationStaff entity and return it or a copy of it to the sender. 
	 A copy of the DepartmentRelationStaff entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = IDepartmentRelationStaffDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity DepartmentRelationStaff entity to update
	 @return DepartmentRelationStaff the persisted DepartmentRelationStaff entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
	public DepartmentRelationStaff update(DepartmentRelationStaff entity);
	public DepartmentRelationStaff findById( DepartmentRelationStaffId id);
	 /**
	 * Find all DepartmentRelationStaff entities with a specific property value.  
	 
	  @param propertyName the name of the DepartmentRelationStaff property to query
	  @param value the property value to match
	  	  @return List<DepartmentRelationStaff> found by query
	 */
	public List<DepartmentRelationStaff> findByProperty(String propertyName, Object value
		);
	/**
	 * Find all DepartmentRelationStaff entities.
	  	  @return List<DepartmentRelationStaff> all DepartmentRelationStaff entities
	 */
	public List<DepartmentRelationStaff> findAll(
		);	
}