package data;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Department entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see data.Department
 * @author MyEclipse Persistence Tools
 */
public class DepartmentDAO implements IDepartmentDAO {
	// property constants
	public static final String DEPARTMENT_NAME = "departmentName";
	public static final String DEPARTMENT_NUM = "departmentNum";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * DepartmentDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Department entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Department entity) {
		EntityManagerHelper.log("saving Department instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Department entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DepartmentDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Department entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Department entity) {
		EntityManager em=getEntityManager();
		EntityManagerHelper.log("deleting Department instance", Level.INFO, null);
		try {
			em.getTransaction().begin(); 
			//entity = getEntityManager().getReference(Department.class, entity.getDepartmentId());
			em.remove(em.merge(entity));
			em.getTransaction().commit(); 
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = DepartmentDAO.update(entity);
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
	public Department update(Department entity) {
		EntityManager em=getEntityManager();
		EntityManagerHelper.log("updating Department instance", Level.INFO, null);
		try {
			Department  result;
			em.getTransaction().begin(); 
			result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			em.getTransaction().commit(); 
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Department findById(Integer id) {
		EntityManagerHelper.log("finding Department instance with id: " + id, Level.INFO, null);
		try {
			Department instance = getEntityManager().find(Department.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Department entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Department property to query
	 * @param value
	 *            the property value to match
	 * @return List<Department> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Department instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Department model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Department> findByDepartmentName(Object departmentName) {
		return findByProperty(DEPARTMENT_NAME, departmentName);
	}

	public List<Department> findByDepartmentNum(Object departmentNum) {
		return findByProperty(DEPARTMENT_NUM, departmentNum);
	}

	/**
	 * Find all Department entities.
	 * 
	 * @return List<Department> all Department entities
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findAll() {
		EntityManagerHelper.log("finding all Department instances", Level.INFO, null);
		try {
			final String queryString = "select model from Department model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	public int getcount() 
	{
					EntityManagerHelper.log("finding all Department count", Level.INFO, null);
			try {
			final String queryString = "select max( model."+"departmentId"+" )from Department model";
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