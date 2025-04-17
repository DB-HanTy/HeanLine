package com.hty.headline.service.impl;

import com.hty.headline.dao.NewsTypeDao;
import com.hty.headline.dao.impl.NewsTypeDaoImpl;
import com.hty.headline.pojo.NewsType;
import com.hty.headline.service.NewsTypeService;
import com.hty.headline.service.NewsUserService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    private NewsTypeDao typeDao=new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return typeDao.findAll();
    }
}
