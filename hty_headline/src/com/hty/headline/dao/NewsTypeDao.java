package com.hty.headline.dao;

import com.hty.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /*
    *��ѯ����ͷ�����͵Ľӿ�
    *
    * */
    List<NewsType> findAll();
}
