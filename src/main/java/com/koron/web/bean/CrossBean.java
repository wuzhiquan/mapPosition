package com.koron.web.bean;

import java.util.HashMap;

public class CrossBean {
	private HashMap<String, Object> map = new HashMap<String, Object>();
	/**
	 * @param key
	 * @return
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	public Object get(Object key) {
		return map.get(key);
	}
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(String key, Object value) {
		return map.put(key, value);
	}
	/**
	 * @return 获取#{bare_field_comment}
	 */
	public HashMap<String, Object> getMap() {
		return map;
	}
	/**
	 * @param 设置#{bare_field_comment}
	 */
	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}
}
