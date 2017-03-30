package com.swan.weixin.bean;

import java.util.HashMap;

/**
 * 客服发送音乐消息
 * @author Administrator
 *
 */
public class ServiceMusicMessageBean{
	
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，music
	 */
	private String msgtype="music";
	/**
	 * 音乐内容
	 */
	private HashMap<String, String> music=new HashMap<String, String>();
	
	public void setTouser(String touser){
		this.touser = touser;
	}
	public String getTouser(){
		return touser;
	}
	public String getMsgtype(){
		return msgtype;
	}
	public void setTitle(String title){
		music.put("title", title);
	}
	public String getTitle(){
		return music.get("title");
	}
	public void setDescription(String description){
		music.put("description", description);
	}
	public String getDescription(){
		return music.get("description");
	}
	public void setMusicurl(String musicurl){
		music.put("musicurl", musicurl);
	}
	public String getMusicurl(){
		return music.get("musicurl");
	}
	public void setHqmusicurl(String hqmusicurl){
		music.put("hqmusicurl", hqmusicurl);
	}
	public String getHqmusicurl(){
		return music.get("hqmusicurl");
	}
	public void setThumb_media_id(String thumb_media_id){
		music.put("thumb_media_id", thumb_media_id);
	}
	public String getThumb_media_id(){
		return music.get("thumb_media_id");
	}
}