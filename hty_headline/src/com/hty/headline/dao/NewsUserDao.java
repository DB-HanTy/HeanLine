package com.hty.headline.dao;

import com.hty.headline.pojo.NewsUser;

public interface NewsUserDao {

    /**
     * �����û�����ѯ�û�
     * @param username
     * @return
     */
    NewsUser findByUsername(String username);

    /**
     * ����id��ѯ�û�
     * @param userId
     * @return
     */
    NewsUser findById(Integer userId);

    /**
     * ����û�
     * @param registUser
     * @return
     */
    Integer insertUser(NewsUser registUser);
}
