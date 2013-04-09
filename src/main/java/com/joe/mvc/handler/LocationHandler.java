package com.joe.mvc.handler;

import java.util.ArrayList;
import java.util.List;

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

@Service("locationHandler")
public class LocationHandler extends AbstractHandler{
	
	@Resource(name="receiveMsgService")
	private ReceiveMsgService receiveMsgService;
	
	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	
	@Autowired
	private Config config;
	
	public SendMsg handle(ReceiveMsg msg){
		receiveMsgService.add(msg);
		UserInfo user =  userInfoService.getByUserName(msg.getFromUserName());
		String sex = user.getSex().equals("男") ? "女" : "男";
		List<ReceiveMsg> msgs = receiveMsgService.getNearestUser(msg.getLocationY(), msg.getLocationX(), sex);
		
		SendMsg sendMsg = new SendMsg();
		sendMsg.setCreateTime(System.currentTimeMillis());
		sendMsg.setFromUserName(config.getAccount());
		sendMsg.setMsgType(MsgType.TEXT.getName());
		sendMsg.setFuncFlag("0");
		sendMsg.setToUserName(msg.getFromUserName());
		sendMsg.setContent(createContent(msgs));
		return sendMsg;
	}
	
	private String createContent(List<ReceiveMsg> msgs){
		List<ReceiveMsg> results = removeDuplicateMsgs(msgs);
		String users = "";
		String content = "";
		if(results.size() > 0){
			for(int i = 0 ; i < results.size(); i++){
				ReceiveMsg m = results.get(i);
				String userName = m.getToUserName() == null ? "" : m.getToUserName();
				users += userName;
				if(i < results.size()-1)
					users += "，";
			}
			content = config.getResult().replaceAll("@USERS", users);
		}else{
			content = config.getNoResult();
		}
		return content;
	}

	private List<ReceiveMsg> removeDuplicateMsgs(List<ReceiveMsg> msgs){
		List<ReceiveMsg> results = new ArrayList<ReceiveMsg>();
		for(ReceiveMsg m : msgs){
			if(!isExists(results,m))
				results.add(m);
		}
		return results;
	}
	
	private boolean isExists(List<ReceiveMsg> msgs, ReceiveMsg msg){
		for(ReceiveMsg m : msgs){
			if(m.getFromUserName().equals(msg.getFromUserName()))
				return true;
		}
		return false;
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
