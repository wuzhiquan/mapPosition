package com.koron.util;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SwanRequestWrapper extends HttpServletRequestWrapper {
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	private HashMap<String, String[]> data = new HashMap<String, String[]>();

	public SwanRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String name) {
		String[] s = data.get(name);
		if (s == null || s.length == 0)
			return super.getParameter(name);
		return s[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequestWrapper#getParameterMap()
	 */
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> ret = super.getParameterMap();
		ret.putAll(data);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequestWrapper#getParameterNames()
	 */
	@Override
	public Enumeration<String> getParameterNames() {
		Set<String> ret = new TreeSet<String>(data.keySet());
		Enumeration<String> params = super.getParameterNames();
		if (params != null) {
			while (params.hasMoreElements())
				ret.add(params.nextElement());
		}
		return Collections.enumeration(ret);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
	 */
	@Override
	public String[] getParameterValues(String name) {
		String[] ret = data.get(name);
		if (ret != null && ret.length > 0)
			return ret;
		return super.getParameterValues(name);
	}

	public String[] put(String key, String... value) {
		return data.put(key, value);
	}
	
}