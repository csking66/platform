package com.domain.es;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.domain.base.BaseUuidEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @ClassName: Party
* @Description: 
* @date 2018年11月23日
*
*/
@Table(name = "party")
@Entity
@ApiModel(value = "Party", description = "组织")
public class Party extends BaseUuidEntity<String>{

	private static final long serialVersionUID = 454770973947091607L;
	
	/**
	 * 中文名称
	 */
	@ApiModelProperty(value = "中文名称")
	private String cnName;
	
	/**
	 * 英文名称
	 */
	@ApiModelProperty(value = "英文名称", required = true)
	private String enName;
	
	/**
	 * 父级id.
	 */
	@ApiModelProperty(value = "上级ID", required = true)
	@NotBlank(message = "{es.message.parentId.empty}")
	private String parentId;
	
	/**
	 * 父级ids.
	 */
	@ApiModelProperty(value = "所有上级ID 逗号分隔", required = true)
	private String parentIds;
	
	/**
	 * 节点类型
	 */
	@ApiModelProperty(value = "节点类型")
	private Integer typeId;
	
	/**
	 * 下级节点
	 */
	@ApiModelProperty(value = "下级节点")
	@Transient
	private List<Party> children;

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public List<Party> getChildren() {
		return children;
	}

	public void setChildren(List<Party> children) {
		this.children = children;
	}
	
	
	

}
