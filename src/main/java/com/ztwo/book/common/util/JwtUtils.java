package com.ztwo.book.common.util;

import com.sun.media.jfxmedia.logging.Logger;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Author ZTwo
 * @Date 2021/12/21 14:58
 */
@Slf4j
@Component
public class JwtUtils {

    private final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.tokenHead}")
    private String TOKEN_PREFIX;
    @Value("${jwt.expiration}")
    private Integer EXPIRATION;


    /**
     * 生成token
     *
     * @param claim 载荷
     */
    public String generateToken(Map<String, Object> claim) {
        String jwt = null;
        try {
            jwt = Jwts.builder()
                    .setClaims(claim)
                    .signWith(ALGORITHM, SECRET)
                    .setExpiration(getExpiration())
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TOKEN_PREFIX + "_" + jwt;
    }

    /**
     * 校验token
     *
     * @param token
     * @return [java.lang.String]
     */
    public Map<String, Object> verifyToken(String token) {
        //判断token是否为空
        if (token == null) {
            throw new RuntimeException("令牌为空");
        }

        try {
            return getClaimFromToken(token);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("令牌已过期");
        } catch (Exception e) {
            throw new RuntimeException("令牌解析异常");
        }
    }

    /**
     * 通过request获取userId
     *
     * @param request
     * @return [javax.servlet.http.HttpServletRequest]
     */
    public Integer getUserIdFromRequest(HttpServletRequest request) {
        Claims claim = null;
        try {
            String token = request.getHeader("Authorization");
            claim = getClaimFromToken(token);
            return (Integer) claim.get("userId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从token获取claim
     *
     * @param token
     * @return [java.lang.String]
     */
    private Claims getClaimFromToken(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replaceFirst(TOKEN_PREFIX + "_", ""))
                .getBody();
    }

    /**
     * 获取有效时间
     */
    private Date getExpiration() {
        return new Date(System.currentTimeMillis() + EXPIRATION);
    }
}
