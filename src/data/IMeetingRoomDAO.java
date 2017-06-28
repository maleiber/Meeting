package data;

import java.util.List;

/**
 * Interface for MeetingRoomDAO.
 * @author MyEclipse Persistence Tools
 */

public interface IMeetingRoomDAO {
		/**
	 Perform an initial save of a previously unsaved MeetingRoom entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   IMeetingRoomDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity MeetingRoom entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(MeetingRoom entity);
    /**
	 Delete a persistent MeetingRoom entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   IMeetingRoomDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity MeetingRoom entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(MeetingRoom entity);
   /**
	 Persist a previously saved MeetingRoom entity and return it or a copy of it to the sender. 
	 A copy of the MeetingRoom entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = IMeetingRoomDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity MeetingRoom entity to update
	 @return MeetingRoom the persisted MeetingRoom entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
	public MeetingRoom update(MeetingRoom entity);
	public MeetingRoom findById( Integer id);
	 /**
	 * Find all MeetingRoom entities with a specific property value.  
	 
	  @param propertyName the name of the MeetingRoom property to query
	  @param value the property value to match
	  	  @return List<MeetingRoom> found by query
	 */
	public List<MeetingRoom> findByProperty(String propertyName, Object value
		);
	public List<MeetingRoom> findByMeetingRoomName(Object meetingRoomName
		);
	public List<MeetingRoom> findByCapacity(Object capacity
		);
	public List<MeetingRoom> findByRoomNumbler(Object roomNumbler
		);
	public List<MeetingRoom> findByRemark(Object remark
		);
	public List<MeetingRoom> findByCurrentAtate(Object currentAtate
		);
	/**
	 * Find all MeetingRoom entities.
	  	  @return List<MeetingRoom> all MeetingRoom entities
	 */
	public List<MeetingRoom> findAll(
		);	
}