package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;

/**
 * 数据库表t_site
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Table("t_site")
public class Site{

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
	 * 网站备注名称
	 */
	private String name;
	/**
	 * 网站域名
	 */
	private String domain;

	public Site() {
		// TODO Auto-generated constructor stub
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
	
	public String getName(){
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDomain(){
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}