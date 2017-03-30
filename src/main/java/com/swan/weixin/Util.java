package com.swan.weixin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.protocol.Protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koron.util.Config;
import com.koron.util.Tools;
import com.koron.web.bean.FileBean;
import com.swan.weixin.bean.MediaBean;
import com.swan.weixin.bean.QRCodeBean;
import com.swan.weixin.bean.RequestUserMessageBean;
import com.swan.weixin.bean.ServiceTextMessageBean;
import com.swan.weixin.bean.oauth.OauthBean;
import com.swan.weixin.bean.oauth.OauthUserInfoBean;

public class Util {
	
//	private static final String appId = ConfigBean.getAppid();	
//	private static final String appSecret = ConfigBean.getAppsecret();
	
	private static final String appId =ConfigBean.getAppid();	
	private static final String appSecret = ConfigBean.getAppsecret();
	
	//private static final String appId ="wxe903a0fa1d039ba0";	
	//private static final String appSecret ="246bc3ebda85f0cc63b14a70ca28e08d";
	
	private static long tokenStamp = 0;
	private static String accessToken = null;
	/**
	 * 获取access token
	 * 
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	private static String getAccessToken(String appId, String appSecret) {
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
		String ret =  get(tokenUrl);
		HashMap<String, Object> map = extractedMap(ret);
		if (map.get("errcode") == null || map.get("errcode").equals("0")) {
			accessToken = String.valueOf(map.get("access_token"));
			tokenStamp = System.currentTimeMillis();
			return String.valueOf(map.get("access_token"));
		} else {
//			logger.error("获取token出错，错误代码" + map.get("errcode"));
			return String.valueOf(map.get("errcode"));
		}
	}
	/**
	 * 获取accessToken,token每100分钟刷新一次
	 * @return
	 */
	public static String getAccessToken() {
		return getAccessToken(false);
	}
	/**
	 * 强制刷accesstoken
	 * 如果force为true,则直接取得accesstoken，否则，每１００分钟刷新一次
	 * @param force 是否强制刷新token
	 * @return
	 */
	public static String getAccessToken(boolean force) {
		if(force)
			return getAccessToken(appId,appSecret);
		else if(accessToken == null || tokenStamp < System.currentTimeMillis()-100*60*1000)
			return getAccessToken(appId,appSecret);
		return accessToken;
	}
	
	public static String requestUserMessage(RequestUserMessageBean bean)
	{
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?";
		url += "access_token="+bean.getAccess_token();
		url += "&openid="+bean.getOpenid();
		url += "&lang="+bean.getLang();
		return get(url);
	}
	
