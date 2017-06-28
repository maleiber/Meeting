package data;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * VisitNum entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see data.VisitNum
 * @author MyEclipse Persistence Tools
 */
public class VisitNumDAO implements IVisitNumDAO {
	// property constants
	public static final String NUM = "num";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * VisitNumDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VisitNum entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VisitNum entity) {
		EntityManagerHelper.log("saving VisitNum instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent VisitNum entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VisitNumDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VisitNum entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VisitNum entity) {
		EntityManagerHelper.log("deleting VisitNum instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(VisitNum.class, entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = VisitNumDAO.update(entity);
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
	public VisitNum update(VisitNum entity) {
		
		EntityManager em=getEntityManager();

		EntityManagerHelper.log("updating VisitNum instance", Level.INFO, null);
		try {
			
		//make VisitNum result= getEntityManager().merge(entity) to ;
		VisitNum result;
		em.getTransaction().begin();
		
		result= getEntityManager().merge(entity);
		
		em.getTransaction().commit();

		
		EntityManagerHelper.log("update successful", Level.INFO, null);
		    return result;
		} catch (Exception re) {
					EntityManagerHelper.log("update failed", Level.SEVERE, re);
		   // throw re;
		}
		return null;
	}

	public VisitNum findById(Integer id) {
		EntityManagerHelper.log("finding VisitNum instance with id: " + id, Level.INFO, null);
		try {
			VisitNum instance = getEntityManager().find(VisitNum.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all VisitNum entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VisitNum property to query
	 * @param value
	 *            the property value to match
	 * @return List<VisitNum> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<VisitNum> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding VisitNum instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from VisitNum model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<VisitNum> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	/**
	 * Find all VisitNum entities.
	 * 
	 * @return List<VisitNum> all VisitNum entities
	 */
	@SuppressWarnings("unchecked")
	public List<VisitNum> findAll() {
		EntityManagerHelper.log("finding all VisitNum instances", Level.INFO, null);
		try {
			final String queryString = "select model from VisitNum model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}