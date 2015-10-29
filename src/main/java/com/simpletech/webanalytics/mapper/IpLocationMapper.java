package com.simpletech.webanalytics.mapper;

import java.util.List;

import com.simpletech.webanalytics.model.Visit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.simpletech.webanalytics.model.IpLocation;
import com.simpletech.webanalytics.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;


/**
 * 数据库表t_ip_location的mapper接口
 * @author 树朾
 * @date 2015-10-16 10:38:40 中国标准时间
 */
public interface IpLocationMapper extends MybatisMultiDao<IpLocation>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO t_ip_location ( id , ip , tb_country , tb_province , tb_city , tb_districk , tb_isp , bd_country , bd_province , bd_city , bd_districk , bd_isp , create_time , tx_districk , tx_city , tx_country , tx_isp , tx_province ,ipip_country, ipip_province, ipip_city, update_time ) VALUES ( #{id} , #{ip} , #{tbCountry} , #{tbProvince} , #{tbCity} , #{tbDistrick} , #{tbIsp} , #{bdCountry} , #{bdProvince} , #{bdCity} , #{bdDistrick} , #{bdIsp} , #{createTime} , #{txDistrick} , #{txCity} , #{txCountry} , #{txIsp} , #{txProvince}, #{ipipCountry} , #{ipipProvince} , #{ipipCity} , #{updateTime} )")
	int insert(IpLocation model) throws Exception;
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_ip_location WHERE id=#{id}")
	int delete(@Param("id") Object id) throws Exception;
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE t_ip_location SET id=#{id} , ip=#{ip} , tb_country=#{tbCountry} , tb_province=#{tbProvince} , tb_city=#{tbCity} , tb_districk=#{tbDistrick} , tb_isp=#{tbIsp} , bd_country=#{bdCountry} , bd_province=#{bdProvince} , bd_city=#{bdCity} , bd_districk=#{bdDistrick} , bd_isp=#{bdIsp} , create_time=#{createTime} , tx_districk=#{txDistrick} , tx_city=#{txCity} , tx_country=#{txCountry} , tx_isp=#{txIsp} , tx_province=#{txProvince} , ipip_country=#{ipipCountry} ,ipip_province= #{ipipProvince} , ipip_city=#{ipipCity} ,update_time=#{updateTime} WHERE id=#{id} ")
	int update(IpLocation model) throws Exception;
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_ip_location")
	int countAll() throws Exception;
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT id , ip , tb_country tbCountry , tb_province tbProvince , tb_city tbCity , tb_districk tbDistrick , tb_isp tbIsp , bd_country bdCountry , bd_province bdProvince , bd_city bdCity , bd_districk bdDistrick , bd_isp bdIsp , create_time createTime , tx_districk txDistrick , tx_city txCity , tx_country txCountry , tx_isp txIsp , tx_province txProvince , ipip_country ipipCountry ,ipip_province ipipProvince , ipip_city ipipCity , update_time updateTime FROM t_ip_location WHERE id=#{id}")
	IpLocation findById(@Param("id") Object id) throws Exception;
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT id , ip , tb_country tbCountry , tb_province tbProvince , tb_city tbCity , tb_districk tbDistrick , tb_isp tbIsp , bd_country bdCountry , bd_province bdProvince , bd_city bdCity , bd_districk bdDistrick , bd_isp bdIsp , create_time createTime , tx_districk txDistrick , tx_city txCity , tx_country txCountry , tx_isp txIsp , tx_province txProvince , ipip_country ipipCountry ,ipip_province ipipProvince , ipip_city ipipCity ,update_time updateTime FROM t_ip_location ${order}")
	List<IpLocation> findAll(@Param("order") String order) throws Exception;
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT id , ip , tb_country tbCountry , tb_province tbProvince , tb_city tbCity , tb_districk tbDistrick , tb_isp tbIsp , bd_country bdCountry , bd_province bdProvince , bd_city bdCity , bd_districk bdDistrick , bd_isp bdIsp , create_time createTime , tx_districk txDistrick , tx_city txCity , tx_country txCountry , tx_isp txIsp , tx_province txProvince ,ipip_country ipipCountry ,ipip_province ipipProvince , ipip_city ipipCity , update_time updateTime FROM t_ip_location ${order} LIMIT ${start},${limit}")
	List<IpLocation> findByPage(@Param("order") String order, @Param("limit") int limit, @Param("start") int start) throws Exception;
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_ip_location ${where}")
	int deleteWhere(@Param("where") String where) throws Exception;
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_ip_location WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value) throws Exception;
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_ip_location ${where}")
	int countWhere(@Param("where") String where) throws Exception;
	/**
	 * 根据属性统计
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM WHERE ${propertyName}=#{value}")
	int countByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value) throws Exception;
	/**
	 * 选择性查询
	 * @param where SQL条件语句
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , ip , tb_country tbCountry , tb_province tbProvince , tb_city tbCity , tb_districk tbDistrick , tb_isp tbIsp , bd_country bdCountry , bd_province bdProvince , bd_city bdCity , bd_districk bdDistrick , bd_isp bdIsp , create_time createTime , tx_districk txDistrick , tx_city txCity , tx_country txCountry , tx_isp txIsp , tx_province txProvince ,ipip_country ipipCountry ,ipip_province ipipProvince , ipip_city ipipCity , update_time updateTime FROM t_ip_location ${where} ${order}")
	List<IpLocation> findWhere(@Param("order") String order, @Param("where") String where) throws Exception;
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , ip , tb_country tbCountry , tb_province tbProvince , tb_city tbCity , tb_districk tbDistrick , tb_isp tbIsp , bd_country bdCountry , bd_province bdProvince , bd_city bdCity , bd_districk bdDistrick , bd_isp bdIsp , create_time createTime , tx_districk txDistrick , tx_city txCity , tx_country txCountry , tx_isp txIsp , tx_province txProvince ,ipip_country ipipCountry ,ipip_province ipipProvince , ipip_city ipipCity , update_time updateTime FROM t_ip_location ${where} ${order} LIMIT ${start},${limit}")
	List<IpLocation> findWhereByPage(@Param("order") String order, @Param("where") String where, @Param("limit") int limit, @Param("start") int start) throws Exception;
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT id , ip , tb_country tbCountry , tb_province tbProvince , tb_city tbCity , tb_districk tbDistrick , tb_isp tbIsp , bd_country bdCountry , bd_province bdProvince , bd_city bdCity , bd_districk bdDistrick , bd_isp bdIsp , create_time createTime , tx_districk txDistrick , tx_city txCity , tx_country txCountry , tx_isp txIsp , tx_province txProvince ,ipip_country ipipCountry ,ipip_province ipipProvince , ipip_city ipipCity , update_time updateTime FROM t_ip_location WHERE ${propertyName}=#{value} ${order}")
	List<IpLocation> findByPropertyName(@Param("order") String order, @Param("propertyName") String propertyName, @Param("value") Object value) throws Exception;
	/**
	 * 查找t_visit中所有未被对比的IP信息
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT id ,location_ip ip , location_country ipipCountry , location_region ipipProvince , location_city ipipCity FROM t_visit WHERE location_compared is null group by location_ip")
	List<IpLocation> findAllIp() throws Exception;



}