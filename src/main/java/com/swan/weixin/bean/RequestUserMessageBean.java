package com.swan.weixin.bean;
public class RequestUserMessageBean{
	/**
	 * 	调用接口凭证
	 */
	private String access_token;
	/**
	 * 是	 普通用户的标识，对当前公众号唯一
	 */
	private String openid;
	/**
	 *  否	 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 */
	private String lang;
	public String getAccess_token() {
		return access_token;
	}
	public String getOpenid() {
		return openid;
	}
	public String getLang() {
		return lang;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	@Override
	public String toString() {
		return "RequestUserMessageBean [access_token=" + access_token + ", openid=" + openid + ", lang=" + lang + "]";
	}
	
}