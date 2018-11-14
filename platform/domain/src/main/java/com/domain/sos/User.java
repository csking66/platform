package com.domain.sos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.domain.base.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @ClassName: User
* @Description: 
* @date 2018年11月13日
*
*/
@ApiModel(value = "User ", description = "用户")
@Entity
@Table(name = "user")
public class User extends BaseEntity<Long>{

	private static final long serialVersionUID = 6597326777478953795L;
	
	@ApiModelProperty(value = "企业Id")
	private Long ccid;
	
	@ApiModelProperty(value = "企业")
	@Column(length = 100)
	private String company;
	
	@ApiModelProperty(value = "姓名")
	@Column(length = 50)
	private String name;
	
	@ApiModelProperty(value = "账号")
	@Column(length = 50)
	private String accout;
	
	@ApiModelProperty(value = "密码")
	@Column(length = 50)
	private String password;
	
	@ApiModelProperty(value = "邮件")
	@Column(length = 100)
	private String email;
	
	@ApiModelProperty(value = "入职日期")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date entryTime;

	public Long getCcid() {
		return ccid;
	}

	public void setCcid(Long ccid) {
		this.ccid = ccid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

}
