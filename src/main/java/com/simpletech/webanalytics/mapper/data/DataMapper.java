package com.simpletech.webanalytics.mapper.data;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 数据查询处理Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface DataMapper {

    @Select("SELECT *\n" +
            "FROM (SELECT\n" +
            "        mac_device,\n" +
            "        idshop,\n" +
            "        sum(is_new_user) num,\n" +
            "        count(id)        vt,\n" +
            "        sum(count_logs)  pv\n" +
            "      FROM t_visit\n" +
            "      GROUP BY mac_device, idshop\n" +
            "      ORDER BY mac_device) AS t\n" +
            "WHERE num > 1\n" +
            "LIMIT ${skip},${limit}")
    List<Map<String,Object>> abnormalNewUserVisit(@Param("limit") int limit, @Param("skip") int skip);

}

