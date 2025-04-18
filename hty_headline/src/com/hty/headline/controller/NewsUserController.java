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

    private NewsUserService userService=new NewsUserServiceImpl();//创建服务层对象


    /**
     * 前端自己校验是否失去登陆状态的接口
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
     * 完成注册的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收JSON信息
        NewsUser registUser = WebUtil.readJson(req, NewsUser.class);

        //调用服务层将用户信息存入数据
        Integer rows = userService.registUser(registUser);//影响数据库记录的行数

        //根据存入是否成功处理响应值
        Result result = Result.ok(null);
        if (rows==0){
            result=Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * 校验用户名是否被占用的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取账号
        String username = req.getParameter("username");

        //根据用户名查询用户信息 找到了返回505 找不到返回200
        NewsUser newsUser = userService.findByUsername(username);
        Result result = Result.ok(null);
        if (null!=newsUser){
            result=Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * 根据token获取用户信息的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的token
        String token = req.getHeader("token");


        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if (null!= token && (!"".equals(token))){//token是否为空
            if (!JwtUtil.isExpiration(token)) {//token是否过期
               Integer userId = JwtUtil.getUserId(token).intValue();//获取用户id
               NewsUser newsUser =  userService.findById(userId);//根据用户id查询用户信息
               if (null!=newsUser){
                   //通过校验 查询用户信息放入Result
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
     * 处理登录表单提交的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户名和密码
        /*
        {
        "username":"zhangsan",用户名
        "userPwd":"123456"//明文密码
        }
         */
         NewsUser paramUser =  WebUtil.readJson(req, NewsUser.class);//从请求中获取JSON串并转换为用户信息对象

        //调用服务层方法 实现登录
        NewsUser loginUser = userService.findByUsername(paramUser.getUsername());//根据用户名查询用户信息
        Result result = null;//封装返回结果
        if (null!=loginUser){//用户存在
            if (MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())){
                //明文密码加密成密文密码                      不区分大小写                密文密码

                Map data = new HashMap();
                data.put("token",JwtUtil.createToken(loginUser.getUid().longValue()));
                                          //根据用户id生成token
                result=Result.ok(data);//返回token键值对
            }else {
                result=Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        }else {
            result=Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }


        //向客户端响应登陆验证信息
        WebUtil.writeJson(resp,result);
    }
}
