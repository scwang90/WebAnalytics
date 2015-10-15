package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.SharePoint;
import com.simpletech.webanalytics.model.ShareUser;

/**
 * 分享传播分析 的Dao接口
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface TrackShareDao {

    /**
     * 获取ShareUser对象
     *
     * @param siteId    网站ID
     * @param idvisitor 访问者ID
     * @return ShareUser or null
     */
    ShareUser getShareUser(int siteId, String idvisitor) throws Exception;

    /**
     * 获取ShareUser对象 (如果不存在则添加)
     *
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @param idvisitor 访问者ID
     * @return 返回有效的 ShareUser 对象
     */
    ShareUser getShareUser(int siteId, String idsubsite, String idvisitor) throws Exception;

    /**
     * 获取上一个分享点 (如果不存在则添加)
     *
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @param idurl     页面ID
     * @param idfromtor 分享者ID
     * @return 返回有效的 SharePoint 对象
     */
    SharePoint getSharePoint(int siteId, String idsubsite, String idurl, String idfromtor) throws Exception;

    /**
     * 获取本次分享点 (如果不存在则添加)
     *
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @param idurl     页面ID
     * @param idfromtor 分享者ID
     * @param idvisitor 接受者ID
     * @return 返回有效的 SharePoint 对象
     */
    SharePoint getSharePoint(int siteId, String idsubsite, String idurl, String idfromtor, String idvisitor) throws Exception;

    /**
     * 添加分享点
     * @param point SharePoint
     */
    int insertSharePoint(SharePoint point) throws Exception;

    /**
     * 更新分享点
     * @param tpoint SharePoint
     */
    int updateSharePoint(SharePoint tpoint) throws Exception;

    /**
     * 添加分用户
     * @param user ShareUser
     */
    int insertShareUser(ShareUser user) throws Exception;

    /**
     * 更新分用户
     * @param user ShareUser
     */
    int updateShareUser(ShareUser user) throws Exception;
}
