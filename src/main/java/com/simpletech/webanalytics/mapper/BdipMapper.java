package com.simpletech.webanalytics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.simpletech.webanalytics.model.Bdip;
import com.simpletech.webanalytics.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;


/**
 * 数据库表t_bdip的mapper接口
 * @author 树朾
 * @date 2015-12-11 18:11:54 中国标准时间
 */
public interface BdipMapper extends MybatisMultiDao<Bdip>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO t_bdip ( id , create_time , update_time , bd_ip , bd_country , bd_region , bd_city , bd_isp ) VALUES ( #{id} , #{createTime} , #{updateTime} , #{bdIp} , #{bdCountry} , #{bdRegion} , #{bdCity} , #{bdIsp} )")
	int insert(Bdip model);
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_bdip WHERE id=#{id}")
	int delete(@Param("id") Object id);
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE t_bdip SET id=#{id} , create_time=#{createTime} , update_time=#{updateTime} , bd_ip=#{bdIp} , bd_country=#{bdCountry} , bd_region=#{bdRegion} , bd_city=#{bdCity} , bd_isp=#{bdIsp} WHERE id=#{id} ")
	int update(Bdip model);
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_bdip")
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT id , create_time createTime , update_time updateTime , bd_ip bdIp , bd_country bdCountry , bd_region bdRegion , bd_city bdCity , bd_isp bdIsp FROM t_bdip WHERE id=#{id}")
	Bdip findById(@Param("id") Object id);
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT id , create_time createTime , update_time updateTime , bd_ip bdIp , bd_country bdCountry , bd_region bdRegion , bd_city bdCity , bd_isp bdIsp FROM t_bdip ${order}")
	List<Bdip> findAll(@Param("order") String order);
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT id , create_time createTime , update_time updateTime , bd_ip bdIp , bd_country bdCountry , bd_region bdRegion , bd_city bdCity , bd_isp bdIsp FROM t_bdip ${order} LIMIT ${start},${limit}")
	List<Bdip> findByPage(@Param("order") String order,@Param("limit") int limit,@Param("start") int start);
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_bdip ${where}")
	int deleteWhere(@Param("where") String where);
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_bdip WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value);
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_bdip ${where}")
	int countWhere(@Param("where") String where);
	/**
	 * 根据属性统计
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM WHERE ${propertyName}=#{value}")
	int countByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value);
	/**
	 * 选择性查询
	 * @param where SQL条件语句
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , create_time createTime , update_time updateTime , bd_ip bdIp , bd_country bdCountry , bd_region bdRegion , bd_city bdCity , bd_isp bdIsp FROM t_bdip ${where} ${order}")
	List<Bdip> findWhere(@Param("order") String order,@Param("where") String where);
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , create_time createTime , update_time updateTime , bd_ip bdIp , bd_country bdCountry , bd_region bdRegion , bd_city bdCity , bd_isp bdIsp FROM t_bdip ${where} ${order} LIMIT ${start},${limit}")
	List<Bdip> findWhereByPage(@Param("order") String order,@Param("where") String where,@Param("limit") int limit,@Param("start") int start);
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT id , create_time createTime , update_time updateTime , bd_ip bdIp , bd_country bdCountry , bd_region bdRegion , bd_city bdCity , bd_isp bdIsp FROM t_bdip WHERE ${propertyName}=#{value} ${order}")
	List<Bdip> findByPropertyName(@Param("order") String order,@Param("propertyName") String propertyName,@Param("value") Object value);
}