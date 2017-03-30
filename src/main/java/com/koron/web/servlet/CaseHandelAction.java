package com.koron.web.servlet;

import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swan.bean.MessageBean;

import com.koron.web.bean.CaseHandelBean;
import com.koron.web.mapper.CaseHandelMapper;

@Controller
public class CaseHandelAction {
	/**
	 * 插入
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/addCaseHandel.htm")
	@ResponseBody
	public final String addCaseHandel(CaseHandelBean bean) {
		return new SessionFactory().runTask(this, "addCaseHandel", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("addCaseHandel")
	private MessageBean<Integer> addCaseHandel(SessionFactory factory, CaseHandelBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		CaseHandelMapper mapper = factory.getMapper(CaseHandelMapper.class);
		Integer ret = mapper.addCaseHandel(bean);
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
}