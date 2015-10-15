package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.model.SharePoint;
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
    ShareUser getShareUser(@Param("siteId") int siteId,@Param("idvisitor") String idvisitor) throws Exception;

    @Insert("INSERT INTO t_share_user ( id , idsite , idsubsite , idvisitor , openid , unionid , nickname , headimgurl , sex , province , city , country , privilege , create_time , update_time ) VALUES ( #{id} , #{idsite} , #{idsubsite} , #{idvisitor} , #{openid} , #{unionid} , #{nickname} , #{headimgurl} , #{sex} , #{province} , #{city} , #{country} , #{privilege} , #{createTime} , #{updateTime} )")
    int insertShareUser(ShareUser user) throws Exception;

    @Select("SELECT id , idsite , idsubsite , idvisitor , idurl , idrefer , count_pv countPv , share_time shareTime , create_time createTime , update_time updateTime FROM t_share_point WHERE idsite=#{siteId} AND idurl=#{idurl} AND idvisitor=#{idvisitor}")
    SharePoint getSharePoint(@Param("siteId") int siteId, @Param("idurl") String idurl, @Param("idvisitor") String idvisitor) throws Exception;

    @Insert("INSERT INTO t_share_point ( id , idsite , idsubsite , idvisitor , idurl , idrefer , count_pv , share_time , create_time , update_time ) VALUES ( #{id} , #{idsite} , #{idsubsite} , #{idvisitor} , #{idurl} , #{idrefer} , #{countPv} , #{shareTime} , #{createTime} , #{updateTime} )")
    int insertSharePoint(SharePoint point) throws Exception;

    @Select("SELECT id , idsite , idsubsite , idvisitor , idurl , idrefer , count_pv countPv , share_time shareTime , create_time createTime , update_time updateTime FROM t_share_point WHERE idsite=#{siteId} AND idurl=#{idurl} AND idvisitor=#{idvisitor} AND idrefer=#{idfromtor}")
    SharePoint getSharePoint(@Param("siteId") int siteId, @Param("idurl") String idurl, @Param("idfromtor") String idfromtor, @Param("idvisitor") String idvisitor) throws Exception;

    @Update("UPDATE t_share_point SET id=#{id} , idsite=#{idsite} , idsubsite=#{idsubsite} , idvisitor=#{idvisitor} , idurl=#{idurl} , idrefer=#{idrefer} , count_pv=#{countPv} , share_time=#{shareTime} , create_time=#{createTime} , update_time=#{updateTime} WHERE id=#{id} ")
    int updateSharePoint(SharePoint point) throws Exception;

    @Update("UPDATE t_share_user SET id=#{id} , idsite=#{idsite} , idsubsite=#{idsubsite} , idvisitor=#{idvisitor} , openid=#{openid} , unionid=#{unionid} , nickname=#{nickname} , headimgurl=#{headimgurl} , sex=#{sex} , province=#{province} , city=#{city} , country=#{country} , privilege=#{privilege} , create_time=#{createTime} , update_time=#{updateTime} WHERE id=#{id} ")
    int updateShareUser(ShareUser user) throws Exception;
}
