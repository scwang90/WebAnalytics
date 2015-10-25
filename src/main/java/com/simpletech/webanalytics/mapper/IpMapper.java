package com.simpletech.webanalytics.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 * IP Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface IpMapper {


    @Select("SELECT userip,COUNT(*) pv FROM vote_num WHERE `parser`=0 GROUP BY userip ORDER BY pv DESC")
    List<HashMap<String,Object>> findGroupIp();

    @Select("SELECT userip FROM vote_num WHERE `parser`=0 ORDER BY userip")
    List<HashMap<String,Object>> findIp();

    @Select("SELECT userip FROM vote_num WHERE `parser`=0 ORDER BY userip DESC")
    List<HashMap<String,Object>> findIpDESC();

    @Update("UPDATE vote_num SET `parser`=1,city=#{city},province=#{province},country=#{country} WHERE userip=#{userip}")
    int setLocation(@Param("userip") String userip,@Param("city")  String city,@Param("province")  String province,@Param("country")  String country);

}
