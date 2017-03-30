package com.koron.web.servlet;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.koron.util.Tools;
import com.koron.web.bean.CaseInfoBean;
import com.koron.web.mapper.CaseInfoMapper;

@Controller
public class CaseInfoAction {
	@RequestMapping("/viewcaseinfo.htm")
	public final ModelAndView viewcaseinfo(HttpSession session,Integer id) {
		System.out.println(id);
		return Tools.getView("caseinfo.btl");
	}
	/**
	 * 根据案件ID获取案件详情
	 * @return
	 */
	@RequestMapping("/getCaseInfoById.htm")
	@ResponseBody
	public String getCaseInfoById(@RequestParam("id") Integer id) {
		HashMap<String, Object> map = new HashMap<>();
		@SuppressWarnings({ "resource" })
		CaseInfoBean bean = new SessionFactory().runTask(this, "getCaseInfoById", CaseInfoBean.class, id);
		map.put("data", bean);
		return new Gson().toJson(map);
	}

	@TaskAnnotation("getCaseInfoById")
	private CaseInfoBean getCaseInfoById(SessionFactory factory,Integer id) {
		CaseInfoMapper mapper = factory.getMapper(CaseInfoMapper.class);
		CaseInfoBean caseinfoBean = mapper.getCaseInfoById(id);
		return caseinfoBean;
	}
}