package com.koron.web.servlet;

import org.koron.ebs.businessLog.LogInfo;
import org.koron.ebs.module.ModuleService;
import org.koron.ebs.mybatis.ADOSession;
import org.koron.ebs.mybatis.MybatisInfo;

public class InitParam {
	public InitParam(ModuleService service)
	{
		service.registe(new MybatisInfo());
		service.registe(new LogInfo());
		ADOSession session = service.invokeObject("org.koron.mybatis", ADOSession.class);
		session.registeJndiMap("_default", "java:comp/env/jndi/mysql");
	}
}
