package com.swan.weixin;

/**
 * 系统配置bean
 * @author Administrator
 *
 */
public class ConfigBean{
	
	private static String appid;//公众号id
	private static String appsecret;//公众号secret
	private static String token;//公众号验证token 
	private static String wXServerPrefix;// 微信服务器前缀
	public static String getAppid() {
		return appid;
	}

	public static void setAppid(String appid) {
		ConfigBean.appid = appid;
	}

	public static String getAppsecret() {
		return appsecret;
	}

	public static void setAppsecret(String appsecret) {
		ConfigBean.appsecret = appsecret;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		ConfigBean.token = token;
	}

	public static String getWXServerPrefix() {
		return wXServerPrefix;
	}

	public static void setWXServerPrefix(String wXServerPrefix) {
		ConfigBean.wXServerPrefix = wXServerPrefix;
	}

      
	

}