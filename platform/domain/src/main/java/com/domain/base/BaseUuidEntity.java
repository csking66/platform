package com.domain.base;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
* @ClassName: BaseUuidEntity
* @Description: 生成uuid
* @date 2018年11月23日
*
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseUuidEntity<ID extends Serializable> implements Serializable {

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
	
	/**
	 * 版本
	 */
	@ApiModelProperty(value = "版本控制号")
	@Version
	@Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
	private Long optlock = 0L;

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

		BaseUuidEntity<?> that = (BaseUuidEntity<?>) obj;

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

	public Long getOptlock() {
		return optlock;
	}

	public void setOptlock(Long optlock) {
		this.optlock = optlock;
	}
	
	

}