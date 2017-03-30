package com.swan.weixin.bean;

import java.util.HashMap;

/**
 * 客服图片消息
 * @author Administrator
 *
 */
public class ServiceImageMessageBean{
	
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，image
	 */
	private String msgtype="image";
	/**
	 * 发送的图片的媒体ID
	 */
	private HashMap<String, String> image=new HashMap<String, String>();
	
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
		image.put("media_id", media_id);
	}
	public String getMedia_id(){
		return image.get("media_id");
	}
}