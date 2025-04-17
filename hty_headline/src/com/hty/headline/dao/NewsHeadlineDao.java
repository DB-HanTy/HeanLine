package com.hty.headline.dao;

import com.hty.headline.pojo.NewsHeadline;
import com.hty.headline.pojo.vo.HeadlineDetailVo;
import com.hty.headline.pojo.vo.HeadlinePageVo;
import com.hty.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    /**
     * 分页查询
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    /**
     * 查询页数
     * @param headlineQueryVo
     * @return
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);

    /**
     * 增加浏览量
     * @param hid
     */
    int incrPageViews(int hid);

    /**
     * 查询头条详情
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(int hid);

    /**
     * 添加头条
     * @param newsHeadline
     * @return
     */
    int addNewsHeadline(NewsHeadline newsHeadline);

    /**
     * 根据hid查询头条
     * @param hid
     * @return
     */
    NewsHeadline findByHid(Integer hid);

    /**
     * 修改头条
     * @param newsHeadline
     * @return
     */
    int update(NewsHeadline newsHeadline);

    /**
     * 根据hid删除头条
     *
     * @param hid
     * @return
     */
    int removeByHid(int hid);
}
