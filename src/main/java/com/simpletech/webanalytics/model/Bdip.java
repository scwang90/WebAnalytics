package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;
import com.simpletech.webanalytics.model.base.ModelBase;

/**
 * 数据库表t_bdip
 * @author 树朾
 * @date 2015-12-11 18:11:55 中国标准时间
 */
@Table("t_bdip")
public class Bdip extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String id;
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
	 * 外网IP地址
	 */
	@Column("bd_ip")
	private String bdIp;
	/**
	 * 国家
	 */
	@Column("bd_country")
	private String bdCountry;
	/**
	 * 区域
	 */
	@Column("bd_region")
	private String bdRegion;
	/**
	 * 城市
	 */
	@Column("bd_city")
	private String bdCity;
	/**
	 * 运营商
	 */
	@Column("bd_isp")
	private String bdIsp;

	public Bdip() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getBdIp(){
		return this.bdIp;
	}

	public void setBdIp(String bdIp) {
		this.bdIp = bdIp;
	}
	
	public String getBdCountry(){
		return this.bdCountry;
	}

	public void setBdCountry(String bdCountry) {
		this.bdCountry = bdCountry;
	}
	
	public String getBdRegion(){
		return this.bdRegion;
	}

	public void setBdRegion(String bdRegion) {
		this.bdRegion = bdRegion;
	}
	
	public String getBdCity(){
		return this.bdCity;
	}

	public void setBdCity(String bdCity) {
		this.bdCity = bdCity;
	}
	
	public String getBdIsp(){
		return this.bdIsp;
	}

	public void setBdIsp(String bdIsp) {
		this.bdIsp = bdIsp;
	}
	
}