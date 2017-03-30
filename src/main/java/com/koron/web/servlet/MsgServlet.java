package com.koron.web.servlet;

import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.*;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.swan.weixin.ConfigBean;
import com.swan.weixin.WeixinMsgHandler;
import com.swan.weixin.bean.MessageTypeBean;

@Controller
@RequestMapping("/msg.htm")
public class MsgServlet {
	Logger logger = Logger.getLogger(MsgServlet.class);
	private String token = ConfigBean.getToken();

	/**
	 * 用来验证开发环境
	 * 
	 * @param echoStr
	 * @param writer
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void receiveInject(@RequestParam("echostr") String echoStr, PrintWriter writer) {
		writer.print(echoStr);
	}

	/**
	 * 接收信息
	 * 
	 * @param writer
	 * @param request
	 * @throws DocumentException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void receiveMessage(HttpServletResponse response, HttpServletRequest request, @RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) throws DocumentException {
		System.out.println("----------------------------");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			BufferedReader reader = request.getReader();
			if (checkSignature(signature, timestamp, nonce, token)) {
				StringBuilder sb = new StringBuilder();
				String tmp = null;
				try {
					while ((tmp = reader.readLine()) != null) {
						sb.append(tmp).append("\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				logger.debug(sb.toString());
				Unmarshaller um = JAXBContext.newInstance(MessageTypeBean.class).createUnmarshaller();
				MessageTypeBean bean = (MessageTypeBean) um.unmarshal(new StringReader(sb.toString()));
				String ret = WeixinMsgHandler.process(bean.getBean(sb));
				System.out.println(ret);
				if (ret != null && !ret.equals("")) {
					if (ret.equals("0.0") || ret.equals("0"))
						response.getWriter().print("success");
					else
						response.getWriter().print(ret);
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public boolean checkSignature(String signature, String timestamp, String nonce, String token) {
		String[] strArr = new String[] { nonce, timestamp, token };
		String tmpStr = "";
		Arrays.sort(strArr);
		for (String string : strArr) {
			tmpStr += string;
		}
		tmpStr = this.SHA1Encode(tmpStr);
		if (tmpStr.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}
	}

	private String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	private final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}
	
	/*//测试main方法
	public static void main(String[] args) {
		
		try {
			System.out.println(readStringXmlOut("<xml>"
					+ "<ToUserName><![CDATA[gh_d47e6cc00fa9]]></ToUserName>"
					+ "<FromUserName><![CDATA[oTVjl0xT-Ok-LH6tSLOpN8ITbQ-M]]></FromUserName>"
					+ "<CreateTime>1489568979</CreateTime>"
					+ "<MsgType><![CDATA[text]]></MsgType>"
					+ "<Content><![CDATA[轻微的无群二多]]></Content>"
					+ "<MsgId>6397650050492555592</MsgId>"
					+ "</xml>").get("FromUserName"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 将xml转成map(缺少jar包)
	 * @param xml
	 * @return
	 * @throws DocumentException 
	 */
    /*public static Map<String, String> readStringXmlOut(String xml) throws DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        doc = DocumentHelper.parseText(xml); // 将字符串转为XML
        org.dom4j.Element rootElt = doc.getRootElement(); // 获取根节点
        @SuppressWarnings("unchecked")
        List<org.dom4j.Element> list = rootElt.elements();// 获取根节点下所有节点
        for (org.dom4j.Element element : list) { // 遍历节点
            map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
        }
        
        return map;
    }*/
}
