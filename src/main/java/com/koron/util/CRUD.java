package com.koron.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.koron.ebs.util.field.EnumElement;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.servlet.ModelAndView;
import org.swan.bean.MessageBean;

import com.google.gson.Gson;
import com.koron.web.bean.CrossBean;
import com.koron.web.bean.DefineFieldBean;
import com.koron.web.bean.IdentityBean;
import com.koron.web.bean.query.BaseQueryBean;
/**
 * 实现基础的CRUD功能
 * @author swan
 */
public class CRUD {
	/**
	 * 列表方式展示数据列表
	 * @param crud 实现CRUD接口
	 * @param bean 查询BEAN
	 * @param tag 执行方法的标签,参看{@link TaskAnnotation}
	 * @return spring视图
	 */
	
	public static final ModelAndView list(ICrud crud, BaseQueryBean bean, String tag) {
		ModelAndView view = Tools.getView(crud.getActionKey() + ".btl", 3);
		@SuppressWarnings("resource")
		List<?> list = new SessionFactory().runTask(crud, tag, List.class, bean, view);
		view.addObject("datalist", list);
		view.addObject("condition", new Gson().toJson(bean));
		view.addObject("layer", FieldGroupFunction.getFieldBean(crud.getLayer()));
		view.addObject("flag", new HashMap<String, String>());
		
		return view;
	}
	
	@SuppressWarnings("unchecked")
	public static final ModelAndView cross(ICrud crud, BaseQueryBean bean, String tag, CrossConfigBean config) {
		ModelAndView view = Tools.getView(crud.getActionKey() + ".btl", 3);
		view.addObject("flag", new HashMap<String, String>());
		
		@SuppressWarnings("resource")
		HashMap<String, Object> map =cross(new SessionFactory().runTask(crud, tag, List.class, bean, view),config,crud.getLayer());
		view.addObject("datalist", map.get("datalist"));
		view.addObject("layer", map.get("layer"));
		view.addObject("condition", new Gson().toJson(bean));
		return view;
	}
	
	/**
	 * AJAX方式获取数据列表
	 * @param crud 实现CRUD接口
	 * @param bean 查询BEAN
	 * @param tag 执行方法的标签,参看{@link TaskAnnotation}
	 * @return json数据
	 */
	
