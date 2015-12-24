package com.simpletech.webanalytics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;


/**
 * 数据库表t_visit的mapper接口
 * @author 树朾
 * @date 2015-12-11 18:11:55 中国标准时间
 */
public interface VisitMapper extends MybatisMultiDao<Visit>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO t_visit ( id , idsite , idsubsite , create_time , update_time , new_user , new_sub_user , idvisitor , visit_servertime , visit_localtime , visit_totaltime , idurl , idtitle , idurl_entry , idtitle_entry , idurl_exit , idtitle_exit , url_referer , useragent , operate_system , operate_version , browser_name , browser_version , screen_resolution , screen_depth , sp_java , sp_cookie , location_ip , location_lang , location_country , location_region , location_city , location_isp , location_compared , end_app , end_model , end_brand , end_type , net_type , count_visits , count_events , action_last_time ) VALUES ( #{id} , #{idsite} , #{idsubsite} , #{createTime} , #{updateTime} , #{newUser} , #{newSubUser} , #{idvisitor} , #{visitServertime} , #{visitLocaltime} , #{visitTotaltime} , #{idurl} , #{idtitle} , #{idurlEntry} , #{idtitleEntry} , #{idurlExit} , #{idtitleExit} , #{urlReferer} , #{useragent} , #{operateSystem} , #{operateVersion} , #{browserName} , #{browserVersion} , #{screenResolution} , #{screenDepth} , #{spJava} , #{spCookie} , #{locationIp} , #{locationLang} , #{locationCountry} , #{locationRegion} , #{locationCity} , #{locationIsp} , #{locationCompared} , #{endApp} , #{endModel} , #{endBrand} , #{endType} , #{netType} , #{countVisits} , #{countEvents} , #{actionLastTime} )")
	int insert(Visit model);
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_visit WHERE id=#{id}")
	int delete(@Param("id") Object id);
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE t_visit SET id=#{id} , idsite=#{idsite} , idsubsite=#{idsubsite} , create_time=#{createTime} , update_time=#{updateTime} , new_user=#{newUser} , new_sub_user=#{newSubUser} , idvisitor=#{idvisitor} , visit_servertime=#{visitServertime} , visit_localtime=#{visitLocaltime} , visit_totaltime=#{visitTotaltime} , idurl=#{idurl} , idtitle=#{idtitle} , idurl_entry=#{idurlEntry} , idtitle_entry=#{idtitleEntry} , idurl_exit=#{idurlExit} , idtitle_exit=#{idtitleExit} , url_referer=#{urlReferer} , useragent=#{useragent} , operate_system=#{operateSystem} , operate_version=#{operateVersion} , browser_name=#{browserName} , browser_version=#{browserVersion} , screen_resolution=#{screenResolution} , screen_depth=#{screenDepth} , sp_java=#{spJava} , sp_cookie=#{spCookie} , location_ip=#{locationIp} , location_lang=#{locationLang} , location_country=#{locationCountry} , location_region=#{locationRegion} , location_city=#{locationCity} , location_isp=#{locationIsp} , location_compared=#{locationCompared} , end_app=#{endApp} , end_model=#{endModel} , end_brand=#{endBrand} , end_type=#{endType} , net_type=#{netType} , count_visits=#{countVisits} , count_events=#{countEvents} , action_last_time=#{actionLastTime} WHERE id=#{id} ")
	int update(Visit model);
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_visit")
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , screen_resolution screenResolution , screen_depth screenDepth , sp_java spJava , sp_cookie spCookie , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity , location_isp locationIsp , location_compared locationCompared , end_app endApp , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , count_events countEvents , action_last_time actionLastTime FROM t_visit WHERE id=#{id}")
	Visit findById(@Param("id") Object id);
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , screen_resolution screenResolution , screen_depth screenDepth , sp_java spJava , sp_cookie spCookie , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity , location_isp locationIsp , location_compared locationCompared , end_app endApp , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , count_events countEvents , action_last_time actionLastTime FROM t_visit ${order}")
	List<Visit> findAll(@Param("order") String order);
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , screen_resolution screenResolution , screen_depth screenDepth , sp_java spJava , sp_cookie spCookie , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity , location_isp locationIsp , location_compared locationCompared , end_app endApp , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , count_events countEvents , action_last_time actionLastTime FROM t_visit ${order} LIMIT ${start},${limit}")
	List<Visit> findByPage(@Param("order") String order,@Param("limit") int limit,@Param("start") int start);
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_visit ${where}")
	int deleteWhere(@Param("where") String where);
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_visit WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value);
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_visit ${where}")
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
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , screen_resolution screenResolution , screen_depth screenDepth , sp_java spJava , sp_cookie spCookie , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity , location_isp locationIsp , location_compared locationCompared , end_app endApp , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , count_events countEvents , action_last_time actionLastTime FROM t_visit ${where} ${order}")
	List<Visit> findWhere(@Param("order") String order,@Param("where") String where);
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , screen_resolution screenResolution , screen_depth screenDepth , sp_java spJava , sp_cookie spCookie , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity , location_isp locationIsp , location_compared locationCompared , end_app endApp , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , count_events countEvents , action_last_time actionLastTime FROM t_visit ${where} ${order} LIMIT ${start},${limit}")
	List<Visit> findWhereByPage(@Param("order") String order,@Param("where") String where,@Param("limit") int limit,@Param("start") int start);
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , screen_resolution screenResolution , screen_depth screenDepth , sp_java spJava , sp_cookie spCookie , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity , location_isp locationIsp , location_compared locationCompared , end_app endApp , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , count_events countEvents , action_last_time actionLastTime FROM t_visit WHERE ${propertyName}=#{value} ${order}")
	List<Visit> findByPropertyName(@Param("order") String order,@Param("propertyName") String propertyName,@Param("value") Object value);
}