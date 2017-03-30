package com.koron.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.web.multipart.MultipartFile;

import com.koron.util.Config;
import com.koron.util.Tools;
import com.koron.web.bean.FileBean;
import com.koron.web.mapper.FileMapper;
/**
 * 文件处理类
 * @author Administrator
 */
public class FileDeal {
	private final static String UPLOADPATH=Config.getUploadpath();
	public final static String GETBYIDURL=Config.getContentpath()+"/file/get.htm?id=";
	public final static String GETBYKEYURL=Config.getContentpath()+"/file/get.htm?key=";
	/**
	 * 根据id获取文件
	 * @param id
	 * @param response
	 * @return
	 */
	public static boolean showFile(Integer id,HttpServletResponse response) {
		SessionFactory factory = new SessionFactory();
		try {
			FileMapper mapper = factory.getMapper(FileMapper.class);
			FileBean bean=mapper.getFileById(id);
			if(bean!=null){
				mapper.addNum(bean.getId());
			}
			return showFile(bean, response);
		} catch (Exception e) {
			
		}finally{
			factory.close();
		}
		return false;
	}
	/**
	 * 根据key获取文件
	 * @param key
	 * @param response
	 * @return
	 */
	public static boolean showFile(String key,HttpServletResponse response) {
		SessionFactory factory = new SessionFactory();
		try {
			FileMapper mapper = factory.getMapper(FileMapper.class);
			FileBean bean=mapper.getFileByKey(key);
			if(bean!=null){
				mapper.addNum(bean.getId());
			}
			return showFile(bean, response);
		} catch (Exception e) {
			
		}finally{
			factory.close();
		}
		return false;
	}
	
	/**
	 * 读取文件
	 * @param bean
	 * @param response
	 * @return
	 */
	public static boolean showFile(FileBean bean, HttpServletResponse response) {
		if(bean==null){
			return false;
		}
		File root = new File(UPLOADPATH);
		root = new File(root, bean.getUrl());
		response.setContentType(bean.getMimetype());
		if (root.exists()) {
			try {
				OutputStream ous = response.getOutputStream();
				FileInputStream ins = new FileInputStream(root);
				int tmp = -1;
				while((tmp=ins.read())!=-1)
				{
					ous.write(tmp);
				}
				ins.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * 读取多个文件文件并返回zip
	 * @param list
	 * @param response
	 * @return
	 */
	public static boolean showFile(List<FileBean> list, HttpServletResponse response) {
		if(list==null||list.size()==0){
			return false;
		};
		byte[] buf = new byte[1024];  
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment ; filename="+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".zip");
		try {
			OutputStream ous = response.getOutputStream();
			ZipOutputStream out = new ZipOutputStream(ous);
			for (FileBean bean : list) {
				File file = new File(UPLOADPATH);
				file = new File(file, bean.getUrl());
				if (file.exists()) {
					FileInputStream in = new FileInputStream(file);  
	                // Add ZIP entry to output stream.
					String name= bean.getKey()==null?file.getName():bean.getKey()+".jpg";
	                out.putNextEntry(new ZipEntry(name));  
	                // Transfer bytes from the file to the ZIP file  
	                int len;  
	                while ((len = in.read(buf)) > 0) {  
	                    out.write(buf, 0, len);  
	                }  
	                // Complete the entry  
	                out.closeEntry();  
	                in.close();  
				}
			}
			out.close();
			ous.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return false;
	}
	/**
	 * 添加文件返回id
	 * @param file
	 * @param bean
	 * @param pathPrefix
	 * @return
	 */
	public static Integer addFile(MultipartFile file, FileBean bean) {
		File root = new File(UPLOADPATH);
		Calendar now = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		bean.setType(1);
		String pathPrefix = (String)Tools.getEnumValue("tblfile.typePath", bean.getType());
		pathPrefix = pathPrefix == null ? "":pathPrefix + now.get(Calendar.YEAR) + now.get(Calendar.MONTH);
		root = new File(root, pathPrefix);
		if(root.exists() && root.isFile()){
			root.delete();
		}
		if (!root.exists()) {
			root.mkdirs();
		}
		
		String originalFilename = null; 
		String orginalFileKey =null;
		String orgianlFileDescription =null;
		try {
			if(bean.getKey()!=null){
				orginalFileKey = new String(bean.getKey().getBytes("ISO8859-1"),"UTF-8");
				bean.setKey(orginalFileKey);
			}	
			if(bean.getDescription()!=null)
				orgianlFileDescription = new String(bean.getDescription().getBytes("ISO8859-1"),"UTF-8");
				originalFilename = new String(file.getOriginalFilename().getBytes("ISO8859-1"),"UTF-8");
		    if(orgianlFileDescription!=null&&!orgianlFileDescription.trim().isEmpty()){
		    	bean.setDescription(orgianlFileDescription);//使用设置的文件描述
		    }
		    else{
		    	bean.setDescription(originalFilename);//使用文件名作为文件描述
		    }
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		Random random = new Random();
		String filename = df.format(now.getTime())+random.nextInt(100);
		if ((originalFilename != null) && (originalFilename.length() > 0)) {   
            int dot = originalFilename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (originalFilename.length() - 1))) {   
                filename +=originalFilename.substring(dot);   
            }   
        } 
		root = new File(root,filename );
		SessionFactory factory = new SessionFactory();
		try {
			file.transferTo(root);
			bean.setMimetype(file.getContentType());
			bean.setUrl(pathPrefix + "/" + filename);
			FileMapper mapper = factory.getMapper(FileMapper.class);
			if(mapper.addFile(bean)>0){
				return bean.getId();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			factory.close();
		}
		return -1;
	}
	
	
	
}
