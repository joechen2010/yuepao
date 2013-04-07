package com.joe.mvc.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joe.mvc.common.MessageConverter;
import com.joe.mvc.common.MsgType;
import com.joe.mvc.domain.ReceiveMsg;
import com.joe.mvc.domain.SendMsg;
import com.joe.mvc.handler.LocationHandler;
import com.joe.mvc.handler.TextHandler;
import com.mysql.jdbc.StringUtils;


/**
 * Handles and retrieves person request
 */
@Controller
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="textHandler")
	private TextHandler textHandler;
	
	@Resource(name="locationHandler")
	private LocationHandler locationHandler;
	
	/**
	 * Handles and retrieves all persons and show it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping("/yuehui")
    public void getPersons(HttpServletRequest request, HttpServletResponse response) {
    	
    	logger.debug("Received request ");
    	String resp = null;
         try {
        	 response.setContentType("text/html;charset=UTF-8");
             PrintWriter pw = response.getWriter();
             String wxMsgXml = IOUtils.toString(request.getInputStream(),"utf-8");
             ReceiveMsg msg = MessageConverter.getReceiveMsg(wxMsgXml);
             SendMsg reply = null;
             if(msg != null && !StringUtils.isNullOrEmpty(msg.getMsgType())){
            	 if(MsgType.TEXT.getName().equals(msg.getMsgType())){
            		 if(!StringUtils.isNullOrEmpty(msg.getContent()))
            			 msg.setContent(msg.getContent().trim());
            		 reply = textHandler.handle(msg);
            	 }else{
            		 reply = locationHandler.handle(msg);
            	 }
             }
             resp = MessageConverter.getSendMsg(reply);
             pw.println(resp);
         } catch (Exception e) {
             e.printStackTrace();
         }
	}
    
    
    
}
