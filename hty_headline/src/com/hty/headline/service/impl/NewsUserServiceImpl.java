package com.hty.headline.service.impl;

import com.hty.headline.dao.NewsUserDao;
import com.hty.headline.dao.impl.NewsUserDaoImpl;
import com.hty.headline.pojo.NewsUser;
import com.hty.headline.service.NewsUserService;
import com.hty.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao=new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public NewsUser findById(Integer userId) {
        return userDao.findById(userId);
    }

    @Override
    public Integer registUser(NewsUser registUser) {
        //处理增加数据的业务
        //将明文密码转换成密文密码
        registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));

        return userDao.insertUser(registUser);
    }
}
