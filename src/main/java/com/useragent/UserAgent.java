package com.useragent;

import com.useragent.target.*;

public class UserAgent {

	private Brand.Model brand;
	private Browser.Model browser;
	private Nettype.Model netType;
	private Platform.Model platform;
	private Application.Model application;
	private OperateSystem.Model operateSystem;
	private BrowserEngine.Model browserEngine;

	public Brand.Model getBrand() {
		return brand;
	}

	public void setBrand(Brand.Model brand) {
		this.brand = brand;
	}

	public Browser.Model getBrowser() {
		return browser;
	}

	public void setBrowser(Browser.Model browser) {
		this.browser = browser;
	}

	public Nettype.Model getNetType() {
		return netType;
	}

	public void setNetType(Nettype.Model netType) {
		this.netType = netType;
	}

	public Platform.Model getPlatform() {
		return platform;
	}

	public void setPlatform(Platform.Model platform) {
		this.platform = platform;
	}

	public Application.Model getApplication() {
		return application;
	}

	public void setApplication(Application.Model application) {
		this.application = application;
	}

	public OperateSystem.Model getOperateSystem() {
		return operateSystem;
	}

	public void setOperateSystem(OperateSystem.Model operateSystem) {
		this.operateSystem = operateSystem;
	}

	public BrowserEngine.Model getBrowserEngine() {
		return browserEngine;
	}

	public void setBrowserEngine(BrowserEngine.Model browserEngine) {
		this.browserEngine = browserEngine;
	}
}
