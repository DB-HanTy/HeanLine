package com.hty.headline.service.impl;

import com.hty.headline.dao.NewsHeadlineDao;
import com.hty.headline.dao.impl.NewsHeadlineDaoImpl;
import com.hty.headline.pojo.NewsHeadline;
import com.hty.headline.pojo.vo.HeadlineDetailVo;
import com.hty.headline.pojo.vo.HeadlinePageVo;
import com.hty.headline.pojo.vo.HeadlineQueryVo;
import com.hty.headline.service.NewsHeadlineService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private NewsHeadlineDao headlineDao = new NewsHeadlineDaoImpl();

    /**
     * pageData:[
     *   {
     *       "hid":"1",                        //����id
     *       "title":"����",                   //���ű���
     *       "type":"1",                      //�������������
     *       "pageViews":"40",                //���������
     *       "pastHours":"3",                 //����ʱ���ѹ�Сʱ��
     *       "publisher":"1"
     *   }
     * ],
     * pageNum:1,
     * pageSize:1,
     * totalPage:1,
     * totalSize:1
     *
     */
    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageData = headlineDao.findPageList(headlineQueryVo);
        int totalSize = headlineDao.findPageCount(headlineQueryVo);

        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;

        Map pageInfo = new HashMap();
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalSize",totalSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("pageData",pageData);
        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(int hid) {
        //�޸ĸ�ͷ��������� +1
        headlineDao.incrPageViews(hid);
        //��ѯͷ��������
        return headlineDao.findHeadlineDetail(hid);

    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return headlineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findByHid(Integer hid) {
        return headlineDao.findByHid(hid);
    }

    @Override
    public int update(NewsHeadline newsHeadline) {
        return headlineDao.update(newsHeadline);
    }

    @Override
    public int removeByHid(int hid) {
        return headlineDao.removeByHid(hid);
    }
}
