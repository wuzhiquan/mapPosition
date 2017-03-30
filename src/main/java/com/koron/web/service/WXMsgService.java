package com.koron.web.service;

import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.SqlTask;

import com.koron.web.bean.FileBean;
import com.koron.web.bean.PersonBean;
import com.koron.web.bean.WXMessageBean;
import com.koron.web.mapper.FileMapper;
import com.koron.web.mapper.InputMapper;
import com.koron.web.mapper.WXMessageMapper;
import com.swan.weixin.Util;
import com.swan.weixin.bean.ReceiveImageMessageBean;
import com.swan.weixin.bean.ReceiveLocationMessageBean;
import com.swan.weixin.bean.ReceiveTextMessageBean;

public class WXMsgService {
	/**
	 * 添加普通消息
	 * @param bean
	 */
   @SuppressWarnings("resource")
   public static final void add(ReceiveTextMessageBean bean) {
	 new SessionFactory().runTask(new SqlTask() {
		@Override
		public Object run(SessionFactory factory) {
			 Integer userid = check(factory, bean.getMsgId(), bean.getFromUserName());
			 if (userid != null) {
				 factory.getMapper(WXMessageMapper.class).addWXTextMessage(bean, userid);
			 }
			return null;
		}
	},Void.class);   
   }
   /**
    * 添加图片消息
    * @param bean
    */
   @SuppressWarnings("resource")
   public static final void add(ReceiveImageMessageBean bean) {
	   new SessionFactory().runTask(new SqlTask() {
			@Override
			public Object run(SessionFactory factory) {
				 Integer userid = check(factory, bean.getMsgId(), bean.getFromUserName());
				 if (userid != null) {
					 FileBean file = Util.getFileByMedia_id(bean.getMediaId(), FileBean.TYPE_MSG_IMAGE);
					 if(file != null) {
						 factory.getMapper(FileMapper.class).addFile(file);
						 bean.setPicUrl(file.getId().toString());
					 }
					 factory.getMapper(WXMessageMapper.class).addWXImageMessage(bean, userid);
				 }
				return null;
			}
		},Void.class);   
   }  
   
   /**
    * 添加位置信息
    * @param bean
    */
   @SuppressWarnings("resource")
   public static final void add(ReceiveLocationMessageBean bean) {
	   new SessionFactory().runTask(new SqlTask() {
		   @Override
			public Object run(SessionFactory factory) {
				 Integer userid = check(factory, bean.getMsgId(), bean.getFromUserName());
				 if (userid != null) {
					 factory.getMapper(WXMessageMapper.class).addWXLocationMessage(bean, userid);
				 }
				return null;
			}
		},Void.class);   
   }  
   /**
    * 检查msgid是否存在并获取userid
    * @param factory
    * @param msgId
    * @param openid
    * @return
    */
   private static Integer check(SessionFactory factory, String msgId, String openid) {
	    WXMessageMapper mapper = factory.getMapper(WXMessageMapper.class);
	    // 首先判断msgid存不存在
		WXMessageBean msg = mapper.getWXMessageBeanByMsgid(msgId);
		// 首先根据openid 找到userid;
		PersonBean user = factory.getMapper(InputMapper.class).getUserByOpenid(openid);
		if (msg == null && user != null) {
			return user.getId();
		}
		else 
			return null;
   }
}
