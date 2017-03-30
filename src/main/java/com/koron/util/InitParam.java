package com.koron.util;

import org.koron.ebs.module.ModuleService;
import org.koron.ebs.mybatis.ADOSession;
import org.koron.ebs.mybatis.MybatisInfo;

public class InitParam {
	public InitParam(ModuleService service)
	{
		service.registe(new MybatisInfo());
		ADOSession session = service.invokeObject("org.koron.mybatis", ADOSession.class);
		session.registeJndiMap("_default", "java:comp/env/jndi/mysql");
	}
}
