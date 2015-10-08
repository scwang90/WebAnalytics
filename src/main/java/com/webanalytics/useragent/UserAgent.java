package com.webanalytics.useragent;

public class UserAgent {

	private Brand brand;
	private Browser browser;
	private Nettype netType;
	private Platform platform;
	private Application application;
	private OperateSystem operateSystem;
	private BrowserEngine browserEngine;

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setNetType(Nettype netType) {
		this.netType = netType;
	}
	public Nettype getNetType() {
		return netType;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Application getApplication() {
		return application;
	}
	public void setOperateSystem(OperateSystem operateSystem) {
		this.operateSystem = operateSystem;
	}
	public OperateSystem getOperateSystem() {
		return operateSystem;
	}
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	public Browser getBrowser() {
		return browser;
	}
	public void setBrowserEngine(BrowserEngine browserEngine) {
		this.browserEngine = browserEngine;
	}
	public BrowserEngine getBrowserEngine() {
		return browserEngine;
	}
}
