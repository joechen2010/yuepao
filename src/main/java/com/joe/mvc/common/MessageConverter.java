package com.joe.mvc.common;

import com.joe.mvc.domain.ReceiveMsg;
import com.joe.mvc.domain.SendMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MessageConverter {
	
	public static ReceiveMsg getReceiveMsg(String xml){
		
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("xml", ReceiveMsg.class);
        xstream.aliasField("ToUserName", ReceiveMsg.class, "toUserName");
        xstream.aliasField("FromUserName", ReceiveMsg.class, "fromUserName");
        xstream.aliasField("CreateTime", ReceiveMsg.class, "createTime");
        xstream.aliasField("MsgType", ReceiveMsg.class, "msgType");
        xstream.aliasField("Content", ReceiveMsg.class, "content");
        xstream.aliasField("MsgId", ReceiveMsg.class, "msgId");
        
        xstream.aliasField("PicUrl", ReceiveMsg.class, "picUrl");
        xstream.aliasField("Location_X", ReceiveMsg.class, "locationX");
        xstream.aliasField("Location_Y", ReceiveMsg.class, "locationY");
        xstream.aliasField("Scale", ReceiveMsg.class, "scale");
        xstream.aliasField("Label", ReceiveMsg.class, "label");
        xstream.aliasField("Title", ReceiveMsg.class, "title");
        
        xstream.aliasField("Description", ReceiveMsg.class, "description");
        xstream.aliasField("Url", ReceiveMsg.class, "url");
        xstream.aliasField("Event", ReceiveMsg.class, "event");
        xstream.aliasField("Latitude", ReceiveMsg.class, "latitude");
        xstream.aliasField("Longitude", ReceiveMsg.class, "longitude");
        xstream.aliasField("Precision", ReceiveMsg.class, "precision");
        xstream.aliasField("EventKey", ReceiveMsg.class, "eventKey");
        
        ReceiveMsg receiveMsg = (ReceiveMsg)xstream.fromXML(xml); 
        return receiveMsg;
	}

	public static String getSendMsg(SendMsg sendMsg){
			
	        XStream xstream = new XStream(new DomDriver()); 
	        xstream.alias("xml", SendMsg.class);
	        xstream.aliasField("ToUserName", SendMsg.class, "toUserName");
	        xstream.aliasField("FromUserName", SendMsg.class, "fromUserName");
	        xstream.aliasField("CreateTime", SendMsg.class, "createTime");
	        xstream.aliasField("MsgType", SendMsg.class, "messageType");
	        xstream.aliasField("Content", SendMsg.class, "content");
	        xstream.aliasField("FuncFlag", SendMsg.class, "funcFlag");
	        
	        //xstream.aliasField("ArticleCount", SendMsg.class, "articleCount");
	        //xstream.aliasField("MusicUrl", SendMsg.class, "musicUrl");
	        //xstream.aliasField("HQMusicUrl", SendMsg.class, "hqmusicUrl");
	        
	        String xml =xstream.toXML(sendMsg);
	        return xml;
	}
	
	public static String generateSendMsg(SendMsg sendMsg){
		StringBuffer sb = new StringBuffer();
		 sb.append("<xml><ToUserName><![CDATA[").append(sendMsg.getToUserName())
	    .append("]]></ToUserName><FromUserName><![CDATA[")
	    .append(sendMsg.getFromUserName())
	    .append("]]></FromUserName><CreateTime>")
	    .append(sendMsg.getCreateTime())
	    .append("</CreateTime><MsgType><![CDATA["+sendMsg.getMsgType()+"]]></MsgType>")
	    .append("<Content><![CDATA[")
	    .append(sendMsg.getContent())
	    .append("]]></Content>")
	 	.append("<FuncFlag>0</FuncFlag></xml>");
		 return sb.toString();
	}
	
}
