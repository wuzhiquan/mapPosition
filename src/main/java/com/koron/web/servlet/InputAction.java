package com.koron.web.servlet;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.swan.bean.MessageBean;

import com.google.gson.Gson;
import com.koron.util.Tools;
import com.koron.web.bean.PersonBean;
import com.koron.web.mapper.InputMapper;

@Controller
public class InputAction {
	@RequestMapping("/inspect.wxbase.htm")
	public final ModelAndView inputPeople(HttpSession session) {
		System.out.println(session.getAttribute("_openid"));
		return Tools.getView("inputPeople.btl");
	}

	@RequestMapping("/index.htm")
	public final ModelAndView index(HttpSession session) {
		return Tools.getView("console.btl");
	}

	/**
	 * 录入员工
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/input.htm")
	@ResponseBody
	public final String add(PersonBean bean, HttpSession session) {
		bean.setOpenid((String) session.getAttribute("_openid"));
		return new SessionFactory().runTask(this, "add", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("add")
	private MessageBean<Integer> add(SessionFactory factory, PersonBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		InputMapper mapper = factory.getMapper(InputMapper.class);
		Integer ret = mapper.addPeople(bean);
		if (ret > 0) {
			msg.setCode(0);
			msg.setDescription("添加信息成功！");
			msg.setData(bean.getId());
		}
		return msg;
	}

	/**
	 * 获取人员列表
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping("/list.htm")
	@ResponseBody
	public String getDddressList() {
		HashMap<String, Object> map = new HashMap<>();
		@SuppressWarnings({ "unchecked", "resource" })
		List<PersonBean> list = new SessionFactory().runTask(this, "getList", List.class);
		map.put("list", list);
		return new Gson().toJson(map);
	}

	@TaskAnnotation("getList")
	private List<PersonBean> getsList(SessionFactory factory) {
		InputMapper mapper = factory.getMapper(InputMapper.class);
		return mapper.getList();
	}

	/**
	 * 判断名字是否存在
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/getCount.htm")
	@ResponseBody
	public String getNameCount(@RequestParam("name") String name) {
		return new SessionFactory().runTask(this, "getNameCount", MessageBean.class, name).toJson();
	}

	@TaskAnnotation("getNameCount")
	private MessageBean<Integer> getNameCount(SessionFactory factory, String name) {
		MessageBean<Integer> msg = new MessageBean<>();
		InputMapper mapper = factory.getMapper(InputMapper.class);
		Integer ret = mapper.getCount(name);
		if (ret == null) {
			msg.setCode(-1);
			msg.setDescription("用户尚未存在！");
		}else {
			msg.setCode(ret);
			msg.setDescription("用户已存在！");
		}
		return msg;
	}
	
	/**
	 * 更新用户最后的位置
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/updateAddress.htm")
	@ResponseBody
	public final String update(PersonBean bean) {
		return new SessionFactory().runTask(this, "update", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("update")
	private MessageBean<Integer> update(SessionFactory factory, PersonBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		InputMapper mapper = factory.getMapper(InputMapper.class);
		Integer ret = mapper.updatePeopleAddress(bean);
		if (ret > 0) {
			msg.setCode(0);
			msg.setDescription("更新成功");
		} else {
			msg.setCode(-1);
			msg.setDescription("更新失败");
		}
		return msg;
	}
	
	/**
	 * 更新更新上班状态（下班）
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/updateStatus.htm")
	@ResponseBody
	public final String updateStatus(PersonBean bean) {
		return new SessionFactory().runTask(this, "updateStatus", MessageBean.class, bean).toJson();
	}

	@TaskAnnotation("updateStatus")
	private MessageBean<Integer> updateStatus(SessionFactory factory, PersonBean bean) {
		MessageBean<Integer> msg = new MessageBean<>();
		InputMapper mapper = factory.getMapper(InputMapper.class);
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
	
	/**
	 * 根据用户名获取用户信息
	 * @return
	 */
	@RequestMapping("/getPersonByName.htm")
	@ResponseBody
	public String getPersonByName(@RequestParam("name") String name) {
		HashMap<String, Object> map = new HashMap<>();
		@SuppressWarnings({ "resource" })
		PersonBean bean = new SessionFactory().runTask(this, "getPersonByName", PersonBean.class, name);
		map.put("data", bean);
		return new Gson().toJson(map);
	}

	@TaskAnnotation("getPersonByName")
	private PersonBean getPersonByName(SessionFactory factory,String name) {
		InputMapper mapper = factory.getMapper(InputMapper.class);
		PersonBean personBean = mapper.getUserByName(name);
		return personBean;
	}
}
