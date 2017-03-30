package com.swan.weixin.bean;

import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="xml")
public class ReplyImageMessageBean{
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
	 * image
	 */
	private String msgType="image";
	/**
	 * 通过上传多媒体文件，得到的id。
	 */
	private ArrayList<String> mediaId = new ArrayList<String>();
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
	@XmlElementWrapper(name="Image")
	@XmlElement(name="MediaId")
	public ArrayList<String> getMediaId() {
		return mediaId;
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
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public void setMediaId(ArrayList<String> mediaId) {
		this.mediaId = mediaId;
	}
	public void addMediaId(String mediaId)
	{
		this.mediaId.add(mediaId);
	}
	public static void main(String[] args) {
		ReplyImageMessageBean bean = new ReplyImageMessageBean();
		bean.setCreateTime("2222");
		bean.addMediaId("ssssss");
		try {
			Marshaller um = JAXBContext.newInstance(ReplyImageMessageBean.class).createMarshaller();
			um.marshal(bean, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
}