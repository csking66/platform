package com.domain.base;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID extends Serializable> implements
		Serializable {

	private static final long serialVersionUID = -8986514655616481436L;

	@ApiModelProperty(value = "编号", required = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;

	@ApiModelProperty(value = "创建时间", required = false)
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Date createDate = new Date();

	@ApiModelProperty(value = "创建用户", required = false)
	@CreatedBy
	@Column(nullable = false, updatable = false)
	private Long createUserId;

	@LastModifiedBy
	@Column(nullable = false)
	private Long updateUserId;

	@LastModifiedDate
	@Column(nullable = false)
	private Date updateDate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		BaseEntity<?> other = (BaseEntity<?>) obj;		
		return null == this.getId() ? false : this.getId().equals(other.getId());
	}
	
	@Override
	public String toString() {
		return "ID: " + id;
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
	
	

}
