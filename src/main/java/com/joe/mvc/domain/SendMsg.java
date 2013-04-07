package com.joe.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TSendMsg entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "T_SEND_MSG")
public class SendMsg implements java.io.Serializable {

	private static final long serialVersionUID = -5527586248002296042L;
	
	// Fields
	@Id
	@Column(name = "MsgId")
	@GeneratedValue
	private Integer msgId;
	
	@Column(name = "ToUserName")
	private String toUserName;
	
	@Column(name = "FromUserName")
	private String fromUserName;
	
	@Column(name = "CreateTime")
	private Long createTime;
	
	@Column(name = "MsgType")
	private String msgType;
	
	@Column(name = "Content")
	private String content;
	
	@Column(name = "FuncFlag")
	private String funcFlag;
	
	@Column(name = "ArticleCount")
	private Integer articleCount;
	
	@Column(name = "MusicUrl")
	private String musicUrl;
	
	@Column(name = "HQMusicUrl")
	private String hqmusicUrl;
	
	@Column(name = "Articles")
	private String articles;

	// Constructors

	/** default constructor */
	public SendMsg() {
	}

	/** minimal constructor */
	public SendMsg(Long createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public SendMsg(String toUserName, String fromUserName,
			Long createTime, String msgType, String content,
			String funcFlag, String musicUrl, String hqmusicUrl, String articles, Integer articleCount) {
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.content = content;
		this.funcFlag = funcFlag;
		this.musicUrl = musicUrl;
		this.hqmusicUrl = hqmusicUrl;
		this.articles = articles;
		this.articleCount = articleCount;
	}

	// Property accessors

	public Integer getMsgId() {
		return this.msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getToUserName() {
		return this.toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return this.fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFuncFlag() {
		return this.funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	public String getMusicUrl() {
		return this.musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqmusicUrl() {
		return this.hqmusicUrl;
	}

	public void setHqmusicUrl(String hqmusicUrl) {
		this.hqmusicUrl = hqmusicUrl;
	}

	public String getArticles() {
		return this.articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	
	
}