package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.model.Point;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * IP查询
 * Created by ChenQi on 2015/12/10 18:10.
 */
public interface PointMapper {

    @Select("SELECT idvisitor ep ,idrefervisitor sp FROM t_share_line_point where idrefervisitor=#{idrefervisitor}")
    List<Point> findPoint(@Param("idrefervisitor") String idrefervisitor);

}
