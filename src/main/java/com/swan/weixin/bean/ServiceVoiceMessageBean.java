package com.swan.weixin.bean;

import java.util.HashMap;

/**
 * 客服发送语音消息
 * @author Administrator
 *
 */
public class ServiceVoiceMessageBean{
	
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，voice
	 */
	private String msgtype="voice";
	/**
	 * 发送的语音的媒体内容
	 */
	private HashMap<String, String> voice=new HashMap<String, String>();
	
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
		voice.put("media_id", media_id);
	}
	public String getMedia_id(){
		return voice.get("media_id");
	}
}