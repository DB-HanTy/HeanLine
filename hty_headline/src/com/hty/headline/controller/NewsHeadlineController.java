package com.hty.headline.controller;

import com.hty.headline.common.Result;
import com.hty.headline.pojo.NewsHeadline;
import com.hty.headline.service.NewsHeadlineService;
import com.hty.headline.service.impl.NewsHeadlineServiceImpl;
import com.hty.headline.util.JwtUtil;
import com.hty.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController{
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    /**
     * ɾ��ͷ����ҵ��ӿ�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));
        headlineService.remov
     eByHid(hid);

        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * ����ͷ����ҵ��ӿ�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        headlineService.update(newsHeadline);

        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * �޸�ͷ������ҵ��ӿ�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        NewsHeadline headline = headlineService.findByHid(hid);

        Map data = new HashMap();
        data.put("headline", headline);
        WebUtil.writeJson(resp, Result.ok(data));

    }

    /**
     * ����ͷ���Ľӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //���ղ���
        String token = req.getHeader("token");
        Long userId = JwtUtil.getUserId(token);

        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        newsHeadline.setPublisher(userId.intValue());

        //����Ϣ�������ݿ�
        headlineService.addNewsHeadline(newsHeadline);

        WebUtil.writeJson(resp, Result.ok(null));

    }
}
