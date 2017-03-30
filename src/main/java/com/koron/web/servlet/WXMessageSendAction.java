package com.koron.web.servlet;

import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swan.bean.MessageBean;

import com.koron.web.bean.WXMessageSendBean;
import com.koron.web.mapper.WXMessageMapper;

@Controller
public class WXMessageSendAction {
	/**
	 * 插入
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/addWXmessageSend.htm")
	@ResponseBody
	public final String addWXmessageSend(WXMessageSendBean bean) {
		return new SessionFactory().runTask(this, "addWXmessage", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("addWXmessage")
	private MessageBean<Integer> addWXmessage(SessionFactory factory, WXMessageSendBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		WXMessageMapper mapper = factory.getMapper(WXMessageMapper.class);
		Integer ret = mapper.addWXMessageSend(bean);
		if (ret > 0) {
			msg.setCode(0);
			msg.setDescription("插入成功");
		} else {
			msg.setCode(-1);
			msg.setDescription("插入失败");
		}
		return msg;
	}
	
}
