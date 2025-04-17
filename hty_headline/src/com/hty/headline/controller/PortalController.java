package com.hty.headline.controller;

import com.hty.headline.common.Result;
import com.hty.headline.pojo.NewsType;
import com.hty.headline.pojo.vo.HeadlineDetailVo;
import com.hty.headline.pojo.vo.HeadlineQueryVo;
import com.hty.headline.service.NewsHeadlineService;
import com.hty.headline.service.NewsTypeService;
import com.hty.headline.service.impl.NewsHeadlineServiceImpl;
import com.hty.headline.service.impl.NewsTypeServiceImpl;
import com.hty.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �Ż�������
 * ����Ҫ��¼������Ҫ��ɾ�ĵ��Ż�ҳ�����󶼷����⡣
 * ҳ����ʾ�����ݡ�
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private NewsTypeService typeService = new NewsTypeServiceImpl();
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();


    /**
     * ��ѯͷ�������ҵ��ӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //����Ҫ��ѯͷ����hid
        int hid = Integer.parseInt(req.getParameter("hid"));//�Լ�ֵ�Եķ�ʽ�������л�ȡ����
        //���÷������ɲ�ѯ����
        HeadlineDetailVo headlineDetailVo = headlineService.findHeadlineDetail(hid);
        //����ѯ�����Ӧ���ͻ���
        Map data=new HashMap();
        data.put("headline", headlineDetailVo);
        WebUtil.writeJson(resp,Result.ok(data));
    }


    /**
     * ��ҳ��ѯͷ����Ϣ�Ľӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //���������еĲ���
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);

        //���������ݸ������ ���з�ҳ��ѯ
        /**
         * pageData:[
         *   {
         *       "hid":"1",                        //����id
         *       "title":"����",                   //���ű���
         *       "type":"1",                      //�������������
         *       "pageViews":"40",                //���������
         *       "pastHours":"3",                 //����ʱ���ѹ�Сʱ��
         *       "publisher":"1"
         *   }
         * ],
         * pageNum:1,
         * pageSize:1,
         * totalPage:1,
         * totalSize:1
         *
         */
        Map pageInfo = headlineService.findPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo",pageInfo);

        //����ҳ��ѯ�Ľ��ת����json��Ӧ���ͻ���
        WebUtil.writeJson(resp,Result.ok(data));

    }

    /**
     * ��ѯ�������͵Ľӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ��ѯ���ݿ��е��������ͣ�����װ��Result�����з��ظ��ͻ���
        List<NewsType> newsTypeList = typeService.findAll();

        WebUtil.writeJson(resp,Result.ok(newsTypeList));
    }
}
