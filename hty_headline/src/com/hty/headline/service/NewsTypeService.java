package com.hty.headline.service;

import com.hty.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeService {

    /*
    * ��ѯ����ͷ�����͵ķ���
    * @return ���ͷ��������List<NewsType>������ʽ����
    * */
    List<NewsType> findAll();
}