	public static final String listajax(ICrud crud, BaseQueryBean bean, String tag){
		@SuppressWarnings("resource")
		List<?> list = new SessionFactory().runTask(crud, tag, List.class, bean, null);
		List<Object> data = new ArrayList<>();
		String layer = crud.getLayer();
		FieldGroupFunction function = new FieldGroupFunction();
		for (Object o : list) {
			data.add(function.call(new Object[] { layer, o }, null));
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("datalist", data);
		map.put("data", list);
		map.put("condition", bean);
		return new Gson().toJson(map);
	}
	/**
	 * 导出数据
	 * @param crud 实现CRUD接口
	 * @param bean 查询BEAN
	 * @param tag 执行方法的标签,参看{@link TaskAnnotation}
	 * @return spring视图
	 */
	public static final String export(ICrud crud, BaseQueryBean bean, String tag,HttpServletResponse response) {
		return null;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final String listajaxCross(ICrud crud, BaseQueryBean bean, String tag,CrossConfigBean config) {
		@SuppressWarnings("resource")
		List<CrossBean> list = new SessionFactory().runTask(crud, tag, List.class, bean, null);
		List<Object> data = new ArrayList<>();
		HashMap<String, Object> crossMap =cross(list,config,crud.getLayer());

		FieldGroupFunction function = new FieldGroupFunction();
		for (Object o : (Collection)crossMap.get("datalist")) {
			data.add(function.call(new Object[] { crossMap.get("layer"), o }, null));
		}
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("datalist", data);
		map.put("data", crossMap.get("datalist"));
		map.put("condition", bean);
		return new Gson().toJson(map);
	}

	/**
	 * 获取明细功能
	 * @param crud 实现CRUD接口
	 * @param pojo 查询BEAN
	 * @param tag 执行方法的标签,参看{@link TaskAnnotation}
	 * @return spring视图
	 */
	public static final ModelAndView detail(ICrud crud, IdentityBean pojo, String tag) {
		ModelAndView view = Tools.getView(crud.getActionKey() + "pre.btl", 3);
		Object ret = pojo;
		if (pojo.getId() != -1) {
			ret = new SessionFactory().runTask(crud, tag,pojo.getClass(),pojo.getId(), view);
		}
		view.addObject("flag", new HashMap<String, String>());
		view.addObject("bean", ret);
		return view;
	}
	

	/**
	 * <pre>
	 * 存盘功能 
	 * id为-1则insert,否则update
	 * </pre>
	 * 
	 * @param crud crud接口
	 * @param pojo 包含ID的POJO对象
	 * @param insertTag 执行插入的标签,参看{@link TaskAnnotation}
	 * @param updateTag 执行更新的标签,参看{@link TaskAnnotation}
	 * @return 消息BEAN
	 */
	public static final MessageBean<Integer> save(ICrud crud, IdentityBean pojo, String insertTag, String updateTag) {
		Integer ret = 0;
		String item = "添加";
		if (pojo.getId() == -1) {
			ret = new SessionFactory().runTask(crud, insertTag, Integer.class, pojo);
		} else {
			ret = new SessionFactory().runTask(crud, updateTag, Integer.class, pojo);
			item = "修改";
		}
		MessageBean<Integer> msg = new MessageBean<Integer>();
		msg.setData(ret);
		if (ret == null) {
			msg.setCode(Constant.MESSAGE_DBFAIL);
			msg.setDescription("数据" + item + "失败。");
		} else if (ret > 0) {
			msg.setCode(0);
			msg.setDescription("已" + item + ret + "条数据。");
		} else {
			msg.setCode(Constant.MESSAGE_DBFAIL);
			msg.setDescription("数据" + item + "失败。");
		}
		return msg;
	}

	/**
	 * 直接执行某个写操作(insert delete updae)
	 * 
	 * @param crud crud接口
	 * @param pojo 包含ID的POJO对象
	 * @param tag 执行方法的标签,参看{@link TaskAnnotation}
	 * @return 消息BEAN
	 */
	@SuppressWarnings("resource")
	public static final MessageBean<Integer> execute(ICrud crud, Object[] pojo, String tag, String item) {
		Integer ret = 0;
		ret = new SessionFactory().runTask(crud, tag, Integer.class, Arrays.asList(pojo));
		MessageBean<Integer> msg = new MessageBean<Integer>();
		msg.setData(ret);
		if (ret == null) {
			msg.setCode(Constant.MESSAGE_DBFAIL);
			msg.setDescription("数据" + item + "失败。");
		} else if (ret >0) {
			msg.setCode(0);
			msg.setDescription("已" + item + ret + "条数据。");
		} else {
			msg.setCode(Constant.MESSAGE_DBFAIL);
			msg.setDescription("数据" + item + "失败。");
		}
		return msg;
	}

	/**
	 * 删除功能
	 * 
	 * @param crud crud接口
	 * @param id 删除数据的ID
	 * @param tag 执行方法的标签,参看{@link TaskAnnotation}
	 * @return
	 */
	@SuppressWarnings("resource")
	public static final MessageBean<Integer> delete(ICrud crud, int id, String tag) {
		Integer ret = 0;
		ret = new SessionFactory().runTask(crud, tag, Integer.class, id);
		MessageBean<Integer> msg = new MessageBean<Integer>();
		msg.setData(ret);
		if (ret == null) {
			msg.setCode(Constant.MESSAGE_DBFAIL);
			msg.setDescription("数据删除失败。");
		} else if (ret == 1) {
			msg.setCode(0);
			msg.setDescription("已删除" + ret + "条数据。");
		} else {
			msg.setCode(Constant.MESSAGE_DBFAIL);
			msg.setDescription("数据删除失败。");
		}
		return msg;
	}
	/**
	 * 批量删除功能
	 * 
	 * @param crud crud接口
	 * @param id 删除数据的ID
	 * @param tag 执行方法的标签,参看{@link TaskAnnotation}
	 * @return
	 */
	@SuppressWarnings("resource")
	public static final MessageBean<Integer> delete(ICrud  crud, List<Integer> ids, String tag) {
		Integer ret = 0;
		ret = new SessionFactory().runTask(crud, tag, Integer.class, ids);
		MessageBean<Integer> msg = new MessageBean<Integer>();
		msg.setData(ret);
		if (ret == null) {
			msg.setCode(Constant.MESSAGE_DBFAIL);
			msg.setDescription("数据删除失败。");
		} else if (ret == ids.size()) {
			msg.setCode(0);
			msg.setDescription("已删除" + ret + "条数据。");
		} else {
			msg.setCode(Constant.MESSAGE_DBFAIL);
			msg.setDescription("数据删除失败。");
		}
		return msg;
	}
	
	
	public static class CrossConfigBean {
		/**
		 * 定义交叉的列，如果为NULL则根据实际情况进行交叉，否则根据这值进行交叉
		 */
		private LinkedHashMap<Object, String> columns;
		/**
		 * 标识是同一行的数据的字段名字
		 */
		private String key;
		/**
		 * 要交叉的列
		 */
		private String crossField;
		/**
		 * 交叉的值
		 */
		private String[] valueString;

		/**
		 * @return 获取定义交叉的列，如果为NULL则根据实际情况进行交叉，否则根据这值进行交叉
		 */
		public LinkedHashMap<Object, String> getColumns() {
			return columns;
		}

		/**
		 * @param 设置定义交叉的列，如果为NULL则根据实际情况进行交叉，否则根据这值进行交叉
		 */
		public void setColumns(LinkedHashMap<Object, String> columns) {
			this.columns = columns;
		}

		/**
		 * @return 获取标识是同一行的数据
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @param 设置标识是同一行的数据
		 */
		public void setKey(String key) {
			this.key = key;
		}

		/**
		 * @return 获取要交叉的列
		 */
		public String getCrossField() {
			return crossField;
		}

		/**
		 * @param 设置要交叉的列
		 */
		public void setCrossField(String crossField) {
			this.crossField = crossField;
		}

		/**
		 * @return 获取交叉的值
		 */
		public String[] getValueString() {
			return valueString;
		}

		/**
		 * @param 设置交叉的值
		 */
		public void setValueString(String[] valueString) {
			this.valueString = valueString;
		}
		/**
		 * @param  枚举key值 设置定义交叉的列，如果为NULL则根据实际情况进行交叉，否则根据这值进行交叉
		 */
		public void setColumns(String enumKey) {
			EnumElement<Object> enums = Tools.getEnumByKey(enumKey);
			setColumns((LinkedHashMap<Object, String>) enums.getItem());
		}
	}
	
	public static final HashMap<String, Object> cross(List<CrossBean> list, CrossConfigBean config,String layer) {
		List<DefineFieldBean> beans = FieldGroupFunction.getFieldBean(layer);
		// 原始数据
		LinkedHashMap<Object, String> columns= new LinkedHashMap<>();
		LinkedHashMap<String, CrossBean> map = new LinkedHashMap<String, CrossBean>();
		// 如果没指定交叉的列，则从数据中取到交叉的列
		if (config.getColumns() == null) {
			for (CrossBean crossBean : list) {
				BeanMap bm = BeanMap.create(crossBean);
				String column = String.valueOf(bm.get(config.getCrossField()));
				columns.put(column, column);
			}
		}else
		{
			for (Entry<Object, String> entry: config.getColumns().entrySet()) {
				columns.put(entry.getKey(),entry.getValue());
			}
		}
		
		//给字段增加上交叉的列
		List<DefineFieldBean> tmps = new ArrayList<DefineFieldBean>();
		for (int i =0;i<beans.size();i++) {
			DefineFieldBean defineBean = beans.get(i);
			if(defineBean.getName().equals(config.getCrossField()))
			{
				Set<Entry<Object, String>> sets=columns.entrySet();
				for (Entry<Object, String> set: sets) {
					DefineFieldBean tmp = defineBean.clone();
					tmp.setCaption(set.getValue());
					tmp.setName(config.getValueString()[0]+String.valueOf(set.getKey()));
					tmps.add(tmp);
				}
			}else
				tmps.add(defineBean);
		}
		beans = tmps;
		// 依次从原始数据中取出数据并放进map中进行分行处理
		for (CrossBean crossBean : list) {
			BeanMap bm = BeanMap.create(crossBean);
			if (!map.containsKey(String.valueOf(bm.get(config.getKey())))) {
				//如果处理的数据不存在
				map.put(String.valueOf(bm.get(config.getKey())), crossBean);
				Set<Entry<Object, String>> sets=columns.entrySet();
				for (Entry<Object, String> set: sets) {
					crossBean.put(config.getValueString()[0]+String.valueOf(set.getKey()),"");
				}
			}
			setValue(config, bm,map.get(bm.get(config.getKey())));
			System.out.println("");
		}
		HashMap<String, Object> ret = new HashMap<String, Object>();
		ret.put("datalist",map.values());
		ret.put("layer", beans);
		return ret;
	}
	/**
	 * @param config 交叉配置
	 * @param bm 原始BEAN
	 * @param target 转换到了目标BEAN
	 */
	private static final void setValue(CrossConfigBean config,BeanMap bm,CrossBean target){
		target.put(config.getValueString()[0]+bm.get(config.getCrossField()), bm.get(config.getValueString()[0]));
	}
}
