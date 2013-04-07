package com.joe.mvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.mvc.domain.SendMsg;

/**
 * Service for processing SendMsgs
 * 
 */
@Service("sendMsgService")
@Transactional
public class SendMsgService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all SendMsgs
	 * 
	 * @return a list of SendMsgs
	 */
	public List<SendMsg> getAll() {
		logger.debug("Retrieving all SendMsgs");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  SendMsg");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single SendMsg
	 */
	public SendMsg get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing SendMsg first
		SendMsg SendMsg = (SendMsg) session.get(SendMsg.class, id);
		
		return SendMsg;
	}
	/**
	 * Adds a new SendMsg
	 */
	public void add(SendMsg SendMsg) {
		logger.debug("Adding new SendMsg");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Save
		session.save(SendMsg);
	}
	
	/**
	 * Deletes an existing SendMsg
	 * @param id the id of the existing SendMsg
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing SendMsg");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing SendMsg first
		SendMsg SendMsg = (SendMsg) session.get(SendMsg.class, id);
		
		// Delete 
		session.delete(SendMsg);
	}
	
	
}
