package com.koron.web.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.swan.bean.MessageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koron.util.CRUD;
import com.koron.util.Config;
import com.koron.util.ICrud;
import com.koron.util.Tools;
import com.koron.web.bean.FileBean;
import com.koron.web.bean.query.FileQueryBean;
import com.koron.web.mapper.FileMapper;
import com.swan.weixin.Util;


@Controller
public class FileAction implements ICrud {
	public static final String ACTIONKEY = "file";
	public static final String LAYER = "root.file.list";

	@RequestMapping(ACTIONKEY + ".htm")
	public ModelAndView list(FileQueryBean bean) {
		return CRUD.list(this, bean, "list");
	}

	@RequestMapping(ACTIONKEY + "/ajax.htm")
	@ResponseBody
	public String listajax(FileQueryBean bean) {
		return CRUD.listajax(this, bean, "list");
	}

	@RequestMapping(ACTIONKEY + "/pre.htm")
	@ResponseBody
	public ModelAndView preadd(FileBean bean) {
		return CRUD.detail(this, bean, "get");
	}

	@RequestMapping(value = ACTIONKEY + "/update.htm")
	@ResponseBody
	public String add(FileBean bean) {
		return CRUD.save(this, bean, "add", "update").toJson();
	}

	@RequestMapping(value = ACTIONKEY + "/delete.htm")
	@ResponseBody
	public String delete(int id) {
		return CRUD.delete(this, id, "delete").toJson();
	}

	@RequestMapping(ACTIONKEY + "/uploadpre.htm")
	@ResponseBody
	public ModelAndView uploadPre(FileBean bean) {
		ModelAndView view = Tools.getView(this.getActionKey() + "upload.btl");
		Object ret = bean;
		view.addObject("bean", ret);
		view.addObject("flag", new HashMap<String, String>());
		return view;
	}

	/**
	 * 上传文件
	 * 
	 * @param bean
	 * @param file
	 * @return
	 */
	@RequestMapping(value = ACTIONKEY + "/upload.htm")
	@ResponseBody
	public String upload(HttpServletRequest request, FileBean bean, @RequestParam("file") MultipartFile file) {
		Integer ret = FileDeal.addFile(file, bean);
		MessageBean<Integer> msg = new MessageBean<Integer>();
		if (ret == null) {
			msg.setCode(10000);
			msg.setDescription("文件上传失败");
		} else if (ret != -1) {
			msg.setCode(0);
			msg.setData(ret);
			msg.setDescription("文件上传成功");
		} else {
			msg.setCode(10000);
			msg.setDescription("文件上传失败");
		}
		return new Gson().toJson(msg);
	}

	@RequestMapping(value = ACTIONKEY + "/uploadKindEdit.htm")
	@ResponseBody
	public String uploadKindEdit(HttpServletRequest request, FileBean bean,
			@RequestParam("imgFile") MultipartFile file) {	
		Integer ret = FileDeal.addFile(file, bean);
		HashMap<String, Object> map = new HashMap<>();
		if (ret == null) {
			map.put("error", 1);
			map.put("message", "上传图片失败");
		} else if (ret != -1) {
			map.put("error", 0);
			map.put("url", "/file/get.htm?id=" + ret.toString());
			// map.put("error", "1");
			map.put("message", "上传图片成功" + request.getContextPath() + "/file/get.htm?id=" + ret.toString());
		} else {
			map.put("error", 1);
			map.put("message", "上传图片失败");
		}
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		return gb.create().toJson(map);
	}

	@RequestMapping(value = ACTIONKEY + "/listKindEdit.htm")
	@ResponseBody
	public String listKindEdit(HttpServletRequest request, @RequestParam("dir") String dir,
			@RequestParam("path") String path, @RequestParam("order") String order) {

		HashMap<String, String> map = new HashMap<>();
		map.put("moveup_dir_path", "");
		map.put("current_dir_path", "");
		map.put("current_url", "");
		map.put("total_count", "");
		map.put("file_list", "");
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		return gb.create().toJson(map);
	}

	/**
	 * 获取文件 根据key或者id
	 * @param bean
	 * @param response
	 */
	@RequestMapping(value = ACTIONKEY + "/get.htm")
	public void upload(FileQueryBean bean, HttpServletResponse response){
		if (bean.getId() != null) {
			FileDeal.showFile(bean.getId(), response);
		} else if (bean.getKey() != null) {
			FileDeal.showFile(bean.getKey(), response);
		}
	}


	@RequestMapping("/pic/key/{key}.jpg")
	public void show(@PathVariable String key, HttpServletResponse response) {
		FileDeal.showFile(key, response);
	}

	@RequestMapping("/pic/id/{id}.jpg")
	public void show2(@PathVariable Integer id, HttpServletResponse response) {
		FileDeal.showFile(id, response);
	}

	@TaskAnnotation("delete")
	private Integer delete(SessionFactory factory, int id) {
		return factory.getMapper(FileMapper.class).deleteFile(id);
	}

	@TaskAnnotation("update")
	private Integer update(SessionFactory factory, FileBean bean) {
		return factory.getMapper(FileMapper.class).updateFile(bean);
	}

	@TaskAnnotation("list")
	private List<FileBean> list(SessionFactory factory, FileQueryBean bean) {
		bean.setRowNumber(factory.getMapper(FileMapper.class).listFileCount(bean));
		return factory.getMapper(FileMapper.class).listFile(bean);
	}

	@TaskAnnotation("get")
	private FileBean list(SessionFactory factory, Integer id) {
		return factory.getMapper(FileMapper.class).getFileById(id);
	}

	@Override
	public String getActionKey() {
		return ACTIONKEY;
	}

	@Override
	public String getLayer() {
		return LAYER;
	}

	@SuppressWarnings("unused")
	public static void main(String... args) {
		
		File file = new File(Config.getContentpath(), "msg_pic/20172/2017032016302698.png");
		String url="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+Util.getAccessToken()+"&type="+"image";
		String result=Util.post(url, file);
//		MediaBean mediaBean = new MediaBean();
//		mediaBean = Util.getMediaid(file, "image");
//		new Gson().fromJson(result, MediaBean.class);
	}
}
