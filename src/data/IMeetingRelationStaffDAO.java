package data;

import java.util.List;

/**
 * Interface for MeetingRelationStaffDAO.
 * @author MyEclipse Persistence Tools
 */

public interface IMeetingRelationStaffDAO {
		/**
	 Perform an initial save of a previously unsaved MeetingRelationStaff entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   IMeetingRelationStaffDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity MeetingRelationStaff entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(MeetingRelationStaff entity);
    /**
	 Delete a persistent MeetingRelationStaff entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   IMeetingRelationStaffDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity MeetingRelationStaff entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(MeetingRelationStaff entity);
   /**
	 Persist a previously saved MeetingRelationStaff entity and return it or a copy of it to the sender. 
	 A copy of the MeetingRelationStaff entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = IMeetingRelationStaffDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity MeetingRelationStaff entity to update
	 @return MeetingRelationStaff the persisted MeetingRelationStaff entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
	public MeetingRelationStaff update(MeetingRelationStaff entity);
	public MeetingRelationStaff findById( MeetingRelationStaffId id);
	 /**
	 * Find all MeetingRelationStaff entities with a specific property value.  
	 
	  @param propertyName the name of the MeetingRelationStaff property to query
	  @param value the property value to match
	  	  @return List<MeetingRelationStaff> found by query
	 */
	public List<MeetingRelationStaff> findByProperty(String propertyName, Object value
		);
	/**
	 * Find all MeetingRelationStaff entities.
	  	  @return List<MeetingRelationStaff> all MeetingRelationStaff entities
	 */
	public List<MeetingRelationStaff> findAll(
		);	
}