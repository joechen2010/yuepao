package com.joe.mvc.handler;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joe.mvc.common.MsgType;
import com.joe.mvc.config.Config;
import com.joe.mvc.domain.ReceiveMsg;
import com.joe.mvc.domain.SendMsg;
import com.joe.mvc.domain.UserInfo;
import com.joe.mvc.service.ReceiveMsgService;
import com.joe.mvc.service.UserInfoService;

@Service("textHandler")
public class TextHandler extends AbstractHandler{
	
	@Resource(name="receiveMsgService")
	private ReceiveMsgService receiveMsgService;
	
	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	
	@Autowired
	private Config config;
	
	public SendMsg handle(ReceiveMsg msg){
		receiveMsgService.add(msg);
		SendMsg sendMsg = new SendMsg();
		sendMsg.setCreateTime(System.currentTimeMillis());
		sendMsg.setFromUserName(config.getAccount());
		sendMsg.setMsgType(MsgType.TEXT.getName());
		sendMsg.setFuncFlag("0");
		sendMsg.setToUserName(msg.getFromUserName());
		UserInfo existsUser =  userInfoService.getByUserName(msg.getFromUserName());
		if(existsUser != null){
			sendMsg.setContent(config.getNewLocationReply());
		}else{
			if(msg.getContent().startsWith("男") || msg.getContent().startsWith("女") ){
				UserInfo user = new UserInfo(msg.getFromUserName(), msg.getContent().substring(0, 1), msg.getContent().substring(1, msg.getContent().length()));
				userInfoService.add(user);
				sendMsg.setContent(config.getNewLocationReply());
			}else{
				sendMsg.setContent(config.getNewReply());
			}
		}
		
		return sendMsg;
	}

	
	public ReceiveMsgService getReceiveMsgService() {
		return receiveMsgService;
	}

	public void setReceiveMsgService(ReceiveMsgService receiveMsgService) {
		this.receiveMsgService = receiveMsgService;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}


	public UserInfoService getUserInfoService() {
		return userInfoService;
	}


	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
}
