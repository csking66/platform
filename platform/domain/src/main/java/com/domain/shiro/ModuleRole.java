package com.domain.shiro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@ApiModel(value = "ModuleRole ", description = "页面操作按钮和权限绑定表")
@Entity
@Table(name = "module_role")
public class ModuleRole {

	@ApiModelProperty(value = "编号", required = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty(value = "按钮ID")
	@OneToOne(cascade={CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="moduleId")
	private Module module;
	
	@ApiModelProperty(value = "权限Id")
	private String roleId;
	
	
	
	
}
