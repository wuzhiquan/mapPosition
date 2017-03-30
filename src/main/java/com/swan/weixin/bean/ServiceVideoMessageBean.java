package com.swan.weixin.bean;

import java.util.HashMap;

public class ServiceVideoMessageBean{
	
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，video
	 */
	private String msgtype="video";
	/**
	 * 发送的视频的内容
	 */
	private HashMap<String, String> video=new HashMap<String, String>();
	
	
	public void setTouser(String touser){
		this.touser = touser;
	}
	public String getTouser(){
		return touser;
	}
	public String getMsgtype(){
		return msgtype;
	}
	public void setMedia_id(String media_id){
		video.put("media_id", media_id);
	}
	public String getMedia_id(){
		return video.get("media_id");
	}
	public void setTitle(String title){
		video.put("title", title);
	}
	public String getTitle(){
		return video.get("title");
	}
	public void setDescription(String description){
		video.put("description", description);
	}
	public String getDescription(){
		return video.get("description");
	}
}