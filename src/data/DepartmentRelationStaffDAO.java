package data;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 	* A data access object (DAO) providing persistence and search support for DepartmentRelationStaff entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see data.DepartmentRelationStaff
  * @author MyEclipse Persistence Tools 
 */
public class DepartmentRelationStaffDAO  implements IDepartmentRelationStaffDAO{
	//property constants





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved DepartmentRelationStaff entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   DepartmentRelationStaffDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity DepartmentRelationStaff entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(DepartmentRelationStaff entity) {
    				EntityManagerHelper.log("saving DepartmentRelationStaff instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent DepartmentRelationStaff entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   DepartmentRelationStaffDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity DepartmentRelationStaff entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(DepartmentRelationStaff entity) {
    	EntityManager em=getEntityManager();
    				EntityManagerHelper.log("deleting DepartmentRelationStaff instance", Level.INFO, null);
	        try {
	        em.getTransaction().begin(); 	
        //	entity = em.getReference(DepartmentRelationStaff.class, entity.getId());
            em.remove(em.merge(entity));
            em.getTransaction().commit();
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved DepartmentRelationStaff entity and return it or a copy of it to the sender. 
	 A copy of the DepartmentRelationStaff entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = DepartmentRelationStaffDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity DepartmentRelationStaff entity to update
	 @return DepartmentRelationStaff the persisted DepartmentRelationStaff entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public DepartmentRelationStaff update(DepartmentRelationStaff entity) {
    	EntityManager em=getEntityManager();
    				EntityManagerHelper.log("updating DepartmentRelationStaff instance", Level.INFO, null);
	        try {
	        	DepartmentRelationStaff result;
				em.getTransaction().begin(); 
                result = getEntityManager().merge(entity);
                em.getTransaction().commit();
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public DepartmentRelationStaff findById( DepartmentRelationStaffId id) {
    				EntityManagerHelper.log("finding DepartmentRelationStaff instance with id: " + id, Level.INFO, null);
	        try {
            DepartmentRelationStaff instance = getEntityManager().find(DepartmentRelationStaff.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all DepartmentRelationStaff entities with a specific property value.  
	 
	  @param propertyName the name of the DepartmentRelationStaff property to query
	  @param value the property value to match
	  	  @return List<DepartmentRelationStaff> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<DepartmentRelationStaff> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding DepartmentRelationStaff instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from DepartmentRelationStaff model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		}
	}			
	
	/**
	 * Find all DepartmentRelationStaff entities.
	  	  @return List<DepartmentRelationStaff> all DepartmentRelationStaff entities
	 */
	@SuppressWarnings("unchecked")
	public List<DepartmentRelationStaff> findAll(
		) {
					EntityManagerHelper.log("finding all DepartmentRelationStaff instances", Level.INFO, null);
			try {
			final String queryString = "select model from DepartmentRelationStaff model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
	public int getcount() 
	{
					EntityManagerHelper.log("finding all DepartmentRelationStaff count", Level.INFO, null);
			try {
			final String queryString = "select max( model."+"id.id"+" )from DepartmentRelationStaff model";
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