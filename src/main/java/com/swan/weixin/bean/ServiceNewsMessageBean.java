package com.swan.weixin.bean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 客服图文消息
 * @author Administrator
 *
 */
public class ServiceNewsMessageBean{
	
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，news
	 */
	private String msgtype="news";
	
	/**
	 * 图文消息内容
	 */
	private HashMap<String, ArrayList<SubNewsMessageBean>> news=new HashMap<String, ArrayList<SubNewsMessageBean>>();
	
	public void setTouser(String touser){
		this.touser = touser;
	}
	public String getTouser(){
		return touser;
	}
	public String getMsgtype(){
		return msgtype;
	}
	public ArrayList<SubNewsMessageBean> getArticles() {
		return news.get("articles");
	}
	public void setArticles(ArrayList<SubNewsMessageBean> articles) {
		news.put("articles", articles);
	}
	/**
	 * 添加图文消息item  超过10条返回false
	 * @param title
	 * @param description
	 * @param url
	 * @param picurl
	 * @return
	 */
	public boolean addItem(String title,String description,String url,String picurl){
		ArrayList<SubNewsMessageBean> articles=news.get("articles");
		if(articles==null){
			articles=new ArrayList<SubNewsMessageBean>();
		}else{
			if(articles.size()>=10){
				return false;
			}
		}
		articles.add(new SubNewsMessageBean(title, description, url, picurl));
		news.put("articles", articles);
		return true;
	}
}