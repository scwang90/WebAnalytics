package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;
import com.simpletech.webanalytics.model.base.ModelBase;

/**
 * 数据库表t_share_line_point
 * @author 树朾
 * @date 2015-12-11 18:11:55 中国标准时间
 */
@Table("t_share_line_point")
public class ShareLinePoint extends ModelBase{

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
	 * 页面ID
	 */
	private String idurl;
	/**
	 * 标题ID
	 */
	private String idtitle;
	/**
	 * 访问者ID
	 */
	private String idvisitor;
	/**
	 * 上个一访问者ID
	 */
	private String idrefervisitor;
	/**
	 * 是否起始分享
	 */
	@Column("is_start_point")
	private Boolean isStartPoint;
	/**
	 * 分享到
	 */
	@Column("share_to")
	private String shareTo;
	/**
	 * 点击量
	 */
	@Column("count_pv")
	private Integer countPv;
	/**
	 * 分享时间差
	 */
	@Column("share_span")
	private Long shareSpan;
	/**
	 * 分享时间
	 */
	@Column("share_time")
	private java.util.Date shareTime;
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

	public ShareLinePoint() {
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
	
	public String getIdvisitor(){
		return this.idvisitor;
	}

	public void setIdvisitor(String idvisitor) {
		this.idvisitor = idvisitor;
	}
	
	public String getIdrefervisitor(){
		return this.idrefervisitor;
	}

	public void setIdrefervisitor(String idrefervisitor) {
		this.idrefervisitor = idrefervisitor;
	}
	
	public Boolean getIsStartPoint(){
		return this.isStartPoint;
	}

	public void setIsStartPoint(Boolean isStartPoint) {
		this.isStartPoint = isStartPoint;
	}
	
	public String getShareTo(){
		return this.shareTo;
	}

	public void setShareTo(String shareTo) {
		this.shareTo = shareTo;
	}
	
	public Integer getCountPv(){
		return this.countPv;
	}

	public void setCountPv(Integer countPv) {
		this.countPv = countPv;
	}
	
	public Long getShareSpan(){
		return this.shareSpan;
	}

	public void setShareSpan(Long shareSpan) {
		this.shareSpan = shareSpan;
	}
	
	public java.util.Date getShareTime(){
		return this.shareTime;
	}

	public void setShareTime(java.util.Date shareTime) {
		this.shareTime = shareTime;
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
	
}