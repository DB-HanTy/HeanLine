package com.hty.headline.test;

import com.hty.headline.util.JwtUtil;
import org.junit.Test;

public class TestJwtHelper {
    @Test
    public void testAllMethod() throws InterruptedException {
       String token = JwtUtil.createToken(1L);//����token

        System.out.println(token);//

        Long userId = JwtUtil.getUserId(token);//��ȡuserid

        System.out.println(JwtUtil.isExpiration(token));//�ж��Ƿ����

        Thread.sleep(6000);

        System.out.println(JwtUtil.isExpiration(token));


    }
}
