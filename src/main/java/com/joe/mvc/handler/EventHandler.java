package com.joe.mvc.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joe.mvc.common.MsgType;
import com.joe.mvc.config.Config;
import com.joe.mvc.domain.ReceiveMsg;
import com.joe.mvc.domain.SendMsg;

@Service("eventHandler")
public class EventHandler extends AbstractHandler{
	
	@Autowired
	private Config config;
	
	public SendMsg handle(ReceiveMsg msg){
		SendMsg sendMsg = new SendMsg();
		sendMsg.setCreateTime(System.currentTimeMillis());
		sendMsg.setFromUserName(config.getAccount());
		sendMsg.setMsgType(MsgType.TEXT.getName());
		sendMsg.setFuncFlag("0");
		sendMsg.setToUserName(msg.getFromUserName());
		sendMsg.setContent(config.getNewReply());
		return sendMsg;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
	
}
