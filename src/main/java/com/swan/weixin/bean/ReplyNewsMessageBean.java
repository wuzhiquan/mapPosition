package com.swan.weixin.bean;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "xml")
@XmlType(propOrder = { "toUserName", "fromUserName", "createTime", "msgType", "articleCount", "items" })
public class ReplyNewsMessageBean {
	/**
	 * 接收方帐号（收到的OpenID）
	 */
	private String toUserName;
	/**
	 * 开发者微信号
	 */
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private String createTime = String.valueOf(System.currentTimeMillis() / 1000);
	/**
	 * news
	 */
	private String msgType = "news";

	/**
	 * 图文消息内容列表
	 */
	private ArrayList<NewsItemBean> items = new ArrayList<NewsItemBean>();

	@XmlType(propOrder = { "title", "description", "picUrl", "url" })
	static class NewsItemBean {
		/**
		 * 图文消息的标题
		 */
		private String title;
		/**
		 * 图文消息的描述
		 */
		private String description;
		/**
		 * 图片链接
		 */
		private String picUrl;
		/**
		 * 点击图文消息跳转链接
		 */
		private String url;

		@XmlElement(name = "Title")
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@XmlElement(name = "Description")
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@XmlElement(name = "PicUrl")
		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		@XmlElement(name = "Url")
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

	@XmlElement(name = "ToUserName")
	public String getToUserName() {
		return toUserName;
	}

	@XmlElement(name = "FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}

	@XmlElement(name = "CreateTime")
	public String getCreateTime() {
		return createTime;
	}

	@XmlElement(name = "MsgType")
	public String getMsgType() {
		return msgType;
	}

	@XmlElement(name = "ArticleCount")
	public Integer getArticleCount() {
		return items.size();
	}

	@XmlElementWrapper(name = "Articles")
	@XmlElement(name = "item")
	public ArrayList<NewsItemBean> getItems() {

		return items;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setItems(ArrayList<NewsItemBean> items) {
		this.items = items;
	}

	/**
	 * 添加图文消息内容item
	 * 
	 * @param title
	 * @param description
	 * @param picUrl
	 * @param url
	 * @return
	 */
	public boolean addItem(String title, String description, String picUrl, String url) {
		if (items.size() >= 8)
			return false;
		NewsItemBean bean = new NewsItemBean();
		bean.setDescription(description);
		bean.setPicUrl(picUrl);
		bean.setTitle(title);
		bean.setUrl(url);
		items.add(bean);
		return true;
	}
}