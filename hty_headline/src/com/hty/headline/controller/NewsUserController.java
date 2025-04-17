package com.hty.headline.controller;

import com.alibaba.druid.sql.parser.Token;
import com.hty.headline.common.Result;
import com.hty.headline.common.ResultCodeEnum;
import com.hty.headline.pojo.NewsUser;
import com.hty.headline.service.NewsUserService;
import com.hty.headline.service.impl.NewsUserServiceImpl;
import com.hty.headline.util.JwtUtil;
import com.hty.headline.util.MD5Util;
import com.hty.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{

    private NewsUserService userService=new NewsUserServiceImpl();//������������


    /**
     * ǰ���Լ�У���Ƿ�ʧȥ��½״̬�Ľӿ�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");

        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if (null!=token){
            if (!JwtUtil.isExpiration(token)){
                result=Result.ok(null);
            }
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * ���ע���ҵ��ӿ�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //����JSON��Ϣ
        NewsUser registUser = WebUtil.readJson(req, NewsUser.class);

        //���÷���㽫�û���Ϣ��������
        Integer rows = userService.registUser(registUser);//Ӱ�����ݿ��¼������

        //���ݴ����Ƿ�ɹ�������Ӧֵ
        Result result = Result.ok(null);
        if (rows==0){
            result=Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * У���û����Ƿ�ռ�õ�ҵ��ӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ��ȡ�˺�
        String username = req.getParameter("username");

        //�����û�����ѯ�û���Ϣ �ҵ��˷���505 �Ҳ�������200
        NewsUser newsUser = userService.findByUsername(username);
        Result result = Result.ok(null);
        if (null!=newsUser){
            result=Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * ����token��ȡ�û���Ϣ�Ľӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ�����token
        String token = req.getHeader("token");


        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if (null!= token && (!"".equals(token))){//token�Ƿ�Ϊ��
            if (!JwtUtil.isExpiration(token)) {//token�Ƿ����
               Integer userId = JwtUtil.getUserId(token).intValue();//��ȡ�û�id
               NewsUser newsUser =  userService.findById(userId);//�����û�id��ѯ�û���Ϣ
               if (null!=newsUser){
                   //ͨ��У�� ��ѯ�û���Ϣ����Result
                   Map data = new HashMap();
                   newsUser . setUserPwd("");
                   data.put("loginUser",newsUser);

                   result = Result.ok(data);
               }
            }
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * �����¼���ύ��ҵ��ӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //�����û���������
        /*
        {
        "username":"zhangsan",�û���
        "userPwd":"123456"//��������
        }
         */
         NewsUser paramUser =  WebUtil.readJson(req, NewsUser.class);//�������л�ȡJSON����ת��Ϊ�û���Ϣ����

        //���÷���㷽�� ʵ�ֵ�¼
        NewsUser loginUser = userService.findByUsername(paramUser.getUsername());//�����û�����ѯ�û���Ϣ
        Result result = null;//��װ���ؽ��
        if (null!=loginUser){//�û�����
            if (MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())){
                //����������ܳ���������                      �����ִ�Сд                ��������

                Map data = new HashMap();
                data.put("token",JwtUtil.createToken(loginUser.getUid().longValue()));
                                          //�����û�id����token
                result=Result.ok(data);//����token��ֵ��
            }else {
                result=Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        }else {
            result=Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }


        //��ͻ�����Ӧ��½��֤��Ϣ
        WebUtil.writeJson(resp,result);
    }
}