	public static String createMenu(MenuButton bean) {
		Gson json = new GsonBuilder().disableHtmlEscaping().create();	
		return createMenu(json.toJson(bean));
	}
	public static String createMenu(String menuStr) {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + getAccessToken();
		String ret = post(url, menuStr);
		System.out.println(ret);
		HashMap<String,Object> map = extractedMap(ret);
		try {
			return (int)Double.parseDouble(map.get("errcode").toString())+"";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	public static String deleteMenu() {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + getAccessToken();
		String ret = post(url, "");
		HashMap<String,Object> map = extractedMap(ret);
		try {
			return String.valueOf(map.get("errcode"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	/**
	 * 发送客服消息
	 * @param c
	 * @return
	 */
	public static String sendServiceMessage(Object c ){
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + getAccessToken();
			String ret = post(url, new GsonBuilder().disableHtmlEscaping().create().toJson(c));
			HashMap<String,Object> map = extractedMap(ret);
			return String.valueOf(map.get("errcode"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	/**
	 * 发送客服文本消息
	 * @param openid    
	 * @param content
	 * @return
	 */
	public static String sendServiceTextMessage(String openid,String content){
		ServiceTextMessageBean bean =new ServiceTextMessageBean();
		bean.setTouser(openid);
		bean.setContent(content);
		System.out.println(content+"-----------------");
		return WeixinMsgHandler.process(bean);
	}

	private static String get(String url) {
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		GetMethod get = new GetMethod(url);
		String respStr = "";
		try {
			get.getParams().setContentCharset("utf-8");
			client.executeMethod(get);
			respStr = get.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		get.releaseConnection();
		return respStr;
	}
	/**
	 * 生成二维码
	 * @param bean
	 * @return
	 */
	public static String createQRCode(QRCodeBean bean)
	{
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+getAccessToken();
		String ret = post(url,bean.getQueryString());
		QRCodeBean retBean=	new Gson().fromJson(ret, QRCodeBean.class);
		System.out.println(retBean);
		return retBean.getTicket();
	}
	
	/**
	 * 以post方式提交请求到https链接
	 * @param url 链接地址
	 * @param postStr post的内容
	 * @return
	 */
	public static String post(String url, String postStr) {
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod post = new PostMethod(url);
		String respStr = "";
		try {
			post.setRequestEntity(new ByteArrayRequestEntity(postStr.getBytes("utf-8")));
			post.getParams().setContentCharset("utf-8");
			client.executeMethod(post);
			respStr = post.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.releaseConnection();
		return respStr;
	}

	@SuppressWarnings("unchecked")
	private static HashMap<String, Object> extractedMap(String respStr) {
		return new Gson().fromJson(respStr, HashMap.class);
	}
	
	/**
	 * 根据code获取opendid（oauth2.0认证）
	 * @param bean
	 * @return
	 */
	public static String getOpenidByoauth(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appId + "&secret=" + appSecret + "&code="
				+ code + "&grant_type=authorization_code";
		String resp=get(url);
		OauthBean oauth = new Gson().fromJson(resp, OauthBean.class);
		if (oauth != null && oauth.getOpenid() != null) {
			return oauth.getOpenid();
		}
		return "";
	}
	
	/**
	 * 根据code获取opendid（oauth2.0认证）
	 * @param bean
	 * @return
	 */
	public static OauthBean getOauthByCode(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appId + "&secret=" + appSecret + "&code="
				+ code + "&grant_type=authorization_code";
		String resp=get(url);
		return new Gson().fromJson(resp, OauthBean.class);
	}
	
	
	/**
	 * 获取oauth2.0授权跳转地址
	 * 
	 * @param url
	 * @return
	 */
	public static String getOauthURL(String url, String state) {
		if (state == null)
			state = "";
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appId + "&redirect_uri=" + url + "&response_type=code&scope="
				+ "snsapi_base" + "&state=" + state + "#wechat_redirect";
	}
	
	/**
	 * 使用网页授权接口调用凭证获取用户基本信息
	 * @param openid
	 * @return
	 */
	public static OauthUserInfoBean getUserInfo(String access_token, String openid){
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+ access_token+"&openid="+openid+"&lang=zh_CN";
		String result = get(url);
		if (result.contains("errcode"))
			return null;
		else
			return new Gson().fromJson(result, OauthUserInfoBean.class);
	}
	
	
	/**
	 * 上传一个媒体文件，得到media_id
	 * @param qrcode
	 * @param file
	 * @param type
	 * @return
	 */
	public static MediaBean getMediaid(File file,String type){
		String url="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+getAccessToken()+"&type="+type;
		String result=post(url, file);
		return new Gson().fromJson(result, MediaBean.class);
	}
	
	/**
	 * 以post方式提交文件到https链接
	 * @param url 链接地址
	 * @param file post的文件
	 * @return
	 */
	public static String post(String url, File file) {
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod post = new PostMethod(url);
		String respStr = "";
		try {
			FilePart fp = new FilePart("media", file); 
			Part[] parts = { fp };  
			MultipartRequestEntity mre = new MultipartRequestEntity(parts, post.getParams());  
			post.setRequestEntity(mre);    
			post.getParams().setContentCharset("utf-8");
			client.executeMethod(post);
			respStr = post.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.releaseConnection();
		return respStr;
	}
	
	
	
	/**
	 * get方式获取文件流 并保存
	 * 
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static FileBean getFile(String url, Integer fileType) {
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https",new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		GetMethod get = new GetMethod(url);
		FileBean fileBean = null;
		try {
			get.getParams().setContentCharset("utf-8");
			client.executeMethod(get);
			String respStr = get.getResponseBodyAsString();
			if (respStr.startsWith("{\"errcode\""))
				return null;
			InputStream ins = get.getResponseBodyAsStream();
			File root = new File(Config.getUploadpath());
			Calendar now = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String pathPrefix = (String)Tools.getEnumValue("tblfile.typePath", fileType);
			pathPrefix = (pathPrefix == null ? "" :pathPrefix) + now.get(Calendar.YEAR) + now.get(Calendar.MONTH);
			root = new File(root, pathPrefix);
			if (root.exists() && root.isFile()) {
				root.delete();
			}
			if (!root.exists()) {
				root.mkdirs();
			}
			Random random = new Random();
			String filename = df.format(now.getTime()) + random.nextInt(100);
			root = new File(root, filename + ".jpg");
			fileBean = new FileBean();
			fileBean.setMimetype("image/jpeg");
			fileBean.setUrl(pathPrefix + "/" + filename + ".jpg");
			OutputStream os = new FileOutputStream(root);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = ins.read(buffer, 0, 1024)) != -1)
				os.write(buffer, 0, bytesRead);
			os.close();
			ins.close();
		} catch (HttpException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
		}
		get.releaseConnection();
		return fileBean;
	}

	/**
	 * 根据media获取文件
	 * @param media_id
	 * @return
	 */
	public static FileBean getFileByMedia_id(String media_id, Integer fileType){
		return getFile("https://api.weixin.qq.com/cgi-bin/media/get?access_token="+getAccessToken()+"&media_id="+media_id, fileType);
	}
}
