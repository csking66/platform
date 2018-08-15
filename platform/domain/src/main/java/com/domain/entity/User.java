package com.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.domain.base.BaseEntity;

@ApiModel(value = "User ", description = "用户")
@Entity
@Table(name = "user")
public class User extends BaseEntity<Long>{

	private static final long serialVersionUID = 2424944657192343647L;
	
	@ApiModelProperty(value = "姓名")
	@Column(length = 50)
	private String name;
	
	@ApiModelProperty(value = "年龄")
	private Integer age;
	
	@ApiModelProperty(value = "地址")
	@Column(length = 500)
	private String address;
	
	@ApiModelProperty(value = "账号")
	@Column(length = 50)
	private String account;
	
	@ApiModelProperty(value = "密码")
	@Column(length = 50)
	private String password;
	
	@ApiModelProperty(value = "备注")
	@Column(length = 500)
	private String remark;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
