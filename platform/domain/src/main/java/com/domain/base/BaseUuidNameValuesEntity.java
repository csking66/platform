package com.domain.base;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法.
 * <p>
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonPropertyOrder({"name", "nameValues"})
public abstract class BaseUuidNameValuesEntity<ID extends Serializable> implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "编号id")
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private ID id;

	@ApiModelProperty(value = "创建时间")
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Date createDate = new Date();

	@ApiModelProperty(value = "创建用户")
	@CreatedBy
	@Column(nullable = false, updatable = false)
	private Long createUserId;

	@ApiModelProperty(value = "最近修改用户")
	@LastModifiedBy
	@Column(nullable = false)
	private Long updateUserId;

	@ApiModelProperty(value = "最近修改时间")
	@LastModifiedDate
	@Column(nullable = false)
	private Date updateDate;

	@NotBlank(message = "{es.message.name.empty}")
	@ApiModelProperty(value = "名称", required = true)
	private String name;

	@Type(type = "Json", parameters = @Parameter(name = "class", value = "java.util.Map"))
	private Map<String, String> nameValues;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Map<String, String> getNameValues() {

		return nameValues;
	}

	public void setNameValues(Map<String, String> nameValues) {

		this.nameValues = nameValues;
	}

	public ID getId() {

		return id;
	}

	public void setId(ID id) {

		this.id = id;
	}

	public Date getCreateDate() {

		return createDate;
	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;
	}

	public Long getCreateUserId() {

		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {

		this.createUserId = createUserId;
	}

	public Long getUpdateUserId() {

		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {

		this.updateUserId = updateUserId;
	}

	public Date getUpdateDate() {

		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {

		this.updateDate = updateDate;
	}

	public boolean equals(Object obj) {

		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		BaseUuidNameValuesEntity<?> that = (BaseUuidNameValuesEntity<?>) obj;

		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}

	@Override
	public String toString() {

		return "ID: " + id;
	}

	
//	public String getLocalName() {
//
//		String localName = null;
//
//		if (nameValues != null && nameValues.containsKey(LoginUtils.getLoginLanguage())) {
//			localName = nameValues.get(LoginUtils.getLoginLanguage());
//		}
//		else {
//			localName = name;
//		}
//
//		return localName;
//	}

}