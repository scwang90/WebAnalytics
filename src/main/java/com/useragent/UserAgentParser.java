package com.useragent;

import com.useragent.target.*;

/**
 * UserAgent 解析器
 */
public class UserAgentParser {

	public UserAgent parse(String useragent)
	{
		UserAgent userAgent = new UserAgent();
		userAgent.setBrand(Brand.parser(useragent));
		userAgent.setBrowser(Browser.parser(useragent));
		userAgent.setNetType(Nettype.parser(useragent));
		userAgent.setPlatform(Platform.parser(useragent));
		userAgent.setApplication(Application.parser(useragent));
		userAgent.setOperateSystem(OperateSystem.parser(useragent));
		userAgent.setBrowserEngine(BrowserEngine.parser(useragent));
		return userAgent;
	}
}
