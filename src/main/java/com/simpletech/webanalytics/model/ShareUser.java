package com.simpletech.webanalytics.model;

import com.simpletech.webanalytics.annotations.dbmodel.Column;
import com.simpletech.webanalytics.annotations.dbmodel.Id;
import com.simpletech.webanalytics.annotations.dbmodel.Table;
import com.simpletech.webanalytics.model.base.ModelBase;

/**
 * 数据库表t_share_user
 * @author 树朾
 * @date 2015-12-11 18:11:55 中国标准时间
 */
@Table("t_share_user")
public class ShareUser extends ModelBase{

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
	 * 访问者ID（cookie）
	 */
	private String idvisitor;
	/**
	 * 微信用户openid
	 */
	private String openid;
	/**
	 * 微信用户唯一ID
	 */
	private String unionid;
	/**
	 * 微信用户昵称
	 */
	private String nickname;
	/**
	 * 微信用户头像链接
	 */
	private String headimgurl;
	/**
	 * 微信用户性别
	 */
	private String sex;
	/**
	 * 微信用户省份
	 */
	private String province;
	/**
	 * 微信用户城市
	 */
	private String city;
	/**
	 * 微信用户国家
	 */
	private String country;
	/**
	 * 微信用户特权
	 */
	private String privilege;
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

	public ShareUser() {
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
	
	public String getOpenid(){
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getUnionid(){
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	public String getNickname(){
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getHeadimgurl(){
		return this.headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	public String getSex(){
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getProvince(){
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity(){
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry(){
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPrivilege(){
		return this.privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
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