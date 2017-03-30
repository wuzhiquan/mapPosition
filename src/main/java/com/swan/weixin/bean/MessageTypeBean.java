package com.swan.weixin.bean;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class MessageTypeBean {
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 事件类型
	 */
	private String event;
	/**
	 * 事件key
	 */
	private String eventKey;

	public Object getBean(StringBuilder sb) throws JAXBException {
		Unmarshaller um = null;
		if (getMsgType().equals("text")) {
			um = JAXBContext.newInstance(ReceiveTextMessageBean.class).createUnmarshaller();
		} else if (getMsgType().equals("image")) {
			um = JAXBContext.newInstance(ReceiveImageMessageBean.class).createUnmarshaller();
		} else if (getMsgType().equals("voice")) {
			um = JAXBContext.newInstance(ReceiveVoiceMessageBean.class).createUnmarshaller();
		} else if (getMsgType().equals("video")) {
			um = JAXBContext.newInstance(ReceiveVideoMessageBean.class).createUnmarshaller();
		} else if (getMsgType().equals("location")) {
			um = JAXBContext.newInstance(ReceiveLocationMessageBean.class).createUnmarshaller();
		} else if (getMsgType().equals("link")) {
			um = JAXBContext.newInstance(ReceiveLinkMessageBean.class).createUnmarshaller();
		} else if (getMsgType().equals("event")) {
			System.out.println(getEvent().toLowerCase());
			if (getEvent().equals("subscribe")) {
				if (getEventKey() == null || getEventKey().equals(""))
				{
					um = JAXBContext.newInstance(ReceiveSubscribeBean.class).createUnmarshaller();
				}
				else
				{
					um = JAXBContext.newInstance(ReceiveQRSubscribeBean.class).createUnmarshaller();
				}
			} else if (getEvent().equals("unsubscribe")) {
				um = JAXBContext.newInstance(ReceiveUnSubscribeBean.class).createUnmarshaller();
			} else if (getEvent().equals("SCAN")) {
				um = JAXBContext.newInstance(ReceiveQRScanBean.class).createUnmarshaller();
			} else if (getEvent().toLowerCase().equals("location")) {
				um = JAXBContext.newInstance(ReceiveWeixinLocationBean.class).createUnmarshaller();
			} else if (getEvent().toLowerCase().equals("click")) {
				um = JAXBContext.newInstance(ReceiveMenuClickBean.class).createUnmarshaller();
			} else if (getEvent().toLowerCase().equals("view")) {
				um = JAXBContext.newInstance(ReceiveMenuLinkBean.class).createUnmarshaller();
			}
		}
		if (um == null)
			return null;
		return um.unmarshal(new StringReader(sb.toString()));
	}

	@XmlElement(name = "MsgType")
	public String getMsgType() {
		return msgType;
	}

	@XmlElement(name = "Event")
	public String getEvent() {
		return event;
	}

	@XmlElement(name = "EventKey")
	public String getEventKey() {
		return eventKey;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@Override
	public String toString() {
		return "MessageTypeBean [msgType=" + msgType + ", event=" + event + ", eventKey=" + eventKey + "]";
	}

}