package com.koron.util;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.koron.ebs.common.KVBean;
import org.koron.ebs.module.ModuleService;
import org.koron.ebs.mybatis.MybatisInfo;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.koron.ebs.util.field.EnumElement;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.swan.bean.MessageBean;

import com.koron.web.bean.EnumDBBean;
import com.koron.web.bean.EnumDetailDBBean;
import com.koron.web.mapper.UserDefineMapper;

public class Tools {
	public static final ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();

	private static ModuleService service = null;

	/**
	 * 获取模块服务组件
	 * 
	 * @return
	 */
	public static final ModuleService getModuleService() {
		if (service == null) {
			WebApplicationContext context2 = WebApplicationContextUtils.getWebApplicationContext(context);
			return (ModuleService) context2.getBean("moduleService");
		}
		return service;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static final SessionFactory getSessionFactory() {
		MessageBean<SessionFactory> msgBean = getModuleService().invoke(MybatisInfo.MODULENAME, "getSessionFactory", SessionFactory.class);
		return msgBean.getCode() == 0 ? msgBean.getData() : null;
	}

	/**
	 * 取得调用该函数的类的包下name对应的文件
	 * 
	 * @param name
	 *            名称
	 * @param layer
	 *            线程中的层数，默认为3
	 * @return
	 */
	public static final ModelAndView getView(String name, int layer) {
		StackTraceElement[] els = Thread.currentThread().getStackTrace();
		if (els == null || els.length < layer + 1)
			return new ModelAndView("/" + name);
		StackTraceElement el = els[layer];
		String pkgStr = el.getClassName();
		if (pkgStr.indexOf('.') != -1) {
			pkgStr = pkgStr.substring(0, pkgStr.lastIndexOf('.'));
			return new ModelAndView("/" + pkgStr.replace('.', '/') + '/' + name);
		} else {
			return new ModelAndView("/" + name);
		}
	}

	/**
	 * 取得调用该函数的类的包下name对应的文件
	 * 
	 * @param name
	 *            名称
	 * @return
	 */
	public static final ModelAndView getView(String name) {
		return getView(name, 3);
	}

	/**
	 * 获取MD5后的字符串
	 * 
	 * @param source
	 *            进行加密的字符串
	 * @return
	 */
	public static String MD5(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte c : b) {
				int val = ((int) c) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));
			}
			return sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取枚举
	 * 
	 * @param key
	 *            枚举的key
	 * @return
	 */
	@SuppressWarnings({ "resource", "unchecked" })
	public static final EnumElement<Object> getEnumByKey(String key) {
		EnumElement<Object> ret = Constant.enumCache.get(key);
		if (ret != null)
			return ret;
		return new SessionFactory().runTask(new Tools(), "getEnum", EnumElement.class, key);
	}

	/**
	 * 获取枚举对应key的value
	 * @param enumKey
	 * @param key
	 * @return
	 */
	public static final Object getEnumValue(String enumKey, Object key) {
		if ( Constant.enumCache.get(enumKey) == null)
		new SessionFactory().runTask(new Tools(), "getEnum", EnumElement.class, enumKey);
		EnumElement<Object> ret = Constant.enumCache.get(enumKey);
		return ret.getItem().get(key);
	}
	/**
	 * 
	 * @param factory
	 * @param key
	 * @return
	 */
	@TaskAnnotation("getEnum")
	private EnumElement<Object> getEnumByKey(SessionFactory factory, String key) {
		Logger.getLogger(this.getClass()).debug("获取指定的枚举值：" + key);
		UserDefineMapper mapper = factory.getMapper(UserDefineMapper.class);
		EnumDBBean bean = mapper.getEnumByKey(key);
		EnumElement<Object> ret = new EnumElement<>();
		ret.setBit(bean.getIsbit());
		ret.setType(bean.getType());
		if (bean.getParam() != null && !bean.getParam().isEmpty()) {
			Map<String, String> map = getDyn(bean.getParam());
			for (Entry<String, String> item : map.entrySet()) {
				switch (bean.getType()) {
				case 0:
					ret.put(item.getKey(), item.getValue());
					break;
				case 1:
					ret.put(Integer.parseInt(item.getKey()), item.getValue());
					break;
				case 2:
					ret.put(Long.parseLong(item.getKey()), item.getValue());
				}
			}
		} else {
			List<EnumDetailDBBean> list = mapper.getEnumDetailByEnumId(bean.getId());
			for (EnumDetailDBBean enumDetailDBBean : list) {
				switch (bean.getType()) {
				case 0:
					ret.put(enumDetailDBBean.getKey(), enumDetailDBBean.getValue());
					break;
				case 1:
					ret.put(Integer.parseInt(enumDetailDBBean.getKey()), enumDetailDBBean.getValue());
					break;
				case 2:
					ret.put(Long.parseLong(enumDetailDBBean.getKey()), enumDetailDBBean.getValue());
				}
			}
		}
		Constant.enumCache.put(key, ret);
		return ret;
	}

	/**
	 * 根据类名#方法的方式获取得KVBean数组，然后转换成map
	 * 
	 * @param url
	 *            mybatis的接口名加方法名,方法不带参数，返回必须为List<KVBean>
	 * @return
	 */
	public static final LinkedHashMap<String, String> getDyn(String url) {
		if (url == null || url.indexOf('#') == -1)
			return null;
		String className = url.substring(0, url.indexOf('#'));
		String methodName = url.substring(url.indexOf('#') + 1);
		try {
			SessionFactory factory = new SessionFactory();
			Class<?> clazz = Class.forName(className);
			Method m = clazz.getMethod(methodName);
			@SuppressWarnings("unchecked")
			List<KVBean> list = (List<KVBean>) m.invoke(factory.getMapper(clazz));
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			for (KVBean kvBean : list) {
				map.put(kvBean.getKey(), kvBean.getValue());
			}
			factory.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 接口调用校验
	 * @param account 帐号
	 * @param content 内容
	 * @param time 调时时间
	 * @param signature 签名
	 * @return
	 */
	public static boolean validate(String account, String content, long time, String signature) {
		if (time - System.currentTimeMillis() > 60000)// 一分钟内有效
		{
			return false;
		}
		if (MD5(account + time + content).equals(signature))
			return true;
		return false;
	}
	public static void main(String[] args) {
		System.out.println(MD5("460033197605220378"+System.currentTimeMillis()+"bingd"));
	}
}
