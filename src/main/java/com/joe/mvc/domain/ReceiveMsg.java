package com.joe.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TReseiveMsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_RESEIVE_MSG")
public class ReceiveMsg implements java.io.Serializable {
	private static final long serialVersionUID = -5527566248002296042L;
	// Fields
	@Id
	@Column(name = "MsgId")
	private Long msgId;
	
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
	
	@Column(name = "PicUrl")
	private String picUrl;
	
	@Column(name = "Location_X")
	private Double locationX;
	
	@Column(name = "Location_Y")
	private Double locationY;
	
	@Column(name = "Scale")
	private Integer scale;
	
	@Column(name = "Label")
	private String label;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Url")
	private String url;
	
	@Column(name = "Event")
	private String event;
	
	@Column(name = "Latitude")
	private Double latitude;
	
	@Column(name = "Longitude")
	private Double longitude;
	
	@Column(name = "A_Precision")
	private Double precision;
	
	@Column(name = "EventKey")
	private String eventKey;

	// Constructors

	/** default constructor */
	public ReceiveMsg() {
	}

	/** minimal constructor */
	public ReceiveMsg(Long msgId, Long createTime) {
		this.msgId = msgId;
		this.createTime = createTime;
	}

	/** full constructor */
	public ReceiveMsg(Long msgId, String toUserName, String fromUserName,
			Long createTime, String msgType, String content,
			String picUrl, Double locationX, Double locationY, Integer scale,
			String label, String title, String description, String url,
			String event, Double latitude, Double longitude, Double precision,String eventKey) {
		this.msgId = msgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.content = content;
		this.picUrl = picUrl;
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label = label;
		this.title = title;
		this.description = description;
		this.url = url;
		this.event = event;
		this.latitude = latitude;
		this.longitude = longitude;
		this.precision = precision;
		this.eventKey = eventKey;
	}

	// Property accessors

	public Long getMsgId() {
		return this.msgId;
	}

	public void setMsgId(Long msgId) {
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

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Double getLocationX() {
		return this.locationX;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	public Double getLocationY() {
		return this.locationY;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	public Integer getScale() {
		return this.scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getPrecision() {
		return this.precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	
}