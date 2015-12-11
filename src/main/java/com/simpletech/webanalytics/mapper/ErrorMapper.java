package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.model.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 统计错误信息Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface ErrorMapper {

    /**
     * 统计错误信息
     *  设备品牌、设备型号、网络类型、浏览器、操作系统、APP、分辨率、颜色深度、语言、国家、省份、城市
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 数据排行
     */
    @Select("SELECT useragent uerAgent, location_ip ip, end_brand remark FROM t_visit WHERE (end_brand is null OR end_brand='' OR end_brand='UN')  GROUP BY useragent  LIMIT ${skip},${limit}")
    List<ErrorValue> brand(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip, end_model remark FROM t_visit WHERE (end_model is null OR end_model='' OR end_model='UN') AND app_name<>'PC' GROUP BY useragent  LIMIT ${skip},${limit}")
    List<ErrorValue> model(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip, net_type remark FROM t_visit WHERE (net_type is null OR net_type='' OR net_type='UN') AND useragent LIKE '%nettype%' GROUP BY useragent  LIMIT ${skip},${limit}")
    List<ErrorValue> nettype(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip, browser_name remark FROM t_visit WHERE (browser_name is null OR browser_name='' OR browser_name='UN' OR (browser_name='SF' AND end_brand <>'AP')) GROUP BY useragent  LIMIT ${skip},${limit}")
    List<ErrorValue> browser(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip,operate_system remark FROM t_visit WHERE (operate_system is null OR operate_system='' OR operate_system='UN')GROUP BY useragent LIMIT ${skip},${limit}")
    List<ErrorValue> system(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip,app_name remark FROM t_visit WHERE (app_name is null OR app_name='' OR app_name='UN')  GROUP BY useragent LIMIT ${skip},${limit}")
    List<ErrorValue> appname(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip,screen_resolution remark FROM t_visit AND (screen_resolution is null OR screen_resolution='' OR screen_resolution='UN') GROUP BY useragent LIMIT ${skip},${limit}")
    List<ErrorValue> resolution(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip,screen_depth remark FROM t_visit WHERE  (screen_depth is null OR screen_depth='' OR screen_depth='UN') GROUP BY useragent LIMIT ${skip},${limit}")
    List<ErrorValue> depth(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip,location_lang remark FROM t_visit WHERE  (location_lang is null OR location_lang='' OR location_lang='UN') GROUP BY useragent LIMIT ${skip},${limit}")
    List<ErrorValue> lang(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip,location_country remark FROM t_visit WHERE  (location_country is null OR location_country='' OR location_country='UN') GROUP BY useragent LIMIT ${skip},${limit}")
    List<ErrorValue> country( @Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip,location_region remark FROM t_visit WHERE  (location_region is null OR location_region='' OR location_region='UN') GROUP BY useragent LIMIT ${skip},${limit}")
    List<ErrorValue> province(@Param("limit") int limit, @Param("skip") int skip);
    @Select("SELECT useragent uerAgent, location_ip ip,location_city remark FROM t_visit WHERE  (location_city is null OR location_city='' OR location_city='UN') GROUP BY useragent LIMIT ${skip},${limit}")
    List<ErrorValue> city(@Param("limit") int limit, @Param("skip") int skip);

}
