package com.swan.weixin.bean;
/**
 * 用来生成带参数的二维码的ＢＥＡＮ
 * @author swan
 *
 */
public class QRCodeBean {
	/**
	 * 临时二维码
	 * @see #QR_LIMIT_SCENE
	 * @see #setAction_name(String)
	 * @see #getAction_name()
	 */
	public static final String QR_SCENE = "QR_SCENE";
	/**
	 * 永久二维码
	 * @see #QR_SCENE
	 * @see #setAction_name(String)
	 * @see #getAction_name()
	 */
	public static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
	/**
	 * 该二维码有效时间，以秒为单位。 最大不超过1800。
	 */
	int expire_seconds;
	/**
	 * 	 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
	 */
	String action_name;
	/**
	 * 	 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 */
	int scene_id;
	/**
	 * 获取二维码返回的ticket
	 */
	String ticket;
	public int getExpire_seconds() {
		return expire_seconds;
	}
	/**
	 * 获取二维码类型
	 * @return 二维码类型
	 * @see #QR_LIMIT_SCENE 临时二维码
	 * @see #QR_SCENE　永久二维码
	 */
	public String getAction_name() {
		return action_name;
	}
	public int getScene_id() {
		return scene_id;
	}
	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	/**
	 * 设置action_name
	 * @param action_name
	 * @see #QR_LIMIT_SCENE 临时二维码
	 * @see #QR_SCENE　永久二维码
	 */
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	public void setScene_id(int scene_id) {
		this.scene_id = scene_id;
	}
	/**
	 * 获取生成二维时用的字符串
	 * @return
	 */
	public String getQueryString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(getAction_name().equals(QR_SCENE))//如果是临时二维码则要把过期时间设置上去
		{
			sb.append("\"expire_seconds\": ").append(getExpire_seconds()).append(",");
		}
		sb.append("\"action_name\": \"").append(getAction_name()).append("\",");
		sb.append("\"action_info\": {\"scene\": {\"scene_id\": ").append(getScene_id()).append("}}");
		sb.append("}");
		return sb.toString();
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "QRCodeBean [expire_seconds=" + expire_seconds + ", action_name=" + action_name + ", scene_id=" + scene_id + ", ticket=" + ticket + "]";
	}
}
