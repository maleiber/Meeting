package data;

import java.util.List;

/**
 * Interface for DepartmentDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDepartmentDAO {
	/**
	 * Perform an initial save of a previously unsaved Department entity. All
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
	 * IDepartmentDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Department entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Department entity);

	/**
	 * Delete a persistent Department entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDepartmentDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Department entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Department entity);

	/**
	 * Persist a previously saved Department entity and return it or a copy of
	 * it to the sender. A copy of the Department entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IDepartmentDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Department entity to update
	 * @return Department the persisted Department entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Department update(Department entity);

	public Department findById(Integer id);

	/**
	 * Find all Department entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Department property to query
	 * @param value
	 *            the property value to match
	 * @return List<Department> found by query
	 */
	public List<Department> findByProperty(String propertyName, Object value);

	public List<Department> findByDepartmentName(Object departmentName);

	public List<Department> findByDepartmentNum(Object departmentNum);

	/**
	 * Find all Department entities.
	 * 
	 * @return List<Department> all Department entities
	 */
	public List<Department> findAll();
}