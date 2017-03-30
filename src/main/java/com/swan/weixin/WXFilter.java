package com.swan.weixin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.swan.bean.MessageBean;

import com.google.gson.Gson;
import com.koron.util.SwanRequestWrapper;
import com.swan.weixin.bean.oauth.OauthBean;
import com.swan.weixin.bean.oauth.OauthUserInfoBean;

public class WXFilter implements Filter {
//	url正则：需要获取openid
	private static String urlRegxBase = ".*\\.wxbase\\.htm$";
//	url正则：需要获取用户基本信息
	private static String urlRegxInfo = ".*\\.wxinfo\\.htm$";
//	session存放信息的key：存放openid
	private static String  sessionKeyOpenid = "_openid";
//	session存放信息的key：存放userinfo
	private static String  sessionKeyUserinfo = "_wxuserinfo";
//	状态码 ：需要授权
	private static Integer msgcode = 10003;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String str = request.getRequestURI();
		String type =  "";
		//是否需要获取openid
		if(str.matches(urlRegxBase) && request.getSession().getAttribute(sessionKeyOpenid) == null)
			type = "snsapi_base";
		//是否需要获取用户信息
		else if(str.matches(urlRegxInfo) && request.getSession().getAttribute(sessionKeyUserinfo) == null)
		    type = "snsapi_userinfo";
		if(!type.isEmpty()){
			String code = request.getParameter("code");
			HttpServletResponse response = (HttpServletResponse) resp;
			if(code == null){
				String returntype = request.getHeader("returntype");
				if (returntype != null && returntype.equals("ajax/json")) {
					MessageBean<Void> msg = new MessageBean<Void>();
					msg.setCode(msgcode);
					msg.setDescription("请先授权获取openid");
					response.setCharacterEncoding("utf-8");
					response.getWriter().println(new Gson().toJson(msg));
				} else {
					String state = SaveRequest.add(request);
					String url = ConfigBean.getWXServerPrefix() + request.getRequestURI();
					String oauthurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ ConfigBean.getAppid() +
							"&redirect_uri=" + url + "&response_type=code&scope="+ type + "&state=" + state + "#wechat_redirect";
					response.sendRedirect(oauthurl);
				}
			}
			else{
				OauthBean oauth = Util.getOauthByCode(code);
				//使用code换取openid
				String openid = oauth.getOpenid();
				OauthUserInfoBean info = null;
				if(type.equals("snsapi_userinfo"))
					//使用网页授权接口获取用户信息
					info = Util.getUserInfo(oauth.getAccess_token(), oauth.getOpenid());
				SwanRequestWrapper rqwrapper = new SwanRequestWrapper(request);
				if(openid != null && !openid.isEmpty()){
					request.getSession().setAttribute(sessionKeyOpenid, openid);
					request.getSession().setAttribute(sessionKeyUserinfo, info);
					String state = request.getParameter("state");
					if(state != null && !state.isEmpty())//根据state回填数据
						SaveRequest.setSavedRequestData(state, rqwrapper);
				}
				chain.doFilter(rqwrapper, resp);
			}
		}
		else
			chain.doFilter(req, resp);
	}


	@Override
	public void init(FilterConfig config) throws ServletException {
		if(config.getInitParameter("urlRegxBase") != null)
			urlRegxBase = config.getInitParameter("urlRegxBase");
		if(config.getInitParameter("urlRegxInfo") != null)
			urlRegxInfo = config.getInitParameter("urlRegxInfo");
		if(config.getInitParameter("sessionKeyOpenid") != null)
			sessionKeyOpenid = config.getInitParameter("sessionKeyOpenid");
		if(config.getInitParameter("sessionKeyUserinfo") != null)
			sessionKeyUserinfo = config.getInitParameter("sessionKeyUserinfo");
		if(config.getInitParameter("msgcode") != null)
			msgcode = Integer.valueOf(config.getInitParameter("msgcode"));
	}
	
	
	
}
