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
import com.koron.web.bean.CaseBean;
import com.koron.web.mapper.CaseMapper;

@Controller
public class CaseAction {
	@RequestMapping("/case.htm")
	@ResponseBody
	public String getLocation() {
		try (SessionFactory factory = new SessionFactory()) {
			@SuppressWarnings("rawtypes")
			MessageBean<List> bean = MessageBean.create(0, "", List.class);
			bean.setData(factory.getMapper(CaseMapper.class).getAll());
			return bean.toJson();
		}
	}
	/**
	 * 插入
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/addCase.htm")
	@ResponseBody
	public final String addCase(CaseBean bean) {
		return new SessionFactory().runTask(this, "addCase", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("addCase")
	private MessageBean<Integer> addCase(SessionFactory factory, CaseBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		CaseMapper mapper = factory.getMapper(CaseMapper.class);
		Integer ret = mapper.addCase(bean);
		if (ret > 0) {
			msg.setCode(0);
			msg.setDescription("插入成功");
			msg.setData(bean.getId());
		} else {
			msg.setCode(-1);
			msg.setDescription("插入失败");
		}
		return msg;
	}
	
	/**
	 * 根据案件ID获取案件详情
	 * @return
	 */
	@RequestMapping("/getCaseById.htm")
	@ResponseBody
	public String getCaseById(@RequestParam("id") Integer id) {
		HashMap<String, Object> map = new HashMap<>();
		@SuppressWarnings({ "resource" })
		CaseBean bean = new SessionFactory().runTask(this, "getCaseById", CaseBean.class, id);
		map.put("data", bean);
		return new Gson().toJson(map);
	}

	@TaskAnnotation("getCaseById")
	private CaseBean getCaseById(SessionFactory factory,Integer id) {
		CaseMapper mapper = factory.getMapper(CaseMapper.class);
		CaseBean caseBean = mapper.getCaseById(id);
		return caseBean;
	}
	/**
	 * 更新状态
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/updateCaseStatus.htm")
	@ResponseBody
	public final String updateCaseStatus(CaseBean bean) {
		return new SessionFactory().runTask(this, "updateCaseStatus", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("updateCaseStatus")
	private MessageBean<Integer> updateCaseStatus(SessionFactory factory, CaseBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		CaseMapper mapper = factory.getMapper(CaseMapper.class);
		Integer ret = mapper.updateCase(bean);
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