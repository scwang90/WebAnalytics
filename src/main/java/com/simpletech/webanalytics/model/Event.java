package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;
import com.simpletech.webanalytics.model.base.ModelBase;

/**
 * 数据库表t_event
 * @author 树朾
 * @date 2015-12-11 18:11:55 中国标准时间
 */
@Table("t_event")
public class Event extends ModelBase{

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
	 * 子站ID
	 */
	private String idsubsite;
	/**
	 * 访问者ID
	 */
	private String idvisitor;
	/**
	 * 本地时间
	 */
	@Column("local_time")
	private java.util.Date localTime;
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
	 * 事件分类
	 */
	private String category;
	/**
	 * 动作
	 */
	private String action;
	/**
	 * 事件名称
	 */
	private String name;
	/**
	 * 值
	 */
	private Double value;

	public Event() {
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
	
	public String getIdsubsite(){
		return this.idsubsite;
	}

	public void setIdsubsite(String idsubsite) {
		this.idsubsite = idsubsite;
	}
	
	public String getIdvisitor(){
		return this.idvisitor;
	}

	public void setIdvisitor(String idvisitor) {
		this.idvisitor = idvisitor;
	}
	
	public java.util.Date getLocalTime(){
		return this.localTime;
	}

	public void setLocalTime(java.util.Date localTime) {
		this.localTime = localTime;
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
	
	public String getCategory(){
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getAction(){
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getValue(){
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
}