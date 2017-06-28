package data;

import java.util.List;

/**
 * Interface for EmployeeCopyDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IEmployeeCopyDAO {
	/**
	 * Perform an initial save of a previously unsaved EmployeeCopy entity. All
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
	 * IEmployeeCopyDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            EmployeeCopy entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(EmployeeCopy entity);

	/**
	 * Delete a persistent EmployeeCopy entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IEmployeeCopyDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            EmployeeCopy entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(EmployeeCopy entity);

	/**
	 * Persist a previously saved EmployeeCopy entity and return it or a copy of
	 * it to the sender. A copy of the EmployeeCopy entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IEmployeeCopyDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            EmployeeCopy entity to update
	 * @return EmployeeCopy the persisted EmployeeCopy entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public EmployeeCopy update(EmployeeCopy entity);

	public EmployeeCopy findById(Integer id);

	/**
	 * Find all EmployeeCopy entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the EmployeeCopy property to query
	 * @param value
	 *            the property value to match
	 * @return List<EmployeeCopy> found by query
	 */
	public List<EmployeeCopy> findByProperty(String propertyName, Object value);

	public List<EmployeeCopy> findByName(Object name);

	public List<EmployeeCopy> findByTelephone(Object telephone);

	public List<EmployeeCopy> findByEmail(Object email);

	public List<EmployeeCopy> findByPosition(Object position);

	public List<EmployeeCopy> findByLevel(Object level);

	/**
	 * Find all EmployeeCopy entities.
	 * 
	 * @return List<EmployeeCopy> all EmployeeCopy entities
	 */
	public List<EmployeeCopy> findAll();
}