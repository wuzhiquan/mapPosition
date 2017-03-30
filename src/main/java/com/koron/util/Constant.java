package com.koron.util;

import java.util.HashMap;

import org.koron.ebs.util.field.EnumElement;

import com.koron.web.bean.DefineFieldBean;


public class Constant {
	/**
	 * 登录用户在SESSION里的储存KEY
	 */
	public static final String USER = "_user";
	/**
	 * 操作成功
	 */
	public static final int MESSAGE_INT_SUCCESS = 0;
	/**
	 * 操作错误
	 */
	public static final int MESSAGE_INT_ERROR = -1;
	/**
	 * 未登录
	 */
	public static final int MESSAGE_INT_NOLOGIN = 10000;
	/**
	 * 无操作权限
	 */
	public static final int MESSAGE_INT_NOMODULE = 10001;
	/**
	 * 无范围权限
	 */
	public static final int MESSAGE_INT_NOFIELD = 10002;
	/**
	 * 数据库操作失败
	 */
	public static final int MESSAGE_DBFAIL = 11000;
	/**
	 * 有下级数据不能删除
	 */
	public static final int MESSAGE_INT_NOEMPTY = 11001;
	
	/**
	 * 已经处理不能再处理
	 */
	public static final int MESSAGE_INT_REPEATDEAL = 12000;
	
	/**
	 * 数据校验错误
	 */
	public static final int MESSAGE_INT_DATAVALIDATEERROR = 12002;
	
	/**
	 * 不能重复的字段重复
	 */
	public static final int MESSAGE_PARAMExist=15001;
	
	/**
	 * 自定义表单增加辅助单位功能(在前台要自动加上辅助功能的脚本)
	 */
	public static final int HAS_ASSIST = 1000;
	
	/**
	 * 自定义层别缓存
	 */
	public static final HashMap<String, Integer> layerCache = new HashMap<>();
	/**
	 * 自定义字段缓存
	 */
	public final static HashMap<String, DefineFieldBean> fieldCache = new HashMap<>();
	/**
	 * 枚举缓存
	 */
	public final static HashMap<String, EnumElement<Object>> enumCache = new HashMap<>();
	
}
