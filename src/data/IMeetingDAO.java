package data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for MeetingDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IMeetingDAO {
	/**
	 * Perform an initial save of a previously unsaved Meeting entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * 
	 * EntityManagerHelper.beginTransaction();
	 * IMeetingDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Meeting entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Meeting entity);

	/**
	 * Delete a persistent Meeting entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMeetingDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Meeting entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Meeting entity);

	/**
	 * Persist a previously saved Meeting entity and return it or a copy of it
	 * to the sender. A copy of the Meeting entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IMeetingDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Meeting entity to update
	 * @return Meeting the persisted Meeting entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Meeting update(Meeting entity);

	public Meeting findById(Integer id);

	/**
	 * Find all Meeting entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Meeting property to query
	 * @param value
	 *            the property value to match
	 * @return List<Meeting> found by query
	 */
	public List<Meeting> findByProperty(String propertyName, Object value);

	public List<Meeting> findByMeetingName(Object meetingName);

	public List<Meeting> findByPeopleNum(Object peopleNum);

	public List<Meeting> findByMeetingNotes(Object meetingNotes);

	public List<Meeting> findByMeetingroomId(Object meetingroomId);

	public List<Meeting> findByStatus(Object status);

	public List<Meeting> findByBookName(Object bookName);

	/**
	 * Find all Meeting entities.
	 * 
	 * @return List<Meeting> all Meeting entities
	 */
	public List<Meeting> findAll();
}