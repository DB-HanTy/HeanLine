package com.hty.headline.dao;

import com.hty.headline.pojo.NewsHeadline;
import com.hty.headline.pojo.vo.HeadlineDetailVo;
import com.hty.headline.pojo.vo.HeadlinePageVo;
import com.hty.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    /**
     * ��ҳ��ѯ
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    /**
     * ��ѯҳ��
     * @param headlineQueryVo
     * @return
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);

    /**
     * ���������
     * @param hid
     */
    int incrPageViews(int hid);

    /**
     * ��ѯͷ������
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(int hid);

    /**
     * ���ͷ��
     * @param newsHeadline
     * @return
     */
    int addNewsHeadline(NewsHeadline newsHeadline);

    /**
     * ����hid��ѯͷ��
     * @param hid
     * @return
     */
    NewsHeadline findByHid(Integer hid);

    /**
     * �޸�ͷ��
     * @param newsHeadline
     * @return
     */
    int update(NewsHeadline newsHeadline);

    /**
     * ����hidɾ��ͷ��
     *
     * @param hid
     * @return
     */
    int removeByHid(int hid);
}
