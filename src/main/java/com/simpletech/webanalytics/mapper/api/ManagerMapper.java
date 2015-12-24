package com.simpletech.webanalytics.mapper.api;

import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.Subsite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 管理API 的mapper接口
 *
 * @author 树朾
 * @date 2015-10-12 15:00:31 中国标准时间
 */
public interface ManagerMapper {

    /**
     * 添加网站
     *
     * @param site 网站
     * @return 改变行数
     */
    @Insert("INSERT INTO t_site ( id , iduser , create_time , update_time , name , domain , regex ) VALUES ( #{id} , #{iduser} , #{createTime} , #{updateTime} , #{name} , #{domain} , #{regex} )")
    int insertSite(Site site);

    /**
     * 更新网站
     *
     * @param site 网站
     * @return 改变行数
     */
    @Update("UPDATE t_site SET update_time=#{updateTime} , name=#{name} , domain=#{domain} , regex=#{regex} WHERE id=#{id} AND iduser=#{iduser} ")
    int updateSite(Site site);

    /**
     * 获取网站列表
     *
     * @param limit 分页限制
     * @param start 分页开始
     * @return 网站列表
     */
    @Select("SELECT id, iduser , create_time createTime , update_time updateTime , name , domain , regex FROM t_site WHERE iduser=#{userId} LIMIT ${start},${limit}")
    List<Site> findSiteList(@Param("userId") String userId, @Param("limit") int limit, @Param("start") int start);

    /**
     * 获取子站列表
     *
     * @param siteId 网站ID
     * @param limit  分页限制
     * @param start  分页开始
     * @return 子站列表
     */
    @Select("SELECT id , idsite , name , remark , create_time createTime , update_time updateTime FROM t_subsite WHERE idsite=#{siteId} LIMIT ${start},${limit}")
    List<Subsite> findSubSiteList(@Param("userId") String userId, @Param("siteId") int siteId, @Param("limit") int limit, @Param("start") int start);

}