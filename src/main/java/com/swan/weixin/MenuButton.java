package com.swan.weixin;

import java.util.List;

public class MenuButton {
	public static final String VIEW = "view";
	public static final String CLICK = "click";

	MenuBean[] button;

	public MenuBean[] getButton() {
		return button;
	}

	public void setButton(MenuBean[] button) {
		this.button = button;
	}

	public final static class MenuBean {
		String type;// 是 菜单的响应动作类型，目前有click、view两种类型
		String name;// 是 菜单标题，不超过16个字节，子菜单不超过40个字节
		String key;// click类型必须 菜单KEY值，用于消息接口推送，不超过128字节
		String url;// view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节

		List<MenuBean> sub_button;

		@Override
		public String toString() {
			return "MenuBean [type=" + type + ", name=" + name + ", key=" + key + ", url=" + url + ", sub_button=" + sub_button + "]";
		}

		public String getType() {
			return type;
		}

		public String getName() {
			return name;
		}

		public String getKey() {
			return key;
		}

		public String getUrl() {
			return url;
		}

		public List<MenuBean> getSub_button() {
			return sub_button;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setKey(String key) {
			if (VIEW.equals(type)) {
				this.url = key;
			} else
				this.key = key;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setSub_button(List<MenuBean> sub_button) {
			this.sub_button = sub_button;
		}
	}
}