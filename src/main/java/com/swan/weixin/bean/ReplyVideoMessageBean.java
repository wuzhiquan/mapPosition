package com.swan.weixin.bean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="xml")
public class ReplyVideoMessageBean{
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
	private String msgType="video";
	/**
	 * 通过上传多媒体文件，得到的id
	 */
	private String mediaId;
	/**
	 * 视频消息的标题
	 */
	private String title;
	/**
	 * 视频消息的描述
	 */
	private String description;
	
	/**
	 * 视频描述内部类
	 * @author Administrator
	 *
	 */
	
	static class InnerVideoBean
	{
		/**
		 * 通过上传多媒体文件，得到的id
		 */
		private String mediaId;
		/**
		 * 视频消息的标题
		 */
		private String title;
		/**
		 * 视频消息的描述
		 */
		private String description;
		@XmlElement(name="MediaId")
		public String getMediaId() {
			return mediaId;
		}
		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
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
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
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
	@XmlElement(name="Video")
	public InnerVideoBean getVideo() {
		InnerVideoBean bean =new InnerVideoBean();
		bean.setDescription(description);
		bean.setMediaId(mediaId);
		bean.setTitle(title);
		return bean;
	}
	
	
	
	public static void main(String[] args) {
		ReplyVideoMessageBean bean = new ReplyVideoMessageBean();
		bean.setCreateTime("2222");
		bean.setFromUserName("222");
		bean.setToUserName("22");
		bean.setDescription("222");
		bean.setMediaId("22");
		bean.setTitle("2222");
		try {
			Marshaller um = JAXBContext.newInstance(ReplyVideoMessageBean.class).createMarshaller();
			um.marshal(bean, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}