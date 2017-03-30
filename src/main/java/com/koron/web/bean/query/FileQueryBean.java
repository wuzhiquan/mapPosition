package com.koron.web.bean.query;
/**
 * 搜索文件
 * @author Administrator
 *
 */
public class FileQueryBean extends BaseQueryBean {
	
	private String key;
	
	private String description;
	
	private Integer id;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
