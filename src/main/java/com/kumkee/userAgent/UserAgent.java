package com.kumkee.userAgent;

public class UserAgent {

	private boolean mobile; // false
	private String browseer; // Firefox
	private String platform; // Windows
	private String os; // Windows 7
	private String engine; 
	private String version; // 5 
	private String engineVersion;
	private String model;
	private Brand brand;
	private Nettype netType;
	private Device device;
	private Application application;
	private OperateSystem operateSystem;
	private Browser browser;

	public boolean isMobile()
	{
		return mobile;
	}
	public void setMobile(boolean mobile)
	{
		this.mobile = mobile;
	}
	public String getBrowseer()
	{
		return browseer;
	}
	public void setBrowseer(String browser)
	{
		this.browseer = browser;
	}
	public String getPlatform()
	{
		return platform;
	}
	public void setPlatform(String platform)
	{
		this.platform = platform;
	}
	public String getOs()
	{
		return os;
	}
	public void setOs(String os)
	{
		this.os = os;
	}
	public String getEngine()
	{
		return engine;
	}
	public void setEngine(String engine)
	{
		this.engine = engine;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public String getEngineVersion()
	{
		return engineVersion;
	}
	public void setEngineVersion(String engineVersion)
	{
		this.engineVersion = engineVersion;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModel() {
		return model;
	}
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
	public void setDevice(Device device) {
		this.device = device;
	}
	public Device getDevice() {
		return device;
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
	public void setBrowseer(Browser browseer) {
		this.browser = browseer;
	}
	public Browser getBrowser() {
		return browser;
	}
}
