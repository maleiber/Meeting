package data;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 	* A data access object (DAO) providing persistence and search support for Administrator entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see data.Administrator
  * @author MyEclipse Persistence Tools 
 */
public class AdministratorDAO  implements IAdministratorDAO{
	//property constants
	public static final String ADM_NAME = "admName";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Administrator entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   AdministratorDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Administrator entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Administrator entity) {
    				EntityManagerHelper.log("saving Administrator instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent Administrator entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   AdministratorDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Administrator entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Administrator entity) {
    				EntityManagerHelper.log("deleting Administrator instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Administrator.class, entity.getAdmId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Administrator entity and return it or a copy of it to the sender. 
	 A copy of the Administrator entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = AdministratorDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Administrator entity to update
	 @return Administrator the persisted Administrator entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Administrator update(Administrator entity) {
    				EntityManagerHelper.log("updating Administrator instance", Level.INFO, null);
	        try {
            Administrator result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Administrator findById( Integer id) {
    				EntityManagerHelper.log("finding Administrator instance with id: " + id, Level.INFO, null);
	        try {
            Administrator instance = getEntityManager().find(Administrator.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all Administrator entities with a specific property value.  
	 
	  @param propertyName the name of the Administrator property to query
	  @param value the property value to match
	  	  @return List<Administrator> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Administrator> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Administrator instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Administrator model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		}
	}			
	public List<Administrator> findByAdmName(Object admName
	) {
		return findByProperty(ADM_NAME, admName
		);
	}
	
	
	/**
	 * Find all Administrator entities.
	  	  @return List<Administrator> all Administrator entities
	 */
	@SuppressWarnings("unchecked")
	public List<Administrator> findAll(
		) {
					EntityManagerHelper.log("finding all Administrator instances", Level.INFO, null);
			try {
			final String queryString = "select model from Administrator model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
}