package com.koron.web.servlet;

import java.util.HashMap;
import java.util.List;

import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swan.bean.MessageBean;

import com.google.gson.Gson;
import com.koron.web.bean.WXMessageBean;
import com.koron.web.mapper.WXMessageMapper;

@Controller
public class GetMsgAction {
	@RequestMapping("/WXmessageList.htm")
	@ResponseBody
	public String getWXmessageList() {
		HashMap<String, Object> map = new HashMap<>();
		@SuppressWarnings({ "unchecked", "resource" })
		List<WXMessageBean> list = new SessionFactory().runTask(this, "getList", List.class);
		map.put("list", list);
		return new Gson().toJson(map);
	}

	@TaskAnnotation("getList")
	private List<WXMessageBean> getsList(SessionFactory factory) {
		WXMessageMapper mapper = factory.getMapper(WXMessageMapper.class);
		return mapper.getList();
	}
	
	/**
	 * 获取历史对话信息
	 * @return
	 */
	@RequestMapping("/WXmessageAllList.htm")
	@ResponseBody
	public String getWXmessageAllList(@RequestParam("userid") Integer userid) {
		HashMap<String, Object> map = new HashMap<>();
		@SuppressWarnings({ "unchecked", "resource" })
		List<WXMessageBean> list = new SessionFactory().runTask(this, "getAllList", List.class, userid);
		map.put("list", list);
		return new Gson().toJson(map);
	}

	@TaskAnnotation("getAllList")
	private List<WXMessageBean> getsAllList(SessionFactory factory,Integer userid) {
		WXMessageMapper mapper = factory.getMapper(WXMessageMapper.class);
		return mapper.getAllList(userid);
	}
	
	/**
	 * 插入
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/addWXmessage1.htm")
	@ResponseBody
	public final String addWXmessage1(WXMessageBean bean) {
		return new SessionFactory().runTask(this, "addWXmessage1", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("addWXmessage1")
	private MessageBean<Integer> addWXmessage1(SessionFactory factory, WXMessageBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		WXMessageMapper mapper = factory.getMapper(WXMessageMapper.class);
		Integer ret = mapper.addWXMessage(bean);
		if (ret > 0) {
			msg.setCode(0);
			msg.setDescription("插入成功");
		} else {
			msg.setCode(-1);
			msg.setDescription("插入失败");
		}
		return msg;
	}
	
	/**
	 * 更新查看状态
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/updateWXmessageStatus.htm")
	@ResponseBody
	public final String updateStatus(WXMessageBean bean) {
		return new SessionFactory().runTask(this, "updateStatus", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("updateStatus")
	private MessageBean<Integer> updateStatus(SessionFactory factory, WXMessageBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		WXMessageMapper mapper = factory.getMapper(WXMessageMapper.class);
		Integer ret = mapper.updateStatus(bean);
		if (ret > 0) {
			msg.setCode(0);
			msg.setDescription("更新成功");
		} else {
			msg.setCode(-1);
			msg.setDescription("更新失败");
		}
		return msg;
	}
	
}
