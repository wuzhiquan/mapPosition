package com.koron.web.servlet;

import java.util.List;

import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swan.bean.MessageBean;

import com.koron.web.bean.TrackBean;
import com.koron.web.mapper.TrackMapper;

@Controller
public class TrackAction {
	/**
	 * 录入员工轨迹
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/addTrack.htm")
	@ResponseBody
	public final String add(TrackBean bean) {
		return new SessionFactory().runTask(this, "add", MessageBean.class, bean).toJson();
	}

	@SuppressWarnings("resource")
	@RequestMapping("/track.htm")
	@ResponseBody
	public final String track(@RequestParam("userid")String userid) {
		return new SessionFactory().runTask(this, "list", MessageBean.class, userid).toJson();
	}

	@SuppressWarnings("rawtypes")
	@TaskAnnotation("list")
	private MessageBean<List> list(SessionFactory factory, String userId) {
		MessageBean<List> msg = new MessageBean<>();
		TrackMapper mapper = factory.getMapper(TrackMapper.class);
		List<TrackBean> list = mapper.list(userId);
		msg.setCode(0);
		msg.setDescription("获取轨迹成功");
		msg.setData(list);
		return msg;
	}

	@TaskAnnotation("add")
	private MessageBean<Integer> add(SessionFactory factory, TrackBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		TrackMapper mapper = factory.getMapper(TrackMapper.class);
		Integer ret = mapper.addTrack(bean);
		if (ret > 0) {
			msg.setCode(0);
			msg.setDescription("添加轨迹成功！");
			msg.setData(bean.getId());
		}
		return msg;
	}
}