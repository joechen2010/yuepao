package com.joe.mvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.mvc.domain.UserInfo;

/**
 * Service for processing UserInfos
 * 
 */
@Service("userInfoService")
@Transactional
public class UserInfoService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all UserInfos
	 * 
	 * @return a list of UserInfos
	 */
	public List<UserInfo> getAll() {
		logger.debug("Retrieving all UserInfos");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  UserInfo");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single UserInfo
	 */
	public UserInfo get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing UserInfo first
		UserInfo UserInfo = (UserInfo) session.get(UserInfo.class, id);
		
		return UserInfo;
	}
	/**
	 * Adds a new UserInfo
	 */
	public void add(UserInfo UserInfo) {
		logger.debug("Adding new UserInfo");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Save
		session.save(UserInfo);
	}
	
	/**
	 * Deletes an existing UserInfo
	 * @param id the id of the existing UserInfo
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing UserInfo");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing UserInfo first
		UserInfo UserInfo = (UserInfo) session.get(UserInfo.class, id);
		
		// Delete 
		session.delete(UserInfo);
	}
	
	
	public UserInfo getByUserName( String userName ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.getNamedQuery("findByName")
				.setString("userName", userName);
		UserInfo UserInfo = (UserInfo) query.uniqueResult();
		
		return UserInfo;
	}
	
}
