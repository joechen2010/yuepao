package com.joe.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * TSendMsg entity. @author MyEclipse Persistence Tools
 */
@NamedQueries({
	@NamedQuery(
		name = "findByName",
		query = "from UserInfo s where s.userName = :userName"
	)
})

@Entity
@Table(name = "T_USER_INFO")
public class UserInfo implements java.io.Serializable {

	private static final long serialVersionUID = -5527586258002296042L;
	
	// Fields
	@Id
	@Column(name = "Id")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "UserName")
	private String userName;
	
	@Column(name = "Sex")
	private String sex;
	
	public UserInfo(){}
	
	public UserInfo(String userName, String sex) {
		
		this.userName = userName;
		this.sex = sex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
}