package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;
import com.simpletech.webanalytics.model.base.ModelBase;

/**
 * 数据库表t_ip_location
 * @author 树朾
 * @date 2015-10-16 10:38:40 中国标准时间
 */
@Table("t_ip_location")
public class IpLocation extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String id;
	/**
	 * 网站ID
	 */
	private String ip;
	/**
	 * 淘宝国家
	 */
	@Column("tb_country")
	private String tbCountry;
	/**
	 * 淘宝省份
	 */
	@Column("tb_province")
	private String tbProvince;
	/**
	 * 淘宝城市
	 */
	@Column("tb_city")
	private String tbCity;
	/**
	 * 淘宝地区
	 */
	@Column("tb_districk")
	private String tbDistrick;
	/**
	 * 淘宝运营商
	 */
	@Column("tb_isp")
	private String tbIsp;
	/**
	 * 百度国家
	 */
	@Column("bd_country")
	private String bdCountry;
	/**
	 * 百度省份
	 */
	@Column("bd_province")
	private String bdProvince;
	/**
	 * 百度城市
	 */
	@Column("bd_city")
	private String bdCity;
	/**
	 * 数据库列bd_districk
	 */
	@Column("bd_districk")
	private String bdDistrick;
	/**
	 * 百度运营商
	 */
	@Column("bd_isp")
	private String bdIsp;
	/**
	 * 创建时间
	 */
	@Column("create_time")
	private java.util.Date createTime;
	/**
	 * 腾讯地区
	 */
	@Column("tx_districk")
	private String txDistrick;
	/**
	 * 腾讯城市
	 */
	@Column("tx_city")
	private String txCity;
	/**
	 * 腾讯国家
	 */
	@Column("tx_country")
	private String txCountry;
	/**
	 * 腾讯运营商
	 */
	@Column("tx_isp")
	private String txIsp;
	/**
	 * 腾讯省份
	 */
	@Column("tx_province")
	private String txProvince;
	/**
	 * 更新时间
	 */
	@Column("update_time")
	private java.util.Date updateTime;

	public IpLocation() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIp(){
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getTbCountry(){
		return this.tbCountry;
	}

	public void setTbCountry(String tbCountry) {
		this.tbCountry = tbCountry;
	}
	
	public String getTbProvince(){
		return this.tbProvince;
	}

	public void setTbProvince(String tbProvince) {
		this.tbProvince = tbProvince;
	}
	
	public String getTbCity(){
		return this.tbCity;
	}

	public void setTbCity(String tbCity) {
		this.tbCity = tbCity;
	}
	
	public String getTbDistrick(){
		return this.tbDistrick;
	}

	public void setTbDistrick(String tbDistrick) {
		this.tbDistrick = tbDistrick;
	}
	
	public String getTbIsp(){
		return this.tbIsp;
	}

	public void setTbIsp(String tbIsp) {
		this.tbIsp = tbIsp;
	}
	
	public String getBdCountry(){
		return this.bdCountry;
	}

	public void setBdCountry(String bdCountry) {
		this.bdCountry = bdCountry;
	}
	
	public String getBdProvince(){
		return this.bdProvince;
	}

	public void setBdProvince(String bdProvince) {
		this.bdProvince = bdProvince;
	}
	
	public String getBdCity(){
		return this.bdCity;
	}

	public void setBdCity(String bdCity) {
		this.bdCity = bdCity;
	}
	
	public String getBdDistrick(){
		return this.bdDistrick;
	}

	public void setBdDistrick(String bdDistrick) {
		this.bdDistrick = bdDistrick;
	}
	
	public String getBdIsp(){
		return this.bdIsp;
	}

	public void setBdIsp(String bdIsp) {
		this.bdIsp = bdIsp;
	}
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public String getTxDistrick(){
		return this.txDistrick;
	}

	public void setTxDistrick(String txDistrick) {
		this.txDistrick = txDistrick;
	}
	
	public String getTxCity(){
		return this.txCity;
	}

	public void setTxCity(String txCity) {
		this.txCity = txCity;
	}
	
	public String getTxCountry(){
		return this.txCountry;
	}

	public void setTxCountry(String txCountry) {
		this.txCountry = txCountry;
	}
	
	public String getTxIsp(){
		return this.txIsp;
	}

	public void setTxIsp(String txIsp) {
		this.txIsp = txIsp;
	}
	
	public String getTxProvince(){
		return this.txProvince;
	}

	public void setTxProvince(String txProvince) {
		this.txProvince = txProvince;
	}
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}