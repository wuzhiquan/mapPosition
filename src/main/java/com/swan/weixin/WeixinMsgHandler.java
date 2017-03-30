package com.swan.weixin;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.swan.weixin.bean.*;

public class WeixinMsgHandler {
	private static final HashMap<Class<?>, Method> map = new HashMap<Class<?>, Method>();
	static {
		registeWeixinBean(WeixinMsgHandler.class);
		registeWeixinBean(WeixinEchoGCE.class);
	}

	public static void registeWeixinBean(Class<?> c, Method m) {
		map.put(c, m);
	}

	public static void registeWeixinBean(Class<?> c) {
		Method[] m = c.getMethods();
		for (Method method : m) {
			WeixinAnno anno = method.getAnnotation(WeixinAnno.class);
			if (anno != null) {
				for (Class<?> annoClass : anno.value()) {
					registeWeixinBean(annoClass, method);
				}
			}
		}
	}

	public static String process(Object o) {
		System.out.println(o);
		if (o == null)
			return "";
		Method m = map.get(o.getClass());
		if (m == null)
			return "";
		try {
			if (m.getReturnType().equals(String.class)) {
				return (String) m.invoke(m.getDeclaringClass().newInstance(), o);
			}
			m.invoke(m.getDeclaringClass().newInstance(), o);
			return "";
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return "";
	}

	@WeixinAnno({ ReplyTextMessageBean.class, ReplyImageMessageBean.class, ReplyVoiceMessageBean.class, ReplyVideoMessageBean.class,
			ReplyMusicMessageBean.class, ReplyNewsMessageBean.class,ReplyCustomerServiceBean.class })
	public String sendMessage(Object c) {
		try {
			Marshaller um = JAXBContext.newInstance(c.getClass()).createMarshaller();
			StringWriter writer = new StringWriter();
			um.marshal(c, writer);
			System.out.println(writer.toString());
			return writer.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	@WeixinAnno(MenuButton.class)
	public void createMenu(MenuButton menu) {
		System.out.println(Util.createMenu(menu));
	}

	@WeixinAnno(RequestUserMessageBean.class)
	public String requestUserMessage(RequestUserMessageBean bean) {
		return Util.requestUserMessage(bean);
	}

	/**
	 * 发送客服消息
	 * 
	 * @param c
	 * @return
	 */
	@WeixinAnno({ ServiceTextMessageBean.class, ServiceImageMessageBean.class, ServiceVoiceMessageBean.class, ServiceVideoMessageBean.class,
			ServiceMusicMessageBean.class, ServiceNewsMessageBean.class })
	public String sendServiceMessage(Object c) {
		String ret =  Util.sendServiceMessage(c);
		if(ret.equals("40001"))
		{
			Util.getAccessToken(true);
			return Util.sendServiceMessage(c);
		}
		return ret;
	}
}
