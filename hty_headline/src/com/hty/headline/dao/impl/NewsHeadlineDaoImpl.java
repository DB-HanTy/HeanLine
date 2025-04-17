package com.hty.headline.dao.impl;

import com.hty.headline.dao.BaseDao;
import com.hty.headline.dao.NewsHeadlineDao;
import com.hty.headline.pojo.NewsHeadline;
import com.hty.headline.pojo.vo.HeadlineDetailVo;
import com.hty.headline.pojo.vo.HeadlinePageVo;
import com.hty.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {
    /**
     *     private Integer hid;
     *     private String title;
     *     private Integer type;
     *     private Integer pageViews;
     *     private Long pastHours;
     *     private Integer publisher;
     *
     *     private String keyWords;
     *     private Integer type ;
     *     private Integer pageNum;
     *     private Integer pageSize;
     */
    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = """
                select 
                    hid,
                    title,
                    type,
                    page_views pageViews,
                    TIMESTAMPDIFF(HOUR,create_time,now()) pastHours ,
                    publisher
                from 
                    news_headline
                where 
                    is_deleted = 0
                """;
        if (headlineQueryVo.getType()!=0){//判断是否为微头条
            sql=sql.concat(" and type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords()!=null && !headlineQueryVo.getKeyWords().equals("")){//判断是否为搜索
            sql=sql.concat(" and title like ? ");
            params.add("%"+headlineQueryVo.getKeyWords()+"%");
        }

        sql=sql.concat(" order by pastHours ASC , page_views DESC ");//按照时间和浏览量排序
        sql=sql.concat(" limit ? , ? ");//分页
        params.add((headlineQueryVo.getPageNum()-1)*headlineQueryVo.getPageSize());//添加分页参数
        params.add(headlineQueryVo.getPageSize());

        return baseQuery(HeadlinePageVo.class,sql,params.toArray());
    }

    @Override
    public int findPageCount(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = """
                select 
                    count(1)
                from 
                    news_headline
                where 
                    is_deleted = 0
                """;
        if (headlineQueryVo.getType()!=0){//判断是否为微头条
            sql=sql.concat(" and type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords()!=null && !headlineQueryVo.getKeyWords().equals("")){//判断是否为搜索
            sql=sql.concat(" and title like ? ");
            params.add("%"+headlineQueryVo.getKeyWords()+"%");
        }

        Long count = baseQueryObject(Long.class,sql,params.toArray());
        return count.intValue();
    }

    @Override
    public int incrPageViews(int hid) {
        String sql = """
                update 
                    news_headline
                set 
                    page_views = page_views + 1
                where 
                    hid = ?
                """;
        return baseUpdate(sql,hid);
    }

    /**
     *           hid;
     *         title;
     *       article;
     *           type;
     *       typeName;
     *      pageViews;
     *      pastHours;
     *      publisher;
     *         author;
     */
    @Override
    public HeadlineDetailVo findHeadlineDetail(int hid) {
        String sql = """
                select 
                 h.hid         hid,
                 h.title       title,
                 h.article     article,
                 h.type        type,
                 t.tname       typeName,
                 h.page_views  pageViews,
                 TIMESTAMPDIFF(HOUR,h.create_time,now()) pastHours,
                 h.publisher   publisher,
                 u.nick_name    author 
                from 
                    news_headline h
                left join 
                    news_type t
                on 
                    h.type=t.tid 
                left join 
                    news_user u
                on  
                    h.publisher = u.uid
                where 
                    h.hid = ? 
                    
                """;
        List<HeadlineDetailVo> list =  baseQuery(HeadlineDetailVo.class,sql,hid);
        return null!= list && list.size()>0?list.get(0) : null;
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        String sql = """
                insert into 
                    news_headline 
                values
                    (DEFAULT,?,?,?,?,0,now(),now(),0)
                """;
        return baseUpdate(sql,
                newsHeadline.getTitle(),
                newsHeadline.getArticle(),
                newsHeadline.getType(),
                newsHeadline.getPublisher()
                );
    }

    @Override
    public NewsHeadline findByHid(Integer hid) {
        String sql = """
                select 
                    hid,
                    title,
                    article,
                    type,
                    publisher,
                    page_views pageViews,
                    create_time createTime,
                    update_time updateTime,
                    is_deleted isDeleted
                from 
                    news_headline
                where 
                    hid = ?
                """;
        List<NewsHeadline> list= baseQuery(NewsHeadline.class,sql,hid);
        return null!= list && list.size()>0?list.get(0) : null;
    }

    @Override
    public int update(NewsHeadline newsHeadline) {
        String sql = """
                update 
                    news_headline
                set 
                    title = ?,
                    article = ?,
                    type = ?,
                    update_time = now() 
                where 
                    hid = ?
                """;
        return baseUpdate(sql,
                newsHeadline.getTitle(),
                newsHeadline.getArticle(),
                newsHeadline.getType(),
                newsHeadline.getHid()
                );
    }

    @Override
    public int removeByHid(int hid) {
        String sql = """
                update 
                    news_headline
                set 
                    is_deleted = 1
                where 
                    hid = ?
                """;
        return baseUpdate(sql,hid);
    }
}
