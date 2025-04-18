package com.hty.headline.util;
import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;
import java.util.Date;

//基于 JSON Web Token (JWT) 的工具类，用于生成、解析和验证 JWT 令牌
//帮助生成token
public class JwtUtil {
    private static long tokenExpiration = 1000*60*60;//定义token的过期时间
    private static String tokenSignKey = "123456";//token签名密钥
    //生成token字符串
    public static String createToken(Long userId) {
        String token = Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)//token中要存储用户的信息，这里存储userId
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }
    //从token字符串获取userid
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }
    //判断token是否有效
    public static boolean isExpiration(String token){
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }
}
