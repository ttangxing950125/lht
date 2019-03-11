package com.bth.lht.util;

import com.aliyuncs.utils.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bth.lht.exception.ResponseException;
import org.springframework.http.HttpStatus;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: lht
 * @description: token工具类
 * @author: Antony
 * @create: 2018-12-21 12:47
 **/
public class TokenUtil {
    public static final String SECRET = "LIGHT-HOUSE-TOKEN-SECRET";
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    public static String createToken(String  openid)  {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = null; // signature
        token = JWT.create().withHeader(map)
                .withClaim("userOpenid", openid)
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt= verifier.verify(token);
        return jwt.getClaims();
    }

    /**
     * 根据Token获取用户OpenId
     *
     * @param token
     * @return 用户用户Openid
     */
    public static String getUserOpenidByToken(String token) {
        Map<String, Claim> claims = null;
        try {
            claims = verifyToken(token);
        } catch (Exception e) {
            throw new ResponseException(HttpStatus.UNAUTHORIZED.value(), "token校验失败");
        }
        Claim userOpenid = claims.get("userOpenid");
        if (null == userOpenid || StringUtils.isEmpty(userOpenid.asString())) {
            throw new ResponseException(HttpStatus.UNAUTHORIZED.value(), "token校验失败");
        }
        return userOpenid.asString();
    }
}
