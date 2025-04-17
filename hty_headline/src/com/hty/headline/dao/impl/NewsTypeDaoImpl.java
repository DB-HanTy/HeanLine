package com.hty.headline.dao.impl;

import com.hty.headline.dao.BaseDao;
import com.hty.headline.dao.NewsTypeDao;
import com.hty.headline.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao{
    @Override
    public List<NewsType> findAll() {
        String sql = "select tid,tname from news_type";
        return  baseQuery(NewsType.class,sql);
    }
}
