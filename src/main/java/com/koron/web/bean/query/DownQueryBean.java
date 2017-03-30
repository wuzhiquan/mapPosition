package com.koron.web.bean.query;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.koron.web.bean.IdentityBean;
import com.koron.web.bean.query.BaseQueryBean;


public class DownQueryBean extends BaseQueryBean implements IdentityBean {

	private Integer id;
	
	private String filename;
	
	private String startDate;
	
	private String endDate;
	
	private String description;	
	
	private Integer flag;
	
	private Timestamp createtime;
	
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public void setId(Integer id){
		this.id = id;
	}
	@Override
	public Integer getId(){
		return id;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getStart()
	{
		if(startDate==null||startDate.equals(""))return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return (sdf.parse(startDate).getTime())/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Long getEnd()
	{
		if(endDate==null||endDate.equals(""))return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return (sdf.parse(endDate).getTime()+86400*1000)/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
