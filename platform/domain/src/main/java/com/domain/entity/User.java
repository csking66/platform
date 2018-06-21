package com.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "User ", description = "用户")
public class User implements Serializable{

	private static final long serialVersionUID = 2424944657192343647L;
	
	@ApiModelProperty(value = "ID")
	private Long id;
	
	@ApiModelProperty(value = "姓名")
	private String name;
	
	@ApiModelProperty(value = "年龄")
	private Integer age;
	
	@ApiModelProperty(value = "备注")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
	
	

}
