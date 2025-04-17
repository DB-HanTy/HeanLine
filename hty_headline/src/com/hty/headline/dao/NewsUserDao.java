package com.hty.headline.dao;

import com.hty.headline.pojo.NewsUser;

public interface NewsUserDao {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    NewsUser findByUsername(String username);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    NewsUser findById(Integer userId);

    /**
     * 添加用户
     * @param registUser
     * @return
     */
    Integer insertUser(NewsUser registUser);
}
