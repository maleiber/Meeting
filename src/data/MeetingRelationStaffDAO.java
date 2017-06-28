package data;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 	* A data access object (DAO) providing persistence and search support for MeetingRelationStaff entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see data.MeetingRelationStaff
  * @author MyEclipse Persistence Tools 
 */
public class MeetingRelationStaffDAO  implements IMeetingRelationStaffDAO{
	//property constants





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved MeetingRelationStaff entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   MeetingRelationStaffDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity MeetingRelationStaff entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(MeetingRelationStaff entity) {
    				EntityManagerHelper.log("saving MeetingRelationStaff instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
	public List<MeetingRelationStaff> findBystaffid(Object staffid) {
		return findByProperty("staffId", staffid);
	}
    
    /**
	 Delete a persistent MeetingRelationStaff entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   MeetingRelationStaffDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity MeetingRelationStaff entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(MeetingRelationStaff entity) {
    		EntityManager em=getEntityManager();
    				EntityManagerHelper.log("deleting MeetingRelationStaff instance", Level.INFO, null);
	        try {
	        em.getTransaction().begin();	
        	entity = em.getReference(MeetingRelationStaff.class, entity.getId());
        	
            em.remove(entity);
            em.getTransaction().commit();
            
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved MeetingRelationStaff entity and return it or a copy of it to the sender. 
	 A copy of the MeetingRelationStaff entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = MeetingRelationStaffDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity MeetingRelationStaff entity to update
	 @return MeetingRelationStaff the persisted MeetingRelationStaff entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public MeetingRelationStaff update(MeetingRelationStaff entity) {
    		EntityManager em=getEntityManager();
    		EntityManagerHelper.log("updating MeetingRelationStaff instance", Level.INFO, null);
	        try {
	        	MeetingRelationStaff result;
				em.getTransaction().begin(); 
                result = em.merge(entity);
                em.getTransaction().commit();
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public MeetingRelationStaff findById( MeetingRelationStaffId id) {
    				EntityManagerHelper.log("finding MeetingRelationStaff instance with id: " + id, Level.INFO, null);
	        try {
            MeetingRelationStaff instance = getEntityManager().find(MeetingRelationStaff.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all MeetingRelationStaff entities with a specific property value.  
	 
	  @param propertyName the name of the MeetingRelationStaff property to query
	  @param value the property value to match
	  	  @return List<MeetingRelationStaff> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<MeetingRelationStaff> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding MeetingRelationStaff instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from MeetingRelationStaff model where model." 
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
	 * Find all MeetingRelationStaff entities.
	  	  @return List<MeetingRelationStaff> all MeetingRelationStaff entities
	 */
	@SuppressWarnings("unchecked")
	public List<MeetingRelationStaff> findAll(
		) {
					EntityManagerHelper.log("finding all MeetingRelationStaff instances", Level.INFO, null);
			try {
			final String queryString = "select model from MeetingRelationStaff model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
	
	public int getcount() 
	{
					EntityManagerHelper.log("finding all MeetingRelationStaff count", Level.INFO, null);
			try {
			final String queryString = "select max( model."+"id"+" )from MeetingRelationStaff model";
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