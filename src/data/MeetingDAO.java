package data;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sun.istack.internal.FinalArrayList;

/**
 * A data access object (DAO) providing persistence and search support for
 * Meeting entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see data.Meeting
 * @author MyEclipse Persistence Tools
 */
public class MeetingDAO implements IMeetingDAO {
	// property constants
	public static final String MEETING_NAME = "meetingName";
	public static final String PEOPLE_NUM = "peopleNum";
	public static final String MEETING_NOTES = "meetingNotes";
	public static final String MEETINGROOM_ID = "meetingroomId";
	public static final String STATUS = "status";
	public static final String BOOK_NAME = "bookName";
	public static final String START_TIME="startTime";
	public static final String END_TIME="endTime";
	
	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Meeting entity. All
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
	 * MeetingDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Meeting entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Meeting entity) {
		EntityManagerHelper.log("saving Meeting instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Meeting entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MeetingDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Meeting entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Meeting entity) {
		EntityManager em=getEntityManager();
		EntityManagerHelper.log("deleting Meeting instance", Level.INFO, null);
		try {
			em.getTransaction().begin();
			entity = em.getReference(Meeting.class, entity.getMeetingId());
			
			em.remove(entity);
			em.getTransaction().commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Meeting entity and return it or a copy of it
	 * to the sender. A copy of the Meeting entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = MeetingDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Meeting entity to update
	 * @return Meeting the persisted Meeting entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Meeting update(Meeting entity) {
    	EntityManager em=getEntityManager();
    	
    				EntityManagerHelper.log("updating Meeting instance", Level.INFO, null);
	        try {
	           	        Meeting result;
	    				em.getTransaction().begin(); 
	                    result = em.merge(entity);
            			
            			em.getTransaction().commit();
            			EntityManagerHelper.log("update successful", Level.INFO, null); 
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
 	            throw re;
        } catch (Exception re)
	    {
        	
	    }
			return entity;
	       
    }

	public Meeting findById(Integer id) {
		EntityManagerHelper.log("finding Meeting instance with id: " + id, Level.INFO, null);
		try {
			Meeting instance = getEntityManager().find(Meeting.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Meeting entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Meeting property to query
	 * @param value
	 *            the property value to match
	 * @return List<Meeting> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Meeting> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Meeting instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Meeting model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Meeting> findByMultiProperty(
			String meeting_name,int people_num,
			Timestamp start_time,Timestamp end_time,
			String meeting_notes,int meetingroom_id,
			int status,String book_name)
	{
		int firstadd=0;
		EntityManagerHelper.log("multiple finding",Level.INFO,null);
		try{
			String queryString="select model from Meeting model where ";
			if(meeting_name!=null)
			{
				if(firstadd==0)
				{
					firstadd=1;
				}else queryString+=" and ";
				queryString+="model."+MEETING_NAME+"= :meetingNameValue";
			}
			if(start_time!=null)
			{
				if(firstadd==0)
				{
					firstadd=1;
				}else queryString+=" and ";
				queryString+="model."+START_TIME+"= :startTimeValue";
			}
			if(end_time!=null)
			{
				if(firstadd==0)
				{
					firstadd=1;
				}else queryString+=" and ";
				queryString+="model."+END_TIME+"= :endTimeValue";
			}
			if(meeting_notes!=null)
			{
				if(firstadd==0)
				{
					firstadd=1;
				}else queryString+=" and ";
				queryString+="model."+MEETING_NOTES+" like :meetingNotesValue";
			}
			if(book_name!=null)
			{
				if(firstadd==0)
				{
					firstadd=1;
				}else queryString+=" and ";
				queryString+="model."+BOOK_NAME+"= :bookNameValue";
			}
			if(people_num!=-1)
			{
				if(firstadd==0)
				{
					firstadd=1;
				}else queryString+=" and ";
				queryString+="model."+PEOPLE_NUM+"= :peopleNumValue";
			}
			if(meetingroom_id!=-1)
			{
				if(firstadd==0)
				{
					firstadd=1;
				}else queryString+=" and ";
				queryString+="model."+MEETINGROOM_ID+"= :meetingRoomIdValue";
			}
			if(status!=-1)
			{
				if(firstadd==0)
				{
					firstadd=1;
				}else queryString+=" and ";
				queryString+="model."+STATUS+"= :statusValue";
			}
			
			Query query = getEntityManager().createQuery(queryString);
			if(meeting_name!=null)
			{
				query.setParameter("meetingNameValue",meeting_name);
			}
			if(start_time!=null)
			{
				query.setParameter("startTimeValue",start_time);
			}
			if(end_time!=null)
			{
				query.setParameter("endTimeValue",end_time);
			}
			if(meeting_notes!=null)
			{
				query.setParameter("meetingNotesValue", meeting_notes);
			}
			if(book_name!=null)
			{
				query.setParameter("bookNameValue",book_name);
			}
			if(people_num!=-1)
			{
				query.setParameter("peopleNumValue",people_num);
			}
			if(meetingroom_id!=-1)
			{
				query.setParameter("meetingRoomIdValue", meetingroom_id);
			}
			if(status!=-1)
			{
				query.setParameter("statusValue", status);
			}
	
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by multi property failed", Level.SEVERE, re);
			throw re;
		}
		
		
		
	}
	public List<Meeting> findByMeetingName(Object meetingName) {
		return findByProperty(MEETING_NAME, meetingName);
	}

	public List<Meeting> findByPeopleNum(Object peopleNum) {
		return findByProperty(PEOPLE_NUM, peopleNum);
	}

	public List<Meeting> findByMeetingNotes(Object meetingNotes) {
		return findByProperty(MEETING_NOTES, meetingNotes);
	}

	public List<Meeting> findByMeetingroomId(Object meetingroomId) {
		return findByProperty(MEETINGROOM_ID, meetingroomId);
	}

	public List<Meeting> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Meeting> findByBookName(Object bookName) {
		return findByProperty(BOOK_NAME, bookName);
	}

	/**
	 * Find all Meeting entities.
	 * 
	 * @return List<Meeting> all Meeting entities
	 */
	@SuppressWarnings("unchecked")
	public List<Meeting> findAll() {
		EntityManagerHelper.log("finding all Meeting instances", Level.INFO, null);
		try {
			final String queryString = "select model from Meeting model";
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
			final String queryString = "select max( model."+"meetingId"+" )from Meeting model";
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