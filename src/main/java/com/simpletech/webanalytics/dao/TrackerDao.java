package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.*;
import com.simpletech.webanalytics.model.entity.JsDetect;

/**
 * js探针 的Dao接口
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface TrackerDao {

    /**
     * 根据 siteId 获取 url 的对象 (如果不存在则添加)
     *
     * @param siteId 网站ID
     * @param url    链接
     * @param title  标题 (添加的时候需要指定)
     * @return 返回有效的 Url 对象
     */
    Url getUrl(int siteId, String idsubsite, String url, String title);

    /**
     * 根据 siteId 获取 title 的对象 (如果不存在则添加)
     *
     * @param siteId 网站ID
     * @param title  标题
     * @return 返回有效的 Title 对象
     */
    Title getTitle(int siteId, String idsubsite, String title);

    /**
     * 根据 siteId 获取 Subsite 的对象 (如果不存在则添加)
     *
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     */
    Subsite getSubSite(int siteId, String idsubsite);

    /**
     * 通过ID获取 Visit
     *
     * @param idvisit ID
     */
    Visit getVisitById(String idvisit);

    /**
     * 获取 siteId网站 访问者detect 30分钟内的 Visit（如果不存在则添加）
     *
     * @param siteId 网站ID
     * @param idsubsite 子站ID
     * @param detect 探针抓取对象
     * @param url    Url 对象
     * @param title  Title 对象
     * @return 返回有效的 Visit 对象
     */
    Visit getVisitHalfHour(int siteId, String idsubsite, JsDetect detect, Url url, Title title);

    /**
     * 获取 siteId网站 访问者detect 30分钟内的 Action（如果不存在则添加）
     *
     * @param siteId 网站ID
     * @param idsubsite 子站ID
     * @param detect 探针抓取对象
     * @param url    Url 对象
     * @param title  Title 对象
     * @return 返回有效的 Visit 对象
     */
    Action getActionHalfHour(int siteId, String idsubsite, JsDetect detect, Url url, Title title);

    /**
     * 根据网站ID获取网站
     *
     * @param siteId 网站ID
     * @return 网站
     */
    Site findSiteById(int siteId);

    /**
     * 更新 Visit
     * @param idsubsite 子站ID
     *
     * @param visit Visit
     */
    void updateVisit(String idsubsite, Visit visit);

    /**
     * 添加 action
     * @param idsubsite 子站ID
     *
     * @param action action
     */
    void insertAction(String idsubsite, Action action);

    /**
     * 添加事件
     * @param idsubsite 子站ID
     *
     * @param event event
     */
    void insertEvent(String idsubsite, Event event);

    /**
     * 新的 Event 产生时更新 Visit
     *  主要是 事件数量统计
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @param idvtor    访问者ID
     * @return 改变行数
     */
    int updateVisitEvent(int siteId, String idsubsite, String idvtor);

    /**
     * 新的 Pv 产生时更新 Visit
     * 主要是更新退出页，计算pv统计，时间计算
     *
     * @param idvisit     Visit的ID
     * @param idurlExit   退出页的链接
     * @param idtitleExit 退出页的标题
     * @return 改变行数
     */
    int updateVisitPageView(String idvisit, String idurlExit, String idtitleExit);

    /**
     * 新创建一个 Visit
     *
     * @param siteId    网站ID
     * @param idsubsite 子站ID
     * @param detect    探针抓取对象
     * @param url       Url 对象
     * @param title     Title 对象
     * @return 新的VisitID
     */
    String newVisit(int siteId, String idsubsite, JsDetect detect, Url url, Title title);
}
