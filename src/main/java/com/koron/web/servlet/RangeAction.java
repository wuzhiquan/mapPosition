package com.koron.web.servlet;

import java.util.List;

import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swan.bean.MessageBean;

import com.koron.web.mapper.RangeMapper;

@Controller
public class RangeAction {
	@RequestMapping("/range.htm")
	@ResponseBody
	public String getLocation() {
		try (SessionFactory factory = new SessionFactory()) {
			@SuppressWarnings("rawtypes")
			MessageBean<List> bean = MessageBean.create(0, "", List.class);
			bean.setData(factory.getMapper(RangeMapper.class).getAll());
			return bean.toJson();
		}
	}
}