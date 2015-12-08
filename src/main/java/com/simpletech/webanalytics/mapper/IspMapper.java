package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.entity.IspValue;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 数据库表t_visit的mapper接口
 * @author 树朾
 * @date 2015-10-28 14:33:37 中国标准时间
 */
public interface IspMapper {


	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE t_visit SET id=#{id} , idsite=#{idsite} , idsubsite=#{idsubsite} , create_time=#{createTime} , update_time=#{updateTime} , new_user=#{newUser} , new_sub_user=#{newSubUser} , idvisitor=#{idvisitor} , visit_servertime=#{visitServertime} , visit_localtime=#{visitLocaltime} , visit_totaltime=#{visitTotaltime} , idurl=#{idurl} , idtitle=#{idtitle} , idurl_entry=#{idurlEntry} , idtitle_entry=#{idtitleEntry} , idurl_exit=#{idurlExit} , idtitle_exit=#{idtitleExit} , url_referer=#{urlReferer} , useragent=#{useragent} , operate_system=#{operateSystem} , operate_version=#{operateVersion} , browser_name=#{browserName} , browser_version=#{browserVersion} , screen_resolution=#{screenResolution} , screen_depth=#{screenDepth} , sp_java=#{spJava} , sp_cookie=#{spCookie} , location_ip=#{locationIp} , location_lang=#{locationLang} , location_country=#{locationCountry} , location_region=#{locationRegion} , location_city=#{locationCity} , location_isp=#{locationIsp} ,  location_compared=#{locationCompared} , end_app=#{endApp} , end_model=#{endModel} , end_brand=#{endBrand} , end_type=#{endType} , net_type=#{netType} , count_visits=#{countVisits} , action_last_time=#{actionLastTime} , count_events=#{countEvents} WHERE id=#{id} ")
	int updateIsp(Visit model);

	/**
	 * 选择性查询
	 * @param where SQL条件语句
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , screen_resolution screenResolution , screen_depth screenDepth , sp_java spJava , sp_cookie spCookie , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity ,location_isp locationIsp , location_compared locationCompared , end_app endApp , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , action_last_time actionLastTime , count_events countEvents FROM t_visit ${where} LIMIT ${start},${limit}")
	List<Visit> findWhereIsp( @Param("where") String where,@Param("limit") int limit,@Param("start") int start);

	/**
	 * 按条件分页查询
	 * @param where		SQL语句
	 * @param limit		每页显示条数
	 * @param start		起始页
	 * @return			符合条件的列表数据
	 * @throws Exception
	 */
	@Select("SELECT id , idsite , idsubsite , create_time  , update_time  , new_user  , new_sub_user  , idvisitor , visit_servertime  , visit_localtime  , visit_totaltime  , idurl , idtitle , idurl_entry  , idtitle_entry  , idurl_exit  , idtitle_exit  , url_referer  , useragent , operate_system  , operate_version  , browser_name  , browser_version  , screen_resolution  , screen_depth  , sp_java  , sp_cookie  , location_ip  , location_lang  , location_country  , location_region  , location_city  ,location_isp  , location_compared  , end_app  , end_model  , end_brand  , end_type  , net_type  , count_visits  , action_last_time  , count_events  FROM t_visit ${where} LIMIT ${start},${limit}")
	List<HashMap<String, Object>> findWhere( @Param("where") String where,@Param("limit") int limit,@Param("start") int start);

	/**
	 * 统计isp信息
	 * @param siteId
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT\n" +
			"  count(location_isp) num,\n" +
			"  location_isp        name\n" +
			"FROM t_visit\n" +
			"WHERE idsite = ${siteId} AND location_isp IS NOT NULL AND (visit_servertime BETWEEN #{start} AND #{end})\n" +
			"GROUP BY location_isp\n" +
			"ORDER BY num DESC")
	List<IspValue> isp(@Param("siteId") String siteId, @Param("start") Date start, @Param("end") Date end);
}