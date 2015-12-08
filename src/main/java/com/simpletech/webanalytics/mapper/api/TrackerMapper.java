package com.simpletech.webanalytics.mapper.api;

import com.simpletech.webanalytics.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * js探针 Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface TrackerMapper {
    /**
     * 根据网站ID获取网站
     *
     * @param id 网站ID
     * @return 网站
     */
    @Select("SELECT id , create_time createTime , update_time updateTime , name , domain , regex FROM t_site WHERE id=#{id}")
    Site findSiteById(@Param("id") int id);

    @Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , hash , url FROM t_url WHERE idsite=#{siteId} AND hash=#{hash}")
    Url getUrl(@Param("siteId") int siteId, @Param("hash") int hash);

    @Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , hash , title FROM t_title WHERE idsite=#{siteId} AND hash=#{hash}")
    Title getTitle(@Param("siteId") int siteId, @Param("hash") int hash);

    /**
     * 获取子站
     *
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @return Subsite or null
     */
    @Select("SELECT id , idsite , name , remark , create_time createTime , update_time updateTime FROM t_subsite WHERE idsite=#{siteId} AND name=#{idsubsite}")
    Subsite getSubSite(@Param("siteId") int siteId, @Param("idsubsite") String idsubsite);


    /**
     * 通过ID获取 Visit
     * @param id ID
     */
    @Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , screen_resolution screenResolution , screen_depth screenDepth , sp_java spJava , sp_cookie spCookie , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity , location_compared locationCompared , end_app endApp , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , action_last_time actionLastTime , count_events countEvents FROM t_visit WHERE id=#{id}")
    Visit getVisitById(@Param("id") String id);

    /**
     * 获取 siteId网站 访问者detect 30分钟内的 Visit
     *
     * @param siteId 网站ID
     * @param idvtor 访问者ID
     * @return Visit or null
     */
    @Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , new_user newUser , new_sub_user newSubUser , idvisitor , visit_servertime visitServertime , visit_localtime visitLocaltime , visit_totaltime visitTotaltime , idurl , idtitle , idurl_entry idurlEntry , idtitle_entry idtitleEntry , idurl_exit idurlExit , idtitle_exit idtitleExit , url_referer urlReferer , useragent , operate_system operateSystem , operate_version operateVersion , browser_name browserName , browser_version browserVersion , end_app endApp , screen_resolution screenResolution , screen_depth screenDepth , location_ip locationIp , location_lang locationLang , location_country locationCountry , location_region locationRegion , location_city locationCity , end_model endModel , end_brand endBrand , end_type endType , net_type netType , count_visits countVisits , count_events countEvents FROM t_visit WHERE  idsite=${siteId} AND idvisitor=#{idvtor} AND visit_servertime > DATE_SUB(NOW(),INTERVAL 30 MINUTE) LIMIT 0,1")
    Visit getVisitHalfHour(@Param("siteId") String siteId, @Param("idvtor") String idvtor);

    /**
     * 获取 siteId网站 访问者detect 30分钟内的 Visit
     *
     * @param siteId 网站ID
     * @param idvtor 访问者ID
     * @return Visit or null
     */
    @Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , idvisitor , idvisit , idtitle , idurl , server_time serverTime , time_loaded timeLoaded , ref_id_url refIdUrl , ref_id_title refIdTitle , ref_time_spent refTimeSpent FROM t_action WHERE idsite=${siteId} AND idvisitor=#{idvtor} AND create_time > DATE_SUB(NOW(),INTERVAL 30 MINUTE) LIMIT 0,1")
    Action getActionHalfHour(@Param("siteId") String siteId, @Param("idvtor") String idvtor);

    /**
     * 判断是否已经存在 访问者idvtor
     *
     * @param siteId 网站ID
     * @param idvtor 访问者ID
     * @return true 存在 false 不存在
     */
    @Select("SELECT COUNT(id) FROM t_visit WHERE idsite=#{siteId} AND idvisitor=#{idvtor}")
    boolean existVisitor(@Param("siteId") int siteId, @Param("idvtor") String idvtor);

    /**
     * 判断是否已经存在 访问者idvtor
     *
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @param idvtor    访问者ID
     * @return true 存在 false 不存在
     */
    @Select("SELECT COUNT(id) FROM t_visit WHERE idsite=#{siteId} AND idsubsite=#{idsubsite} AND idvisitor=#{idvtor}")
    boolean existSubVisitor(@Param("siteId") int siteId, @Param("idsubsite") String idsubsite, @Param("idvtor") String idvtor);

    @Insert("INSERT INTO t_url ( id , idsite , idsubsite , create_time , update_time , hash , hash_title , url ) VALUES ( #{id} , #{idsite} , #{idsubsite} , NOW() , NOW() , #{hash} , #{hashTitle} , #{url} )")
    int insertUrl(Url url);

    @Insert("INSERT INTO t_title ( id , idsite , idsubsite , create_time , update_time , hash , title ) VALUES ( #{id} , #{idsite} , #{idsubsite} , NOW() , NOW() , #{hash} , #{title} )")
    int insertTitle(Title title);

    @Insert("INSERT INTO t_visit ( id , idsite , idsubsite , create_time , update_time , new_user , new_sub_user , idvisitor , visit_servertime , visit_localtime , visit_totaltime , idurl_entry , idtitle_entry , idurl_exit , idtitle_exit , url_referer , useragent , operate_system , operate_version , browser_name , browser_version , screen_resolution , screen_depth , sp_java , sp_cookie , location_ip , location_lang , location_country , location_region , location_city ,location_isp , location_compared , end_app , end_model , end_brand , end_type , net_type , count_visits , action_last_time , count_events ) VALUES ( #{id} , #{idsite} , #{idsubsite} , NOW() , NOW() , #{newUser} , #{newSubUser} , #{idvisitor} , NOW() , #{visitLocaltime} , #{visitTotaltime} , #{idurlEntry} , #{idtitleEntry} , #{idurlExit} , #{idtitleExit} , #{urlReferer} , #{useragent} , #{operateSystem} , #{operateVersion} , #{browserName} , #{browserVersion} , #{screenResolution} , #{screenDepth} , #{spJava} , #{spCookie} , #{locationIp} , #{locationLang} , #{locationCountry} , #{locationRegion} , #{locationCity} ,#{locationIsp} , #{locationCompared} , #{endApp} , #{endModel} , #{endBrand} , #{endType} , #{netType} , #{countVisits} , NOW() , #{countEvents} )")
    int insertVisit(Visit visit);

    /**
     * 更新 Visit
     */
    @Update("UPDATE t_visit SET idsite=#{idsite} , idsubsite=#{idsubsite} , update_time=NOW() , new_user=#{newUser} , new_sub_user=#{newSubUser} , idvisitor=#{idvisitor} , visit_localtime=#{visitLocaltime} , visit_totaltime=#{visitTotaltime} , idurl_entry=#{idurlEntry} , idtitle_entry=#{idtitleEntry} , idurl_exit=#{idurlExit} , idtitle_exit=#{idtitleExit} , url_referer=#{urlReferer} , useragent=#{useragent} , operate_system=#{operateSystem} , operate_version=#{operateVersion} , browser_name=#{browserName} , browser_version=#{browserVersion} , end_app=#{endApp} , screen_resolution=#{screenResolution} , screen_depth=#{screenDepth} , location_ip=#{locationIp} , location_lang=#{locationLang} , location_country=#{locationCountry} , location_region=#{locationRegion} , location_city=#{locationCity} ,location_isp=#{locationIsp} , end_model=#{endModel} , end_brand=#{endBrand} , end_type=#{endType} , net_type=#{netType} , count_visits=#{countVisits} , action_last_time=#{actionLastTime} , count_events=#{countEvents} WHERE id=#{id} ")
    int updateVisit(Visit visit);

    /**
     * 添加 action
     */
    @Insert("INSERT INTO t_action ( id , idsite , idsubsite , create_time , update_time , idvisitor , idvisit , idtitle , idurl , server_time , time_loaded , ref_id_url , ref_id_title , ref_time_spent ) VALUES ( #{id} , #{idsite} , #{idsubsite} , NOW() , NOW() , #{idvisitor} , #{idvisit} , #{idtitle} , #{idurl} , #{serverTime} , #{timeLoaded} , #{refIdUrl} , #{refIdTitle} , #{refTimeSpent} )")
    int insertAction(Action action);

    /**
     * 添加事件 event
     */
    @Insert("INSERT INTO t_event ( id , idsite , idsubsite , idvisitor , local_time , create_time , update_time , category , action , name , value ) VALUES ( #{id} , #{idsite} , #{idsubsite} , #{idvisitor} , #{localTime} , NOW() , NOW() , #{category} , #{action} , #{name} , #{value} )")
    int insertEvent(Event event);

    /**
     * 添加子站 site
     */
    @Insert("INSERT INTO t_subsite ( id , idsite , name , remark , create_time , update_time ) VALUES ( #{id} , #{idsite} , #{name} , #{remark} , NOW() , NOW() )")
    int insertSubSite(Subsite site);

    /**
     * 新的 Pv 产生时更新 Visit
     *  主要是更新退出页，计算pv统计，时间计算
     * @param idvisit       Visit的ID
     * @param idurlExit     退出页的链接
     * @param idtitleExit   退出页的标题
     */
    @Update("UPDATE t_visit SET update_time=NOW() , idurl_exit=#{idurlExit} , idtitle_exit=#{idtitleExit} , count_visits=count_visits+1 , action_last_time=NOW() WHERE id=#{id} ")
    int updateVisitPageView(@Param("id") String idvisit, @Param("idurlExit") String idurlExit, @Param("idtitleExit") String idtitleExit);

    /**
     * 新的 Event 产生时更新 Visit
     *  主要是 事件数量统计
     *
     * @param id    VisitID
     * @return 改变行数
     */
    @Update("UPDATE t_visit SET update_time=NOW(), count_events=count_events+1 WHERE id=#{id} ")
    int updateVisitEvent(@Param("id") String id);

    /**
     * 获取最新的 Visit
     * @param siteId    网站ID
     * @param idvtor    访问者ID
     * @return Visit or null
     */
    @Select("SELECT id FROM t_visit WHERE idsite=${siteId} AND idvisitor=#{idvtor} ORDER BY create_time DESC limit 0,1 ")
    Visit findLastVisit(@Param("siteId") String siteId, @Param("idvtor") String idvtor);
}
