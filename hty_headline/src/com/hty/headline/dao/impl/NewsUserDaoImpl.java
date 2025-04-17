package com.hty.headline.dao.impl;

import com.hty.headline.dao.BaseDao;
import com.hty.headline.dao.NewsUserDao;
import com.hty.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser findByUsername(String username) {
        String sql = """ 
                select
                     uid,
                     username,
                     user_pwd userPwd,
                     nick_name nickName
                from 
                     news_user
                where
                    username = ?
                
                """;
        List<NewsUser> newsUserList =baseQuery(NewsUser.class,sql,username);
                                       //�����Ĳ�ѯ����  ���ص��Ƕ���ļ���
        return newsUserList != null && newsUserList.size()>0?newsUserList.get(0) : null;
    }

    @Override
    public NewsUser findById(Integer userId) {
        String sql = """ 
                select
                     uid,
                     username,
                     user_pwd userPwd,
                     nick_name nickName
                from 
                     news_user
                where
                    uid = ?
                
                """;
        List<NewsUser> newsUserList =baseQuery(NewsUser.class,sql,userId);
        //�����Ĳ�ѯ����  ���ص��Ƕ���ļ���
        return newsUserList != null && newsUserList.size()>0?newsUserList.get(0) : null;
    }

    @Override
    public Integer insertUser(NewsUser registUser) {
        String sql = """ 
                insert into news_user values (DEFAULT,?,?,?)
                """;

        return baseUpdate(sql,
                registUser.getUsername(),
                registUser.getUserPwd(),
                registUser.getNickName()
                );
    }
}
