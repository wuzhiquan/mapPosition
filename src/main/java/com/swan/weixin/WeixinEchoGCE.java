package com.swan.weixin;

import com.koron.web.service.WXMsgService;
import com.swan.weixin.bean.ReceiveImageMessageBean;
import com.swan.weixin.bean.ReceiveLocationMessageBean;
import com.swan.weixin.bean.ReceiveTextMessageBean;
import com.swan.weixin.bean.ReceiveWeixinLocationBean;

public class WeixinEchoGCE {
	@WeixinAnno({ ReceiveWeixinLocationBean.class })
	public void receiveLocationMessage(ReceiveWeixinLocationBean location) {
	}

	@WeixinAnno({ ReceiveTextMessageBean.class})
	public String receiveTextMessage(ReceiveTextMessageBean txt) {
		WXMsgService.add(txt);
		return "";
	}
	
	@WeixinAnno({ReceiveImageMessageBean.class})
	public String receiveImageMessage(ReceiveImageMessageBean img) {
		WXMsgService.add(img);
		return "";
	}
	
	@WeixinAnno({ReceiveLocationMessageBean.class})
	public String receiveLocationMessage(ReceiveLocationMessageBean location) {
		WXMsgService.add(location);
		return "";
	}
}