package com.domain.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法.
 * <p>
 */
@MappedSuperclass
public abstract class BaseMongoEntity<ID extends Serializable> implements Persistable<ID> {

	private static final long serialVersionUID = 1L;

	@Id
	private ID id;

	@CreatedDate
	private Date createDate;

	@CreatedBy
	private Long createUserId;

	@LastModifiedBy
	private Long updateUserId;

	@LastModifiedDate
	private Date updateDate;

	/**
	 * 版本
	 */
	@Version
	private Long optlock;

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

	public Long getOptlock() {

		return optlock;
	}

	public void setOptlock(Long optlock) {

		this.optlock = optlock;
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

		BaseMongoEntity<?> that = (BaseMongoEntity<?>) obj;

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

	@JsonIgnore
	@JSONField(serialize = false)
	@Override
	public boolean isNew() {

		return optlock == null || optlock == 0;
	}
}