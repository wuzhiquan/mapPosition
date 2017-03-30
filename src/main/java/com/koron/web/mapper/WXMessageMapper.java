package com.koron.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koron.web.bean.WXMessageBean;
import com.koron.web.bean.WXMessageSendBean;
import com.swan.weixin.bean.ReceiveImageMessageBean;
import com.swan.weixin.bean.ReceiveLocationMessageBean;
import com.swan.weixin.bean.ReceiveTextMessageBean;

public interface WXMessageMapper {

	/**
	 * 添加消息
	 * @param bean
	 * @param userid
	 * @return
	 */
	public Integer addWXTextMessage(@Param("bean")ReceiveTextMessageBean bean, @Param("userid")Integer userid);
	public Integer addWXMessage(WXMessageBean bean);
	/**
	 * 添加图片
	 * @param bean
	 * @param userid
	 * @return
	 */
	public Integer addWXImageMessage(@Param("bean")ReceiveImageMessageBean bean, @Param("userid")Integer userid);
	/**
	 * 添加位置
	 * @param bean
	 * @param userid
	 * @return
	 */
	public Integer addWXLocationMessage(@Param("bean")ReceiveLocationMessageBean bean, @Param("userid")Integer userid);
	/**
	 * 获取消息列表
	 * @param bean
	 * @return
	 */
	public 	List<WXMessageBean> getList();
	/**
	 * 更新查看状态
	 * @param bean
	 * @return
	 */
	public Integer updateStatus(WXMessageBean bean);
	/**
	 * 根据msgId获取消息
	 * @param msgId
	 * @return
	 */
	public WXMessageBean getWXMessageBeanByMsgid(@Param("msgId")String msgId);
	/**
	 * 插入控制中心向公众号发送的信息
	 * @param bean
	 * @return
	 */
	public Integer addWXMessageSend(WXMessageSendBean bean);
	/**
	 * 获取全部消息列表
	 * @param bean
	 * @return
	 */
	public 	List<WXMessageBean> getAllList(@Param("userid")Integer userid);
}
