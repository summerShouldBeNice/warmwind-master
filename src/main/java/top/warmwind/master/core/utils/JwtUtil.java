package top.warmwind.master.core.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import top.warmwind.master.core.constants.SysConstants;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JWT工具类
 *
 * @author warmwind
 * @since 2024-11-01 上午10:23
 */
public class JwtUtil <T extends JwtSubject> {

    /**
     * 从请求中获取访问令牌
     *
     * @param request   HttpServletRequest
     * @return String
     */
    public static String getAccessToken(HttpServletRequest request) {
        String accessToken = JakartaServletUtil.getHeaderIgnoreCase(request, SysConstants.TOKEN_HEADER_NAME);
        if (StrUtil.isNotBlank(accessToken)) {
            if (accessToken.startsWith(SysConstants.TOKEN_TYPE)) {
                accessToken = StrUtil.removePrefix(accessToken, SysConstants.TOKEN_TYPE).trim();
            }
        } else {
            accessToken = request.getParameter(SysConstants.TOKEN_PARAM_NAME);
        }
        return accessToken;
    }

    /**
     * 生成token
     *
     * @param issuer    签发人
     * @param t         载体
     * @param expire    过期时间
     * @param base64EncodedKey   base64编码的Key
     * @return
     * @param <T>
     */
    public static <T> String buildToken(String issuer,  T t, Long expire, String base64EncodedKey) {
        return buildToken(issuer, JSON.toJSONString(t), expire, decodeKey(base64EncodedKey));
    }

    /**
     * 生成token
     *
     * @param issuer  颁发人
     * @param subject 载体
     * @param expire  过期时间
     * @param secretKey     密钥
     * @return token
     */
    public static String buildToken(String issuer, String subject, Long expire, SecretKey secretKey) {
        Date expireDate = new Date(new Date().getTime() + 1000 * expire);
        return Jwts.builder()
                .header()
                .keyId(UUID.randomUUID().toString())
                .and()
                .issuer(issuer)
                .setSubject(subject)
                .setExpiration(expireDate)
                .setIssuedAt(new Date())
                .signWith(secretKey)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token            token
     * @param base64EncodedKey base64编码的Key
     * @return Claims
     */
    public static Claims parseToken(String token, String base64EncodedKey) {
        return parseToken(token, decodeKey(base64EncodedKey));
    }

    /**
     * 解析token
     *
     * @param token token
     * @param key   密钥
     * @return Claims
     */
    public static Claims parseToken(String token, SecretKey key) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 获取JwtSubject
     *
     * @param claims Claims
     * @return JwtSubject
     */
    public static <T extends JwtSubject> T getJwtSubject(Claims claims, Class<T> clazz) {
        return JSON.parseObject(claims.getSubject(), clazz);
    }

    /**
     * 生成Key
     *
     * @return Key
     */
    public static SecretKey randomKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    /**
     * base64编码key
     *
     * @return String
     */
    public static String encodeKey(SecretKey secretKey) {
        return Encoders.BASE64.encode(secretKey.getEncoded());
    }

    /**
     * base64编码Key
     *
     * @param base64EncodedKey base64编码的key
     * @return Key
     */
    public static SecretKey decodeKey(String base64EncodedKey) {
        if (StrUtil.isBlank(base64EncodedKey)) {
            return null;
        }
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedKey));
    }
}
