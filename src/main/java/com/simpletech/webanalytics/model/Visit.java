package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;

import java.util.Date;

/**
 * 数据库表t_visit
 * @author 树朾
 * @date 2015-09-23 18:27:09 中国标准时间
 */
@Table("t_visit")
public class Visit{

	/**
	 * ID主键
	 */
	@Id
	private String id;
	/**
	 * 网站ID
	 */
	private String idsite;
	/**
	 * 创建时间
	 */
	@Column("create_time")
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@Column("update_time")
	private java.util.Date updateTime;
	/**
	 * 是否老用户
	 */
	private Boolean olduser;
	/**
	 * 访问者ID
	 */
	private String idvisitor;
	/**
	 * 访问服务器时间
	 */
	@Column("visit_servertime")
	private java.util.Date visitServertime;
	/**
	 * 访问者本地时间
	 */
	@Column("visit_localtime")
	private java.util.Date visitLocaltime;
	/**
	 * 访问者停留时间
	 */
	@Column("visit_totaltime")
	private Integer visitTotaltime;
	/**
	 * 当前urlID
	 */
	private String idurl;
	/**
	 * 页面标题ID
	 */
	private String idtitle;
	/**
	 * 进入页url ID
	 */
	@Column("idurl_entry")
	private String idurlEntry;
	/**
	 * 进入页标题ID
	 */
	@Column("idtitle_entry")
	private String idtitleEntry;
	/**
	 * 退出页链接ID
	 */
	@Column("idurl_exit")
	private String idurlExit;
	/**
	 * 退出页标题ID
	 */
	@Column("idtitle_exit")
	private String idtitleExit;
	/**
	 * 上一页面url
	 */
	@Column("url_referer")
	private String urlReferer;
	/**
	 * 用户代理
	 */
	private String useragent;
	/**
	 * 操作系统
	 */
	@Column("operate_system")
	private String operateSystem;
	/**
	 * 操作系统版本
	 */
	@Column("operate_version")
	private String operateVersion;
	/**
	 * 浏览器名称
	 */
	@Column("browser_name")
	private String browserName;
	/**
	 * 浏览器版本
	 */
	@Column("browser_version")
	private String browserVersion;
	/**
	 * 打开页面的App
	 */
	@Column("app_name")
	private String appName;
	/**
	 * 屏幕分辨率
	 */
	@Column("screen_resolution")
	private String screenResolution;
	/**
	 * 颜色深度
	 */
	@Column("screen_depth")
	private String screenDepth;
	/**
	 * 外网IP地址
	 */
	@Column("location_ip")
	private String locationIp;
	/**
	 * 浏览器语言
	 */
	@Column("location_lang")
	private String locationLang;
	/**
	 * 国家
	 */
	@Column("location_country")
	private String locationCountry;
	/**
	 * 区域
	 */
	@Column("location_region")
	private String locationRegion;
	/**
	 * 城市
	 */
	@Column("location_city")
	private String locationCity;
	/**
	 * 终端类型（PC or mobile）
	 */
	@Column("end_type")
	private String endType;
	/**
	 * 网络类型（移动数据，WIFI）
	 */
	@Column("net_type")
	private String netType;
	/**
	 * 这次访问的PV总量
	 */
	@Column("count_visits")
	private Integer countVisits;
	/**
	 * 这次访问的事件总数
	 */
	@Column("count_events")
	private Integer countEvents;

	public Visit() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdsite(){
		return this.idsite;
	}

	public void setIdsite(String idsite) {
		this.idsite = idsite;
	}
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Boolean getOlduser(){
		return this.olduser;
	}

	public void setOlduser(Boolean olduser) {
		this.olduser = olduser;
	}

	public String getIdvisitor() {
		return idvisitor;
	}

	public void setIdvisitor(String idvisitor) {
		this.idvisitor = idvisitor;
	}

	public java.util.Date getVisitServertime(){
		return this.visitServertime;
	}

	public void setVisitServertime(java.util.Date visitServertime) {
		this.visitServertime = visitServertime;
	}
	
	public java.util.Date getVisitLocaltime(){
		return this.visitLocaltime;
	}

	public void setVisitLocaltime(java.util.Date visitLocaltime) {
		this.visitLocaltime = visitLocaltime;
	}
	
	public Integer getVisitTotaltime(){
		return this.visitTotaltime;
	}

	public void setVisitTotaltime(Integer visitTotaltime) {
		this.visitTotaltime = visitTotaltime;
	}
	
	public String getIdurl(){
		return this.idurl;
	}

	public void setIdurl(String idurl) {
		this.idurl = idurl;
	}
	
	public String getIdtitle(){
		return this.idtitle;
	}

	public void setIdtitle(String idtitle) {
		this.idtitle = idtitle;
	}
	
	public String getIdurlEntry(){
		return this.idurlEntry;
	}

	public void setIdurlEntry(String idurlEntry) {
		this.idurlEntry = idurlEntry;
	}
	
	public String getIdtitleEntry(){
		return this.idtitleEntry;
	}

	public void setIdtitleEntry(String idtitleEntry) {
		this.idtitleEntry = idtitleEntry;
	}
	
	public String getIdurlExit(){
		return this.idurlExit;
	}

	public void setIdurlExit(String idurlExit) {
		this.idurlExit = idurlExit;
	}
	
	public String getIdtitleExit(){
		return this.idtitleExit;
	}

	public void setIdtitleExit(String idtitleExit) {
		this.idtitleExit = idtitleExit;
	}
	
	public String getUrlReferer(){
		return this.urlReferer;
	}

	public void setUrlReferer(String urlReferer) {
		this.urlReferer = urlReferer;
	}
	
	public String getUseragent(){
		return this.useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	
	public String getOperateSystem(){
		return this.operateSystem;
	}

	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}
	
	public String getOperateVersion(){
		return this.operateVersion;
	}

	public void setOperateVersion(String operateVersion) {
		this.operateVersion = operateVersion;
	}
	
	public String getBrowserName(){
		return this.browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	
	public String getBrowserVersion(){
		return this.browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	
	public String getAppName(){
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getScreenResolution(){
		return this.screenResolution;
	}

	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}
	
	public String getScreenDepth(){
		return this.screenDepth;
	}

	public void setScreenDepth(String screenDepth) {
		this.screenDepth = screenDepth;
	}
	
	public String getLocationIp(){
		return this.locationIp;
	}

	public void setLocationIp(String locationIp) {
		this.locationIp = locationIp;
	}
	
	public String getLocationLang(){
		return this.locationLang;
	}

	public void setLocationLang(String locationLang) {
		this.locationLang = locationLang;
	}
	
	public String getLocationCountry(){
		return this.locationCountry;
	}

	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}
	
	public String getLocationRegion(){
		return this.locationRegion;
	}

	public void setLocationRegion(String locationRegion) {
		this.locationRegion = locationRegion;
	}
	
	public String getLocationCity(){
		return this.locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	
	public String getEndType(){
		return this.endType;
	}

	public void setEndType(String endType) {
		this.endType = endType;
	}
	
	public String getNetType(){
		return this.netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}
	
	public Integer getCountVisits(){
		return this.countVisits;
	}

	public void setCountVisits(Integer countVisits) {
		this.countVisits = countVisits;
	}
	
	public Integer getCountEvents(){
		return this.countEvents;
	}

	public void setCountEvents(Integer countEvents) {
		this.countEvents = countEvents;
	}

}