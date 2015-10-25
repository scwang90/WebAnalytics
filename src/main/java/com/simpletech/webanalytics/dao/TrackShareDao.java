package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.ShareUser;
import com.simpletech.webanalytics.model.entity.JsDetect;

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
     * @param detect    Js探针数据
     * @return 返回有效的 ShareUser 对象
     */
    ShareUser getShareUser(int siteId, String idsubsite, String idvisitor, JsDetect detect) throws Exception;

//    /**
//     * 获取上一个分享点 (如果不存在则添加)
//     *
//     * @param siteId    网站ID
//     * @param idsubsite 子站ID
//     * @param idurl     页面ID
//     * @param idfromtor 分享者ID
//     * @param fromts    时间分享时间
//     * @return 返回有效的 ShareLinePoint 对象
//     */
//    ShareLinePoint getShareLinePoint(int siteId, String idsubsite, String idurl, String idfromtor, long fromts) throws Exception;

    /**
     * 获取本次分享点 (如果不存在则添加)
     *
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @param idurl     页面ID
     * @param idfromtor 分享者ID
     * @param idvisitor 接受者ID
     * @return 返回有效的 ShareLinePoint 对象
     */
    ShareLinePoint getShareLinePoint(int siteId, String idsubsite, String idurl, String idfromtor, String idvisitor) throws Exception;

    /**
     * 添加分享点
     *
     * @param point ShareLinePoint
     */
    int insertShareLinePoint(ShareLinePoint point) throws Exception;

    /**
     * 更新分享点
     *
     * @param tpoint ShareLinePoint
     */
    int updateShareLinePoint(ShareLinePoint tpoint) throws Exception;

    /**
     * 添加分用户
     *
     * @param user ShareUser
     */
    int insertShareUser(ShareUser user) throws Exception;

    /**
     * 更新分用户
     *
     * @param user ShareUser
     */
    int updateShareUser(ShareUser user) throws Exception;

    /**
     * 判断并创建起始点
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @param idurl     页面ID
     * @param idfromtor 分享者ID
     * @return true 创建
     */
    boolean makeSureStartPoint(int siteId, String idsubsite, String idurl, String idfromtor) throws Exception;
}
