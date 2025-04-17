package com.hty.headline.test;

import com.hty.headline.util.JwtUtil;
import org.junit.Test;

public class TestJwtHelper {
    @Test
    public void testAllMethod() throws InterruptedException {
       String token = JwtUtil.createToken(1L);//生成token

        System.out.println(token);//

        Long userId = JwtUtil.getUserId(token);//获取userid

        System.out.println(JwtUtil.isExpiration(token));//判断是否过期

        Thread.sleep(6000);

        System.out.println(JwtUtil.isExpiration(token));


    }
}
