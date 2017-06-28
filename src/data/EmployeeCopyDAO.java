package data;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * EmployeeCopy entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see data.EmployeeCopy
 * @author MyEclipse Persistence Tools
 */
public class EmployeeCopyDAO implements IEmployeeCopyDAO {
	// property constants
	public static final String NAME = "name";
	public static final String TELEPHONE = "telephone";
	public static final String EMAIL = "email";
	public static final String POSITION = "position";
	public static final String LEVEL = "level";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * EmployeeCopyDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            EmployeeCopy entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(EmployeeCopy entity) {
		EntityManagerHelper.log("saving EmployeeCopy instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent EmployeeCopy entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EmployeeCopyDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            EmployeeCopy entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(EmployeeCopy entity) {
		
		EntityManager em=getEntityManager();

		
		EntityManagerHelper.log("deleting EmployeeCopy instance", Level.INFO, null);
		try {
			
			
			entity = getEntityManager().getReference(EmployeeCopy.class, entity.getStaffId());
			
			em.getTransaction().begin();
			getEntityManager().remove(entity);
			em.getTransaction().commit();
			
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = EmployeeCopyDAO.update(entity);
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
	public EmployeeCopy update(EmployeeCopy entity) {
		
		EntityManager em=getEntityManager();
		
		EntityManagerHelper.log("updating EmployeeCopy instance", Level.INFO, null);
		try {
			EmployeeCopy result;
			em.getTransaction().begin(); 
			result= getEntityManager().merge(entity);
			em.getTransaction().commit();  
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public EmployeeCopy findById(Integer id) {
		EntityManagerHelper.log("finding EmployeeCopy instance with id: " + id, Level.INFO, null);
		try {
			EmployeeCopy instance = getEntityManager().find(EmployeeCopy.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all EmployeeCopy entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the EmployeeCopy property to query
	 * @param value
	 *            the property value to match
	 * @return List<EmployeeCopy> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeCopy> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding EmployeeCopy instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from EmployeeCopy model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<EmployeeCopy> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<EmployeeCopy> findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	public List<EmployeeCopy> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<EmployeeCopy> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List<EmployeeCopy> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	/**
	 * Find all EmployeeCopy entities.
	 * 
	 * @return List<EmployeeCopy> all EmployeeCopy entities
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeCopy> findAll() {
		EntityManagerHelper.log("finding all EmployeeCopy instances", Level.INFO, null);
		//getEntityManager().flush();
		try {
			final String queryString = "select model from EmployeeCopy model";
			Query query = getEntityManager().createQuery(queryString).setHint(  
	                "toplink.refresh", true);  
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	public List<EmployeeCopy> pagedFindAll(int startIndex,int count){
		int rowStartIdx = Math.max(0,startIndex);
		int rowCount = Math.max(0,count);

		
		EntityManagerHelper.log("finding all EmployeeCopy instances", Level.INFO, null);
		try {
			final String queryString = "select model from EmployeeCopy model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdx > 0) {
				query.setFirstResult(rowStartIdx);
			}
			if (rowCount > 0) {
	    		query.setMaxResults(rowCount);    
	    	}

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	public int getcount() 
	{
					EntityManagerHelper.log("finding all employee_copy count", Level.INFO, null);
			try {
			final String queryString = "select max( model."+"staffId"+" )from EmployeeCopy model";
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