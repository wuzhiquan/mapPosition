package com.swan.weixin;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.koron.util.SwanRequestWrapper;

public class SaveRequest{
	private static Random r = new Random();
	private static String tmp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567895a";//64
	private static byte[] c = tmp.getBytes();
	private static final HashMap<String, SavedRequestBean> requestMap = new HashMap<>(); 
	private static String getAvailableState(){
		String state = null;
		String temp;
		byte[] ret = new byte[6];
		for(int i = 0; i<3; i++){
			for (int j = 0; j < 6; j++) {
				int tmp = r.nextInt();
				tmp = tmp & 0x3F;
				ret[j] = c[tmp];
			}
			temp = new String(ret);
			if(!requestMap.containsKey(temp)){
				state = temp;
				break;
			}
		}
		return state;
	}
	public synchronized static String add(HttpServletRequest request){
		String state = getAvailableState();
		if(state != null){
			SavedRequestBean bean = new SavedRequestBean();
			//保存请求参数
			Map<String, String[]> data = new HashMap<>();
			Map<String, String[]> rqData = request.getParameterMap();
			for(Entry<String, String[]> entry:rqData.entrySet()){
				data.put(entry.getKey(), entry.getValue());
			}
			bean.setData(data);
			bean.setTimestamp(System.currentTimeMillis()+180000);
			requestMap.put(state, bean);
		}
		return state;
	} 
	
	public synchronized static void  setSavedRequestData(String state, SwanRequestWrapper request){
		Map<String, String[]> map = new HashMap<>();
		SavedRequestBean bean = requestMap.get(state);
		if(bean != null)
			if(bean.getTimestamp() > System.currentTimeMillis())
				map.putAll(bean.getData());
			else
				requestMap.remove(state);
		for (Entry<String,String[]> entry : map.entrySet()) {
				request.put(entry.getKey(), entry.getValue());
		}
	}
	
	static class SavedRequestBean {
		/**
		 * 请求数据
		 */
		private Map<String, String[]> data;
		/**
		 * 时间戳
		 */
		private Long timestamp;
		
		public Map<String, String[]> getData() {
			return data;
		}
		public void setData(Map<String, String[]> data) {
			this.data = data;
		}
		public Long getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}
	}
}
