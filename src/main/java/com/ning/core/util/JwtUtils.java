package com.ning.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ning.core.exception.TokenUnavailable;

import java.util.Calendar;
import java.util.Date;

/**
 * jwt token工具类
 * jwt的claim里一般包含以下几种数据:
 * 1. iss -- token的发行者
 * 2. sub -- 该JWT所面向的用户
 * 3. aud -- 接收该JWT的一方
 * 4. exp -- token的失效时间
 * 5. nbf -- 在此时间段之前,不会被处理
 * 6. iat -- jwt发布时间
 * 7. jti -- jwt唯一标识,防止重复使用
 */
public class JwtUtils {

    /**
     * 创建令牌
     *
     * @param userId
     * @param realName
     * @param userName
     * @return
     */
    public static String createToken(String userId, String realName, String userName) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(userId) // 签发对象
                .withIssuedAt(new Date()) // 发行时间
                .withExpiresAt(expiresDate) // 有效时间
                .withClaim("userName", userName) // 载荷，随便写几个都可以
                .withClaim("realName", realName)
                .sign(Algorithm.HMAC256(userId + "SSM_SPRINGBOOT_QUICKSTART")); // 加密
    }

    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     *
     * @param token
     * @param secret
     * @throws TokenUnavailable
     */
    public static void verifyToken(String token, String secret) throws TokenUnavailable {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret + "SSM_SPRINGBOOT_QUICKSTART")).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // 效验失败
            // 这里抛出的异常是我自定义的一个异常，你也可以写成别的
            throw new TokenUnavailable();
        }
    }

    /**
     * 获取签发对象
     *
     * @param token
     * @return
     * @throws TokenUnavailable
     */
    public static String getAudience(String token) throws TokenUnavailable {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            // 这里是token解析失败
            throw new TokenUnavailable();
        }
        return audience;
    }

    /**
     * 通过载荷名字获取载荷的值
     *
     * @param token
     * @param name
     * @return
     */
    public static Claim getClaimByName(String token, String name) {
        return JWT.decode(token).getClaim(name);
    }

}
