package com.simpletech.webanalytics.mapper.api;

import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.ShareStartPoint;
import com.simpletech.webanalytics.model.ShareUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 分享传播 Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface TrackShareMapper {

    @Select("SELECT id , idsite , idsubsite , idvisitor , openid , unionid , nickname , headimgurl , sex , province , city , country , privilege , create_time createTime , update_time updateTime FROM t_share_user WHERE idsite=#{siteId} AND idvisitor=#{idvisitor}")
    ShareUser getShareUser(@Param("siteId") int siteId,@Param("idvisitor") String idvisitor);

    @Insert("INSERT INTO t_share_user ( id , idsite , idsubsite , idvisitor , openid , unionid , nickname , headimgurl , sex , province , city , country , privilege , create_time , update_time ) VALUES ( #{id} , #{idsite} , #{idsubsite} , #{idvisitor} , #{openid} , #{unionid} , #{nickname} , #{headimgurl} , #{sex} , #{province} , #{city} , #{country} , #{privilege} , #{createTime} , #{updateTime} )")
    int insertShareUser(ShareUser user);

    @Insert("INSERT INTO t_share_line_point ( id , idsite , idsubsite , idurl , idvisitor , idrefervisitor , is_start_point , share_to , count_pv , share_span , share_time , create_time , update_time ) VALUES ( #{id} , #{idsite} , #{idsubsite} , #{idurl} , #{idvisitor} , #{idrefervisitor} , #{isStartPoint} , #{shareTo} , #{countPv} , #{shareSpan} , #{shareTime} , #{createTime} , #{updateTime} )")
    int insertShareLinePoint(ShareLinePoint point);

    @Select("SELECT id , idsite , idsubsite , idurl , idvisitor , idrefervisitor , is_start_point isStartPoint , share_to shareTo , count_pv countPv , share_span shareSpan , share_time shareTime , create_time createTime , update_time updateTime FROM t_share_line_point WHERE idsite=#{siteId} AND idurl=#{idurl} AND idvisitor=#{idvisitor} AND idrefervisitor=#{idfromtor}")
    ShareLinePoint getShareLinePoint(@Param("siteId") int siteId, @Param("idurl") String idurl, @Param("idfromtor") String idfromtor, @Param("idvisitor") String idvisitor);

    @Update("UPDATE t_share_line_point SET id=#{id} , idsite=#{idsite} , idsubsite=#{idsubsite} , idurl=#{idurl} , idvisitor=#{idvisitor} , idrefervisitor=#{idrefervisitor} , count_pv=#{countPv} , share_span=#{shareSpan} , share_time=#{shareTime} , create_time=#{createTime} , update_time=#{updateTime} WHERE id=#{id} ")
    int updateShareLinePoint(ShareLinePoint point);

    @Update("UPDATE t_share_user SET id=#{id} , idsite=#{idsite} , idsubsite=#{idsubsite} , idvisitor=#{idvisitor} , openid=#{openid} , unionid=#{unionid} , nickname=#{nickname} , headimgurl=#{headimgurl} , sex=#{sex} , province=#{province} , city=#{city} , country=#{country} , privilege=#{privilege} , create_time=#{createTime} , update_time=#{updateTime} WHERE id=#{id} ")
    int updateShareUser(ShareUser user);

    /**
     * 判断 idfromtor 是不是起始点
     * @param siteId 网站ID
     * @param idurl 页面ID
     * @param idfromtor 分享者
     * @return 0 是 > 0 不是
     */
    @Select("SELECT COUNT(*)=0 from t_share_line_point WHERE idvisitor=#{idfromtor} AND idurl=#{idurl}")
    Boolean isStartPoint(@Param("siteId") int siteId, @Param("idurl") String idurl, @Param("idfromtor") String idfromtor);

    /**
     * 判断是否已经存在 idfromtor 的起始点
     * @param siteId 网站ID
     * @param idurl 页面ID
     * @param idfromtor 分享者
     * @return  > 0 存在
     */
    @Select("SELECT COUNT(*) from t_share_start_point WHERE idvisitor=#{idfromtor} AND idurl=#{idurl}")
    int existStartPoint(@Param("siteId") int siteId, @Param("idurl") String idurl, @Param("idfromtor") String idfromtor);


    /**
     * 级联判断判断是否已经存在 idfromtor 的起始点
     * @param siteId 网站ID
     * @param idurl 页面ID
     * @param idfromtor 分享者
     * @return  > 0 存在
     */
    @Select("SELECT csp+clp cp FROM " +
            "(SELECT COUNT(*) csp from t_share_start_point WHERE idvisitor=#{idfromtor} AND idurl=#{idurl}) AS t1," +
            "(SELECT COUNT(*) clp from t_share_line_point WHERE idvisitor=#{idfromtor} AND idurl=#{idurl}) AS t2")
    int checkStartPoint(@Param("siteId") int siteId, @Param("idurl") String idurl, @Param("idfromtor") String idfromtor);

    /**
     * 添加起始点
     * @param point 点
     * @return 改变行数
     * @throws Exception
     */
    @Insert("INSERT INTO t_share_start_point ( id , idsite , idsubsite , idurl , idvisitor , create_time , update_time ) VALUES ( #{id} , #{idsite} , #{idsubsite} , #{idurl} , #{idvisitor} , #{createTime} , #{updateTime} )")
    int insertShareStartPoint(ShareStartPoint point);

}
