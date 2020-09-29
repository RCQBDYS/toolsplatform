package com.lyentech.toolsplatform.utils.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.lyentech.toolsplatform.Analysis.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 672025
 * @date 19:29 2020/9/21
 * @description jwt工具类
 */
@Slf4j
public class JwtUtils {
    /**
     * token过期时间的设定
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    /**
     * 自行设定的密钥
     */
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     *java-jwt 生成token值
     */
    public static String getJwtToken(User user) {
        log.info("生成token值");
//        使用加密算法，并注明
        Algorithm algorithm = Algorithm.HMAC256(APP_SECRET);
//        设置头部信息
        Map<String, Object> map = new HashMap<>(16);
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withHeader(map)
                .withClaim("userAccount", user.getUserAccount())
                .withIssuer("672025")
                .withIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("+8"))))
                .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(60).toInstant(ZoneOffset.of("+8"))))
                .sign(algorithm);
    }

    /**
     *校验token值
     * @param token
     * @return boolean
     */
    public static boolean checkToken(String token){
        try {
            log.info("校验token值");
            Algorithm algorithm = Algorithm.HMAC256(APP_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    // .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("token 无效，请重新获取");
        }
    }

    /**
     *java-jwt 验证token值并返回account值
     */
    public static String checkTokenAccount(String token){
//        long类型值默认初始化值
        log.info("/java-jwt 校验token值并放回账号");
        String userAccount = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256(APP_SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("672025").build();
            DecodedJWT decodedJwt = jwtVerifier.verify(token);
            log.info("token解析"+ decodedJwt.getClaim("userName").asString());
            log.info("token解析"+ decodedJwt.getClaim("userAccount").asString());
            userAccount = decodedJwt.getClaim("userAccount").asString();
        }catch (JWTVerificationException e){
            log.error("解析token失败，exception={}",e.toString());
        }
        return userAccount;
    }


    /**
     *j jwt 生成token值
     */
    public static String  getTwtToken(User user){
        String JwtToken = Jwts.builder()
//                设置头部信息
                .setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")
//                设置失效时间
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
//                设置主体部分，存储用户信息
                .claim("userAccount",user.getUserAccount())
                .claim("userName",user.getUserName())
//                进信信息加密
                .signWith(SignatureAlgorithm.HS256,APP_SECRET)
                .compact();
        return JwtToken;
    }


    /**
     * 判断token是否存在与有效 j jwt
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(jwtToken.isEmpty()) {
                return false;
            }
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token字符串获取用户账户 j jwt
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getParameter("token");
        if(jwtToken.isEmpty()) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userAccount");
    }


}
