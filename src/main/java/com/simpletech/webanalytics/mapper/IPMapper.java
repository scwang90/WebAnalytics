package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.model.IP;
import com.simpletech.webanalytics.model.Visit;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * IP查询
 * Created by ChenQi on 2015/12/10 18:10.
 */
public interface IPMapper {

    @Select("SELECT ID,start_ip_num sIP,end_ip_num eIP,start_ip sIP1,end_ip eIP1,province,city,isp\n" +
            "FROM t_ip\n" +
            "WHERE start_ip<=#{ipNum} AND end_ip>=#{ipNum}")
    List<IP> find( @Param("ipNum") long ipNUm);

}
