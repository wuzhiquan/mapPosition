package com.swan.weixin.bean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="xml")
public class ReplyMusicMessageBean{
	/**
	 * 接收方帐号（收到的OpenID）
	 */
	private String toUserName;
	/**
	 * 开发者微信号
	 */
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private String createTime;
	/**
	 * video
	 */
	private String msgType="music";
	/**
	 * 通过上传多媒体文件，得到的缩略图id
	 */
	private String thumbMediaId;
	/**
	 * 音乐消息的标题
	 */
	private String title;
	/**
	 * 音乐消息的描述
	 */
	private String description;
	/**
	 * 音乐链接
	 */
	private String musicURL;
	/**
	 * 高质量音乐链接
	 */
	private String hQMusicUrl;
	
	/**
	 * 音乐描述内部类
	 * @author Administrator
	 *
	 */
	
	static class InnerMusicBean
	{
		/**
		 * 通过上传多媒体文件，得到的缩略图id
		 */
		private String thumbMediaId;
		/**
		 * 音乐消息的标题
		 */
		private String title;
		/**
		 * 音乐消息的描述
		 */
		private String description;
		/**
		 * 音乐链接
		 */
		private String musicURL;
		/**
		 * 高质量音乐链接
		 */
		private String hQMusicUrl;
		@XmlElement(name="FromUserName")
		public String getThumbMediaId() {
			return thumbMediaId;
		}
		public void setThumbMediaId(String thumbMediaId) {
			this.thumbMediaId = thumbMediaId;
		}
		@XmlElement(name="Title")
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		@XmlElement(name="Description")
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		@XmlElement(name="MusicURL")
		public String getMusicURL() {
			return musicURL;
		}
		public void setMusicURL(String musicURL) {
			this.musicURL = musicURL;
		}
		@XmlElement(name="HQMusicUrl")
		public String gethQMusicUrl() {
			return hQMusicUrl;
		}
		public void sethQMusicUrl(String hQMusicUrl) {
			this.hQMusicUrl = hQMusicUrl;
		}
		
	}
	
	public void setToUserName(String toUserName){
		this.toUserName = toUserName;
	}
	@XmlElement(name="ToUserName")
	public String getToUserName(){
		return toUserName;
	}
	public void setFromUserName(String fromUserName){
		this.fromUserName = fromUserName;
	}
	@XmlElement(name="FromUserName")
	public String getFromUserName(){
		return fromUserName;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	@XmlElement(name="CreateTime")
	public String getCreateTime(){
		return createTime;
	}
	@XmlElement(name="msgType")
	public String getMsgType(){
		return msgType;
	}
	@XmlTransient
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlTransient
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlTransient
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	@XmlTransient
	public String getMusicURL() {
		return musicURL;
	}
	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}
	@XmlTransient
	public String gethQMusicUrl() {
		return hQMusicUrl;
	}
	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}
	@XmlElement(name="Music")
	public InnerMusicBean getVideo() {
		InnerMusicBean bean =new InnerMusicBean();
		bean.setDescription(description);
		bean.sethQMusicUrl(hQMusicUrl);
		bean.setMusicURL(hQMusicUrl);
		bean.setThumbMediaId(thumbMediaId);
		bean.setTitle(title);
		return bean;
	}
	
	
	
	public static void main(String[] args) {
		ReplyMusicMessageBean bean = new ReplyMusicMessageBean();
		bean.setCreateTime("2222");
		bean.setFromUserName("222");
		bean.setToUserName("22");
		bean.setDescription("222");
		bean.setTitle("22");
		bean.sethQMusicUrl("hQMusicUrl");
		bean.setMusicURL("musicURL");
		bean.setThumbMediaId("11");
		bean.setTitle("2222");
		try {
			Marshaller um = JAXBContext.newInstance(ReplyMusicMessageBean.class).createMarshaller();
			um.marshal(bean, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
}