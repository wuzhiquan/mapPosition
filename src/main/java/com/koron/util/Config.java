package com.koron.util;

public class Config {
	
	/**
	 * 磁盘文件上存路径
	 */
	private static String uploadpath;
	/**
	 * 服务器访问地址
	 */
	private static String contentpath;
	public static String getUploadpath() {
		return uploadpath;
	}
	public static void setUploadpath(String uploadpath) {
		Config.uploadpath = uploadpath;
	}
	public static String getContentpath() {
		return contentpath;
	}
	public static void setContentpath(String contentpath) {
		Config.contentpath = contentpath;
	}
    	
}