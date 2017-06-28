package data;

import java.util.List;

/**
 * Interface for VisitNumDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IVisitNumDAO {
	/**
	 * Perform an initial save of a previously unsaved VisitNum entity. All
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
	 * IVisitNumDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VisitNum entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VisitNum entity);

	/**
	 * Delete a persistent VisitNum entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVisitNumDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VisitNum entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VisitNum entity);

	/**
	 * Persist a previously saved VisitNum entity and return it or a copy of it
	 * to the sender. A copy of the VisitNum entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IVisitNumDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VisitNum entity to update
	 * @return VisitNum the persisted VisitNum entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public VisitNum update(VisitNum entity);

	public VisitNum findById(Integer id);

	/**
	 * Find all VisitNum entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VisitNum property to query
	 * @param value
	 *            the property value to match
	 * @return List<VisitNum> found by query
	 */
	public List<VisitNum> findByProperty(String propertyName, Object value);

	public List<VisitNum> findByNum(Object num);

	/**
	 * Find all VisitNum entities.
	 * 
	 * @return List<VisitNum> all VisitNum entities
	 */
	public List<VisitNum> findAll();
}