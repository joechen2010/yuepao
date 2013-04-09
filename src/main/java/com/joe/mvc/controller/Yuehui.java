package com.joe.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.joe.mvc.common.MessageConverter;
import com.joe.mvc.common.MsgType;
import com.joe.mvc.common.SpringContextHolder;
import com.joe.mvc.config.Config;
import com.joe.mvc.domain.ReceiveMsg;
import com.joe.mvc.domain.SendMsg;
import com.joe.mvc.handler.EventHandler;
import com.joe.mvc.handler.LocationHandler;
import com.joe.mvc.handler.TextHandler;
import com.mysql.jdbc.StringUtils;

public class Yuehui extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	@Autowired
	private TextHandler textHandler;
	
	@Autowired
	private LocationHandler locationHandler;
	
	@Autowired
	private EventHandler eventHandler;
	
	@Autowired
	private Config config;
	
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
		  
		  String signature = request.getParameter("signature"); 
		  String timestamp = request.getParameter("timestamp");
		  String nonce = request.getParameter("nonce"); 
		  String echostr = request.getParameter("echostr");
		  System.out.println("?signature="+signature+"&timestamp="+timestamp+"&nonce="+nonce+"&echostr="+echostr);
		  String[] ArrTmp = { getConfig().getToken(), timestamp, nonce }; 
		  Arrays.sort(ArrTmp); 
		  StringBuffer sb = new StringBuffer(); 
		  for (int i = 0; i < ArrTmp.length; i++) {
			  sb.append(ArrTmp[i]); 
		  } 
		  String pwd = Encrypt(sb.toString());
		  if(pwd.equals(signature)){ 
			  if(!"".equals(echostr) && echostr != null){ 
				  response.getWriter().print(echostr); 
			  }
		   }
	  	}
	  
		public void doPost(HttpServletRequest request, HttpServletResponse response)
		             throws ServletException, IOException  {
			
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
	            		 reply = getTextHandler().handle(msg);
	            	 }else if (MsgType.EVENT.getName().equals(msg.getMsgType())){
	            		 reply = getEventHandler().handle(msg);
	            	 }else{
	            		 reply = getLocationHandler().handle(msg);
	            	 }
	             }
	             resp = MessageConverter.generateSendMsg(reply);
	             pw.println(resp);
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
		         
		}
		
		
		@Override
		public void destroy() {
			super.destroy();
		}

		@Override
		public void init() throws ServletException {
			super.init();
		}

		public String Encrypt(String strSrc) {
			MessageDigest md = null;
			String strDes = null;

			byte[] bt = strSrc.getBytes();
			try {
				md = MessageDigest.getInstance("SHA-1");
				md.update(bt);
				strDes = bytes2Hex(md.digest()); //to HexString
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Invalid algorithm.");
				return null;
			}
			return strDes;
		}

		public String bytes2Hex(byte[] bts) {
			String des = "";
			String tmp = null;
			for (int i = 0; i < bts.length; i++) {
				tmp = (Integer.toHexString(bts[i] & 0xFF));
				if (tmp.length() == 1) {
					des += "0";
				}
				des += tmp;
			}
			return des;
		}
		
		public boolean isChinese(String str){  
		     boolean result=false;  
		     for (int i = 0; i < str.length(); i++){  
		          int chr1 = (char) str.charAt(i);  
		          if(chr1>=19968&&chr1<=171941){  
		              result = true;  
		          } 
		     }  
		     return result;  
		}

		public TextHandler getTextHandler() {
			if(textHandler == null){
				textHandler = (TextHandler)SpringContextHolder.getBean("textHandler");
			}
			return textHandler;
		}

		public void setTextHandler(TextHandler textHandler) {
			this.textHandler = textHandler;
		}

		public LocationHandler getLocationHandler() {
			if(locationHandler == null){
				locationHandler = (LocationHandler)SpringContextHolder.getBean("locationHandler");
			}
			return locationHandler;
		}

		public void setLocationHandler(LocationHandler locationHandler) {
			this.locationHandler = locationHandler;
		}

		public Config getConfig() {
			if(config == null){
				config = (Config)SpringContextHolder.getBean("config");
			}
			return config;
		}

		public void setConfig(Config config) {
			this.config = config;
		}

		public EventHandler getEventHandler() {
			if(eventHandler == null){
				eventHandler = (EventHandler)SpringContextHolder.getBean("eventHandler");
			}
			return eventHandler;
		}

		public void setEventHandler(EventHandler eventHandler) {
			this.eventHandler = eventHandler;
		} 
		
		
}
