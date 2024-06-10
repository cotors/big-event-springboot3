package com.chen.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Cotors
 * @create 2024-03-15-13:51
 */
public class jwtTest {


    @Test
    public void test(){

        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");

        //生成jwt代码
        String token =  JWT.create()
                        .withClaim("user",claims) //添加载荷
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1000*30*60*12)) //添加过期时间
                        .sign(Algorithm.HMAC256("chen")); //指定算法，配置密钥

        System.out.println(token);
    }

    @Test
    public void test01(){
        //根据token解析jwt
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MTA1MDM4Nzh9." +
                "OvVmafHpLtCYSnVIrlFa4OIxx7TwwWrrXv1LDz1tx2k";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("chen")).build();
        DecodedJWT verify = jwtVerifier.verify(token); //验证token，解析一个jwt对象
        Map<String, Claim> claims = verify.getClaims(); //得到所有载荷
        System.out.println(claims.get("user")); //获取指定载荷
    }
}
