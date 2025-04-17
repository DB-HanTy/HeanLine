package com.hty.headline.service;

import com.hty.headline.pojo.NewsHeadline;
import com.hty.headline.pojo.vo.HeadlineDetailVo;
import com.hty.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {

    /**
     * 根据条件查询分页
     * @param headlineQueryVo
     * @return
     */
    Map findPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据hid查询新闻详情
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(int hid);

    /**
     * 添加新闻
     * @param newsHeadline
     * @return
     */
    int addNewsHeadline(NewsHeadline newsHeadline);

    /**
     * 根据hid查询新闻
     * @param hid
     * @return
     */
    NewsHeadline findByHid(Integer hid);

    /**
     * 更新新闻
     * @param newsHeadline
     * @return
     */
    int update(NewsHeadline newsHeadline);

    /**
     * 根据hid删除新闻
     * @param hid
     */
    int removeByHid(int hid);
}
