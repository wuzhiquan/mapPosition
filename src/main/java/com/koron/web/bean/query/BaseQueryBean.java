package com.koron.web.bean.query;

public class BaseQueryBean {
	
	/**
	 * 第几页
	 */
	private int page;
	/**
	 * 每页条数
	 */
	private int pageCount = 20;
	/**
	 * 数据条数
	 */
	private int rowNumber;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 起始位置
	 */
	private int offset;
	/**
	 * @return 获取第几页
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param 设置第几页
	 */
	public void setPage(int page) {
		this.page = page;
		setOffset(page * pageCount);
	}

	/**
	 * @return 获取每页条数
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param 设置每页条数
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
		this.totalPage = (rowNumber - 1) / pageCount + 1;
		setOffset(page * pageCount);
	}

	/**
	 * @return 获取数据条数
	 */
	public int getRowNumber() {
		return rowNumber;
	}

	/**
	 * @param 设置数据条数
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
		this.totalPage = (rowNumber - 1) / pageCount + 1;
	}

	/**
	 * @return 获取总页数
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param 设置总页数
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return 获取起始位置
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param 设置起始位置
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
}
