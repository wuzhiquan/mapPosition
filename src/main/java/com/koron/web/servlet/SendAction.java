package com.koron.web.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swan.bean.MessageBean;

import com.google.gson.Gson;
import com.koron.util.Config;
import com.koron.web.bean.FileBean;
import com.koron.web.mapper.FileMapper;
import com.koron.web.mapper.InputMapper;
import com.swan.weixin.Util;
import com.swan.weixin.WeixinMsgHandler;
import com.swan.weixin.bean.MediaBean;
import com.swan.weixin.bean.ServiceImageMessageBean;
import com.swan.weixin.bean.ServiceNewsMessageBean;
import com.swan.weixin.bean.ServiceTextMessageBean;

@Controller
public class SendAction {
	@RequestMapping("/sendMessage.htm")
	@ResponseBody
	public String getNameCount(@RequestParam("id") Integer id,@RequestParam("content") String content) {
		String openid = null;
		try(SessionFactory factory = new SessionFactory();) {
			openid = factory.getMapper(InputMapper.class).getOpenid(id);
		}
		if (openid == null)
			return MessageBean.create(-1, "该用户尚未绑定微信", Void.class).toJson();
		ServiceTextMessageBean msg = new ServiceTextMessageBean();
		msg.setTouser(openid);
		msg.setContent(content);
		String code = WeixinMsgHandler.process(msg);
		if("0.0".equals(code) || "0".equals(code)) {
			return MessageBean.create(0, "success", Void.class).toJson();
		} else {
			return MessageBean.create(-1, "fail", Void.class).toJson();
		}
	}
	@RequestMapping("/sendImageMessage.htm")
	@ResponseBody
	public String sendImageMessage(@RequestParam("id") Integer id,@RequestParam("media_id") String media_id) {
		String openid = null;
		try(SessionFactory factory = new SessionFactory();) {
			openid = factory.getMapper(InputMapper.class).getOpenid(id);
		}
		if (openid == null)
			return MessageBean.create(-1, "该用户尚未绑定微信", Void.class).toJson();
		ServiceImageMessageBean msg = new ServiceImageMessageBean();
		msg.setTouser(openid);
		msg.setMedia_id(media_id);
		String code = WeixinMsgHandler.process(msg);
		if("0.0".equals(code) || "0".equals(code)) {
			return MessageBean.create(0, "success", Void.class).toJson();
		} else {
			return MessageBean.create(-1, "fail", Void.class).toJson();
		}
	}
	
	/**
	 * 发送案件信息
	 * @param id
	 * @param title
	 * @param description
	 * @param picUrl
	 * @return
	 */
	@RequestMapping("/sendCaseMessage.htm")
	@ResponseBody
	public String sendCaseMessage(@RequestParam("id") Integer id,@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("picUrl") String picUrl,@RequestParam("url") String url) {
		String openid = null;
		try(SessionFactory factory = new SessionFactory();) {
			openid = factory.getMapper(InputMapper.class).getOpenid(id);
		}
		if (openid == null)
			return MessageBean.create(-1, "该用户尚未绑定微信", Void.class).toJson();
		ServiceNewsMessageBean msg = new ServiceNewsMessageBean();
		msg.setTouser(openid);
		msg.addItem(title, description, picUrl, url);
		String code = WeixinMsgHandler.process(msg);
		if("0.0".equals(code) || "0".equals(code)) {
			return MessageBean.create(0, "success", Void.class).toJson();
		} else {
			return MessageBean.create(-1, "fail", Void.class).toJson();
		}
	}
	
	/**
	 * 获取media_id
	 * @param request
	 * @param bean
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("file/getMedia_id.htm")
	@ResponseBody
	public String getMedia(HttpServletRequest request, @RequestParam("id") Integer id) {
		MessageBean<Integer> msg = new MessageBean<Integer>();
		FileBean fileBean = new FileBean();
		try(SessionFactory factory = new SessionFactory();) {
			fileBean = factory.getMapper(FileMapper.class).getFileById(id);
		}
		if (fileBean != null) {
			String src = fileBean.getUrl();
			File file = new File(Config.getUploadpath());
			file = new File(file, src);
			String url="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+Util.getAccessToken()+"&type="+"image";
			String result=Util.post(url, file);
			String media_id = new Gson().fromJson(result, MediaBean.class).getMedia_id();
			@SuppressWarnings("rawtypes")
			Map map=new HashMap();
			map.put("media_id",media_id);
			return new Gson().toJson(map);
//			msg.setCode(10000);
//			msg.setDescription(result);
		} else {
			msg.setCode(10000);
			msg.setDescription("文件获取失败");
			return new Gson().toJson(msg);
		}
	}
}
