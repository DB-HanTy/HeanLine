package com.hty.headline.service;

import com.hty.headline.pojo.NewsHeadline;
import com.hty.headline.pojo.vo.HeadlineDetailVo;
import com.hty.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {

    /**
     * ����������ѯ��ҳ
     * @param headlineQueryVo
     * @return
     */
    Map findPage(HeadlineQueryVo headlineQueryVo);

    /**
     * ����hid��ѯ��������
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(int hid);

    /**
     * �������
     * @param newsHeadline
     * @return
     */
    int addNewsHeadline(NewsHeadline newsHeadline);

    /**
     * ����hid��ѯ����
     * @param hid
     * @return
     */
    NewsHeadline findByHid(Integer hid);

    /**
     * ��������
     * @param newsHeadline
     * @return
     */
    int update(NewsHeadline newsHeadline);

    /**
     * ����hidɾ������
     * @param hid
     */
    int removeByHid(int hid);
}
