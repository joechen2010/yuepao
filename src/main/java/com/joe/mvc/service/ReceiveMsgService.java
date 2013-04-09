package com.joe.mvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.mvc.domain.ReceiveMsg;

/**
 * Service for processing ReceiveMsgs
 * 
 */
@Service("receiveMsgService")
@Transactional
public class ReceiveMsgService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all ReceiveMsgs
	 * 
	 * @return a list of ReceiveMsgs
	 */
	public List<ReceiveMsg> getAll() {
		logger.debug("Retrieving all ReceiveMsgs");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  ReceiveMsg");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single ReceiveMsg
	 */
	public ReceiveMsg get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing ReceiveMsg first
		ReceiveMsg ReceiveMsg = (ReceiveMsg) session.get(ReceiveMsg.class, id);
		
		return ReceiveMsg;
	}
	/**
	 * Adds a new ReceiveMsg
	 */
	public void add(ReceiveMsg ReceiveMsg) {
		logger.debug("Adding new ReceiveMsg");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Save
		session.save(ReceiveMsg);
	}
	
	/**
	 * Deletes an existing ReceiveMsg
	 * @param id the id of the existing ReceiveMsg
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing ReceiveMsg");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing ReceiveMsg first
		ReceiveMsg ReceiveMsg = (ReceiveMsg) session.get(ReceiveMsg.class, id);
		
		// Delete 
		session.delete(ReceiveMsg);
	}
	
	
	public List<ReceiveMsg> getNearestUser(Double longitude, Double latitude, String sex) {
		
		  logger.debug("Retrieving nearest user");	
		
		  String sql = "select m.FromUserName,m.CreateTime,m.MsgType,m.Content,m.MsgId,m.PicUrl,m.Location_X,m.Location_Y,m.Scale,"
				  +"m.Label,m.Title,m.Description,m.Url,m.Event,m.Latitude,m.Longitude,m.A_Precision,m.EventKey,u.Mobile as ToUserName"
				  +" from T_RESEIVE_MSG m left join T_USER_INFO u on m.FromUserName = u.UserName "
				  +" where m.Location_X > " 
				  + latitude + "-1 and m.Location_X < " + latitude + "+1 and m.Location_Y > "
				  + longitude +"-1 and m.Location_Y < "+ longitude +"+1 "
				  + " and u.sex = '"+ sex +"'"
				  +" order by ACOS(SIN((" 
				  + latitude + " * 3.1415) / 180 ) *SIN((m.Location_X * 3.1415) / 180 ) +COS((" 
				  + latitude + " * 3.1415) / 180 ) * COS((m.Location_X * 3.1415) / 180 ) *COS(("
				  + longitude +"* 3.1415) / 180 - (m.Location_Y * 3.1415) / 180 ) ) * 6380 asc limit 5";
		  
		  /*String sql = "select * from T_RESEIVE_MSG where sqrt( ( (("
				  + longitude +"-Location_Y)*PI()*12656*cos(((" + latitude + "+Location_X)/2)*PI()/180)/180) * (("
				  + longitude +"-Location_Y)*PI()*12656*cos (((" + latitude + "+Location_X)/2)*PI()/180)/180) ) + ( ((" 
				  + latitude + "-Location_X)*PI()*12656/180) * ((" + latitude + "-Location_X)*PI()*12656/180) ) )<2";*/ 
		  Session session = sessionFactory.getCurrentSession();
		   SQLQuery sqlQuery = session.createSQLQuery(sql); 
		  
		   sqlQuery.addEntity(ReceiveMsg .class); 

		   List<ReceiveMsg> list = sqlQuery.list(); 
		
		// Retrieve all
		   return  list;
	}
	
}
