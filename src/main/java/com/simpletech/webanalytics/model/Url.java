package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;

/**
 * 数据库表t_url
 * @author 树朾
 * @date 2015-09-30 18:37:16 中国标准时间
 */
@Table("t_url")
public class Url{

	/**
	 * ID主键
	 */
	@Id
	private String id;
	/**
	 * 网站ID
	 */
	private Integer idsite;
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
	 * 哈希值
	 */
	private Integer hash;
	/**
	 * url 值
	 */
	private String url;

	public Url() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getIdsite(){
		return this.idsite;
	}

	public void setIdsite(Integer idsite) {
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
	
	public Integer getHash(){
		return this.hash;
	}

	public void setHash(Integer hash) {
		this.hash = hash;
	}
	
	public String getUrl(){
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}