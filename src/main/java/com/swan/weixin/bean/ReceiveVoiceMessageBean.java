package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 接收语音消息
 * @author swan
 *
 */
@XmlRootElement(name="xml")
public class ReceiveVoiceMessageBean {
	/**
	 * 开发者微信号
	 */
	private String toUserName;
	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private String createTime;
	/**
	 * 语音为voice
	 */
	private String msgType;
	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String mediaId;
	/**
	 * 语音格式，如amr，speex等
	 */
	private String format;
	/**
	 * 消息标识
	 */
	private String msgID;
	/**
	 * 开通识别后识别字符串
	 */
	private String recognition;
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
	@XmlElement(name="MediaId")
	public String getMediaId() {
		return mediaId;
	}
	@XmlElement(name="Format")
	public String getFormat() {
		return format;
	}
	@XmlElement(name="MsgID")
	public String getMsgID() {
		return msgID;
	}
	@XmlElement(name="Recognition")
	public String getRecognition() {
		return recognition;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public void setRecognition(String recognition) {
		this.recognition = recognition;
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
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	@Override
	public String toString() {
		return "SoundMessageBean [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", mediaId=" + mediaId + ", format=" + format + "]";
	}

	
}