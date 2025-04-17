package com.hty.headline.service;

import com.hty.headline.pojo.NewsUser;

public interface NewsUserService {

    /*
    * 根据用户登录的账号找用户新的方法
    * @param newsUser 用户输入的账户
    * @return 找到返回NewsUser对象，找不到返回null
    * */
    NewsUser findByUsername(String username);

    /*
    * 根据用户id找用户
    * @param userId 用户id
    * @return 找到返回NewsUser对象，找不到返回null
    * */
    NewsUser findById(Integer userId);

    /**
     * 注册用户
     * @param registUser
     * @return
     */
    Integer registUser(NewsUser registUser);
}
