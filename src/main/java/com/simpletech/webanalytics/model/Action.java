package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;

/**
 * 数据库表t_action
 * @author 树朾
 * @date 2015-09-30 18:37:15 中国标准时间
 */
@Table("t_action")
public class Action{

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
	 * 网站ID
	 */
	private Integer idsite;
	/**
	 * 访客用户ID
	 */
	private String idvisitor;
	/**
	 * 关联Visit ID
	 */
	private String idvisit;
	/**
	 * 标题ID
	 */
	private String idtitle;
	/**
	 * urlID
	 */
	private String idurl;
	/**
	 * 服务器时间
	 */
	@Column("server_time")
	private java.util.Date serverTime;
	/**
	 * 页面加载时间
	 */
	@Column("time_spent")
	private Integer timeSpent;

	public Action() {
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
	
	public Integer getIdsite(){
		return this.idsite;
	}

	public void setIdsite(Integer idsite) {
		this.idsite = idsite;
	}
	
	public String getIdvisitor(){
		return this.idvisitor;
	}

	public void setIdvisitor(String idvisitor) {
		this.idvisitor = idvisitor;
	}
	
	public String getIdvisit(){
		return this.idvisit;
	}

	public void setIdvisit(String idvisit) {
		this.idvisit = idvisit;
	}
	
	public String getIdtitle(){
		return this.idtitle;
	}

	public void setIdtitle(String idtitle) {
		this.idtitle = idtitle;
	}
	
	public String getIdurl(){
		return this.idurl;
	}

	public void setIdurl(String idurl) {
		this.idurl = idurl;
	}
	
	public java.util.Date getServerTime(){
		return this.serverTime;
	}

	public void setServerTime(java.util.Date serverTime) {
		this.serverTime = serverTime;
	}
	
	public Integer getTimeSpent(){
		return this.timeSpent;
	}

	public void setTimeSpent(Integer timeSpent) {
		this.timeSpent = timeSpent;
	}
	
}