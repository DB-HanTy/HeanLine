package com.hty.headline.service;

import com.hty.headline.pojo.NewsUser;

public interface NewsUserService {

    /*
    * �����û���¼���˺����û��µķ���
    * @param newsUser �û�������˻�
    * @return �ҵ�����NewsUser�����Ҳ�������null
    * */
    NewsUser findByUsername(String username);

    /*
    * �����û�id���û�
    * @param userId �û�id
    * @return �ҵ�����NewsUser�����Ҳ�������null
    * */
    NewsUser findById(Integer userId);

    /**
     * ע���û�
     * @param registUser
     * @return
     */
    Integer registUser(NewsUser registUser);
}
