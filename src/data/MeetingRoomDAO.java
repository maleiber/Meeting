package data;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 	* A data access object (DAO) providing persistence and search support for MeetingRoom entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see data.MeetingRoom
  * @author MyEclipse Persistence Tools 
 */
public class MeetingRoomDAO  implements IMeetingRoomDAO{
	//property constants
	public static final String MEETING_ROOM_NAME = "meetingRoomName";
	public static final String CAPACITY = "capacity";
	public static final String ROOM_NUMBLER = "roomNumbler";
	public static final String REMARK = "remark";
	public static final String CURRENT_ATATE = "currentAtate";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved MeetingRoom entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   MeetingRoomDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity MeetingRoom entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(MeetingRoom entity) {
    				EntityManagerHelper.log("saving MeetingRoom instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent MeetingRoom entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   MeetingRoomDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity MeetingRoom entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(MeetingRoom entity) {
    	EntityManager em=getEntityManager();
    				EntityManagerHelper.log("deleting MeetingRoom instance", Level.INFO, null);
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
	 Persist a previously saved MeetingRoom entity and return it or a copy of it to the sender. 
	 A copy of the MeetingRoom entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = MeetingRoomDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity MeetingRoom entity to update
	 @return MeetingRoom the persisted MeetingRoom entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public MeetingRoom update(MeetingRoom entity) {
    	EntityManager em=getEntityManager();
    				EntityManagerHelper.log("updating MeetingRoom instance", Level.INFO, null);
	        try {
	        	MeetingRoom result;
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
    
    public MeetingRoom findById( Integer id) {
    				EntityManagerHelper.log("finding MeetingRoom instance with id: " + id, Level.INFO, null);
	        try {
            MeetingRoom instance = getEntityManager().find(MeetingRoom.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all MeetingRoom entities with a specific property value.  
	 
	  @param propertyName the name of the MeetingRoom property to query
	  @param value the property value to match
	  	  @return List<MeetingRoom> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<MeetingRoom> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding MeetingRoom instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from MeetingRoom model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		}
	}			
	public List<MeetingRoom> findByMeetingRoomName(Object meetingRoomName
	) {
		return findByProperty(MEETING_ROOM_NAME, meetingRoomName
		);
	}
	
	public List<MeetingRoom> findByCapacity(Object capacity
	) {
		return findByProperty(CAPACITY, capacity
		);
	}
	
	public List<MeetingRoom> findByRoomNumbler(Object roomNumbler
	) {
		return findByProperty(ROOM_NUMBLER, roomNumbler
		);
	}
	
	public List<MeetingRoom> findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	
	public List<MeetingRoom> findByCurrentAtate(Object currentAtate
	) {
		return findByProperty(CURRENT_ATATE, currentAtate
		);
	}
	
	
	/**
	 * Find all MeetingRoom entities.
	  	  @return List<MeetingRoom> all MeetingRoom entities
	 */
	@SuppressWarnings("unchecked")
	public List<MeetingRoom> findAll(
		) {
					EntityManagerHelper.log("finding all MeetingRoom instances", Level.INFO, null);
			try {
			final String queryString = "select model from MeetingRoom model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
	public int getcount() 
	{
					EntityManagerHelper.log("finding all MeetingRoom count", Level.INFO, null);
			try {
			final String queryString = "select max( model."+"meetingRoomId"+" )from MeetingRoom model";
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