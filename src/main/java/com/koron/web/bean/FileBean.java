package com.koron.web.bean;

import java.sql.Timestamp;

public class FileBean implements IdentityBean{
	/**
	 * 消息图片
	 */
	public static final Integer TYPE_MSG_IMAGE = 1;
	/**
	 * 消息语音
	 */
	public static final Integer TYPE_MSG_VOICE = 2;
	private Integer id;
	/**
	 * 资源标识名
	 */
	private String key;
	/**
	 * 资源链接地址（系统内部链接或者外部链接的地址）
	 */
	private String url;
	/**
	 * 资源媒体类型
	 */
	private String mimetype;
	/**
	 * 资源描述
	 */
	private String description;
	/**
	 * 资源被引用次数
	 */
	private Integer linknum=0;
	/**
	 * 文件类型
	 */
	private Integer type;
	/**
	 * 资源创建时间
	 */
	private Timestamp createtime;
	/**
	*设置
	*/
	public void setId(Integer id){
		this.id = id;
	}
	/**
	*获取
	*/
	@Override
	public Integer getId(){
		return id;
	}
	/**
	*设置标识资源名
	*/
	public void setKey(String key){
		this.key = key;
	}
	/**
	*获取标识资源名
	*/
	public String getKey(){
		return key;
	}
	/**
	*设置链接地址
	*/
	public void setUrl(String url){
		this.url = url;
	}
	/**
	*获取链接地址
	*/
	public String getUrl(){
		return url;
	}
	/**
	*设置类型
	*/
	public void setMimetype(String mimetype){
		this.mimetype = mimetype;
	}
	/**
	*获取类型
	*/
	public String getMimetype(){
		return mimetype;
	}
	/**
	*设置描述
	*/
	public void setDescription(String description){
		this.description = description;
	}
	/**
	*获取描述
	*/
	public String getDescription(){
		return description;
	}
	/**
	*设置被引用次数
	*/
	public void setLinknum(Integer linknum){
		this.linknum = linknum;
	}
	/**
	*获取被引用次数
	*/
	public Integer getLinknum(){
		return linknum;
	}
	/**
	*设置0 默认产品图片，1自定义图片....
	*/
	public void setType(Integer type){
		this.type = type;
	}
	/**
	*获取0 默认产品图片，1自定义图片....
	*/
	public Integer getType(){
		return type;
	}
	/**
	*设置资源创建时间
	*/
	public void setCreatetime(Timestamp createtime){
		this.createtime = createtime;
	}
	/**
	*获取资源创建时间
	*/
	public Timestamp getCreatetime(){
		return createtime;
	}
}