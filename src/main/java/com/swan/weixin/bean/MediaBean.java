package com.swan.weixin.bean;

import java.io.File;

/**
 * 媒体文件bean
 * @author Administrator
 *
 */
public class MediaBean{
	private String type;
	private String media_id;
	private String created_at;
	private File file;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	@Override
	public String toString() {
		return "MediaBean [type=" + type + ", media_id=" + media_id
				+ ", created_at=" + created_at + "]";
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}
