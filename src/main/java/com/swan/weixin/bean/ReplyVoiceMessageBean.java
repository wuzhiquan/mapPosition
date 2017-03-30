package com.swan.weixin.bean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@XmlRootElement(name="xml")
public class ReplyVoiceMessageBean{
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
	 * voice
	 */
	private String msgType="voice";
	/**
	 * 通过上传多媒体文件，得到的id。
	 */
	private String mediaId;
	
	static class InnerVoiceBean
	{
		/**
		 * 通过上传多媒体文件，得到的id。
		 */
		private String mediaId;
		@XmlElement(name="MediaId")
		public String getMediaId() {
			return mediaId;
		}
		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}
	
	@XmlElement(name="ToUserName")
	public String getToUserName() {
		return toUserName;
	}
	@XmlElement(name="FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}
	@XmlElement(name="CreateTime")
	public String getCreateTime() {
		return createTime;
	}
	@XmlElement(name="MsgType")
	public String getMsgType() {
		return msgType;
	}
	@XmlTransient
	public String getMediaId() {
		return mediaId;
	}
	@XmlElement(name="Voice")
	public InnerVoiceBean getVoice() {
		InnerVoiceBean bean =new InnerVoiceBean();
		bean.setMediaId(mediaId);
		return bean;
	}
	
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public static void main(String[] args) {
		ReplyVoiceMessageBean bean = new ReplyVoiceMessageBean();
		bean.setCreateTime("2222");
		bean.setFromUserName("222");
		bean.setToUserName("22");
		bean.setMediaId("22");
		try {
			Marshaller um = JAXBContext.newInstance(ReplyVoiceMessageBean.class).createMarshaller();
			um.marshal(bean, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
}