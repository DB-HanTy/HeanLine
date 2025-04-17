package com.hty.headline.dao;

import com.hty.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /*
    *查询所有头条类型的接口
    *
    * */
    List<NewsType> findAll();
}
