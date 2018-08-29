package com.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Pagination", description = "分页信息")
public class Pagination<T> implements Pageable {

	/**
	 * 一页数据默认20条
	 */
	@ApiModelProperty(value = "一页数据")
	private int pageSize = 20;

	/**
	 * 一共有多少条数据
	 */
	@ApiModelProperty(value = "一共有多少条数据")
	private long totalRecords;

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页")
	private int currentPage;

	/**
	 * 排序字段
	 */
	@ApiModelProperty(value = "排序字段")
	private String column;
	/**
	 * 升序降序
	 */
	@ApiModelProperty(value = "升序降序")
	private String order;

	/**
	 * 数据集合
	 */
	@ApiModelProperty(value = "数据集合")
	private List<T> data;

	@ApiModelProperty(value = "显示哪些字段")
	private List<String> includeFields;

	/**
	 * 是否分页
	 */
	@ApiModelProperty(value = "是否分页")
	private Boolean isPagable = Boolean.TRUE;

	/**
	 *
	 */
	public Pagination() {

		super();
	}

	public Pagination(int currentPage, int pageSize, long totalRecords) {

		this.setCurrentPage(currentPage);
		this.setPageSize(pageSize);
		this.setTotalRecords(totalRecords);
	}

	public List<String> getIncludeFields() {
		return includeFields;
	}

	public void setIncludeFields(List<String> includeFields) {
		this.includeFields = includeFields;
	}

	public void setPage(Page<T> page) {

		setData(page.getContent());
		setTotalRecords(page.getTotalElements());
	}

	public int getPageSize() {

		if (BooleanUtils.isFalse(getIsPagable()))
			return Integer.MAX_VALUE;

		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}

	public long getTotalRecords() {

		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {

		this.totalRecords = totalRecords;
	}

	public int getTotalPages() {

		return (int) ( ( getTotalRecords() % getPageSize() > 0 ) ?
				( getTotalRecords() / getPageSize() + 1 ) :
				getTotalRecords() / getPageSize() );
	}

	public int getCurrentPage() {

		if (BooleanUtils.isFalse(getIsPagable()))
			return 1;

		return currentPage;
	}

	public void setCurrentPage(int currentPage) {

		this.currentPage = currentPage;
	}

	public List<T> getData() {

		if (data == null)
			return new ArrayList<T>();
		return data;
	}

	public void setData(List<T> data) {

		this.data = data;
	}

	public String getColumn() {

		return column;
	}

	public void setColumn(String column) {

		this.column = column;
	}

	public String getOrder() {

		return order;
	}

	public void setOrder(String order) {

		this.order = order;
	}

	public Boolean getIsPagable() {

		return isPagable;
	}

	public void setIsPagable(Boolean isPagable) {

		this.isPagable = isPagable;
	}

	@Override
	public int getPageNumber() {

		int start = getCurrentPage() - 1;
		if (start < 0)
			start = 0;
		return start;
	}

	@Override
	public int getOffset() {

		return getPageNumber() * getPageSize();
	}

	public int getStart() {

		return getPageNumber() * getPageSize();
	}

	public int getEnd() {

		return ( getPageNumber() + 1 ) * getPageSize();
	}

	@Override
	public Sort getSort() {

		if (StringUtils.isNotBlank(getColumn()) && StringUtils.isNotBlank(getOrder())) {
			return new Sort(Direction.fromStringOrNull(getOrder()), getColumn());
		}
		else if (StringUtils.isNotBlank(getOrder())) {
			return new Sort(Direction.fromStringOrNull(getOrder()), "createDate");
		}
		else
			return new Sort(Direction.DESC, "createDate");
	}

	@Override
	public Pageable next() {

		return null;
	}

	@Override
	public Pageable previousOrFirst() {

		return null;
	}

	@Override
	public Pageable first() {

		return null;
	}

	@Override
	public boolean hasPrevious() {

		return ( getOffset() / getPageSize() ) > 0;
	}

}
