package com.koron.util;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.util.field.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koron.web.bean.DefineFieldBean;
import com.koron.web.bean.LayerBean;
import com.koron.web.mapper.UserDefineMapper;

/**
 * <pre>
 * 用来显示自定义字段的函数 
 * 第一个参数为字段定位，第二个为POJO（可无），第三个为字段添加(相同则替换)属性，为JSON格式（可无）
 * </pre>
 * 
 * @author swan
 */
public class FieldFunction implements Function {
	
	public static final String CAPTIONFILTER = "com.koron.util.CaptionFieldBeanFilter";
	@Override
	public Object call(Object[] arg, Context ctx) {
		if (arg == null || arg.length == 0)
			return null;
		if (arg[0] instanceof FieldBean) {
			if (arg.length == 1)
				return FieldBeanProcessor.newInstance().html((FieldBean) arg[0], ((FieldBean) arg[0]).getParam("defaultValue"));
			else if (arg.length == 2)
				return FieldBeanProcessor.newInstance().html((FieldBean) arg[0], arg[1]);
		} else if (arg[0] instanceof String) {
			DefineFieldBean bean = Constant.fieldCache.get(arg[0]);
			if (bean == null) {// 从数据库中获取数据
				String[] str = String.valueOf(arg[0]).split("\\.");
				if (str.length == 0)
					return "";
				String fieldName = str[str.length - 1];
				SessionFactory factory = new SessionFactory();
				UserDefineMapper mapper = factory.getMapper(UserDefineMapper.class);
				int layerId = -1;
				for (int i = 0; i < str.length - 1; i++) {
					LayerBean tmp = mapper.getLayerBeanByNamePid(str[i], layerId);
					layerId = tmp.getId();
				}
				bean = mapper.getFieldBean(layerId, fieldName);
				factory.close();
				Constant.fieldCache.put(String.valueOf(arg[0]), bean);
			}
			if (bean != null) {
				return beanToHtml(arg, ctx, bean);
			}
		}
		return "";
	}

	public static final String beanToHtml(Object[] arg, final Context ctx, DefineFieldBean bean) {
		String tmp = bean.getDefaultValue();
		Object val = null;
		if (arg.length > 1)
			val = arg[1];
		if (val != null) {
			Object o = null;
			try {
				if (PropertyUtils.isReadable(val, bean.getName()))
					o = PropertyUtils.getProperty(val, bean.getName());
				else
					o = PropertyUtils.getProperty(val, "map(" + bean.getName() + ")");
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			}
			if (o != null)
				tmp = o.toString();
		}
		if (arg.length > 2)// 如果有设置属性
		{
			LinkedHashMap<String, String> map = new Gson().fromJson(arg[2].toString(), new TypeToken<LinkedHashMap<String, String>>() {
			}.getType());
			for (Entry<String, String> entry : map.entrySet()) {
				bean.setParam(entry.getKey(), entry.getValue());
			}
		}
		FieldBeanProcessor processor = FieldBeanProcessor.newInstance();
		if (ctx != null)
			processor.addListener(new FieldParseListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void action(FieldParseEvent event) {
					if (ctx.getGlobal("flag") == null || !(ctx.getGlobal("flag") instanceof Map)) {
						return;
					}
					switch (event.getEventType()) {
					case FieldParseEvent.HAS_DATE:
						((Map<String, String>) ctx.getGlobal("flag")).put("UD_DATE", "true");
						break;
					case FieldParseEvent.HAS_PIC:
						((Map<String, String>) ctx.getGlobal("flag")).put("UD_PIC", "true");
						break;
					case Constant.HAS_ASSIST:
						((Map<String, String>) ctx.getGlobal("flag")).put("UD_HAS_ASSIST", "true");
						break;
					default:
						break;
					}
				}
			});
		return processor.html(bean, tmp);
	}
}