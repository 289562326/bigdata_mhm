package com.mhm.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHm on 2019/7/2.
 */
public class JwtUtil {

    /**
     * token秘钥, 解密用户
     */
    public static final String SECRET = "asdfghjkl";
    /**
     * token 过期时间: 60s
     */
    public static final int calendarField = Calendar.SECOND;
    public static final int calendarInterval = 600;

    public static String createToken(String appkey) {
        Date now = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();
        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create().withIssuedAt(now)// sign time
        .withExpiresAt(expiresDate) // expire time
        .withClaim("appkey", appkey)//保存身份标识，可以使用userid,username
        .sign(Algorithm.HMAC256(SECRET)); // signature
        return token;
    }

    //    public static Map<String,Claim> varifyToken(String token){
    //        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
    //        DecodedJWT decodedJWT =jwtVerifier.verify(token);
    //        return decodedJWT.getClaims();
    //    }

    public static String varifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("appkey").asString();
    }

    public static void main(String[] args) {
        String token = createToken("mhm");
        System.out.println(token);
        System.out.println(varifyToken(token));
    }
}
