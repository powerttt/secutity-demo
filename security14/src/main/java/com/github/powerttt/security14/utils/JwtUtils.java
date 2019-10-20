package com.github.powerttt.security14.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Author tongning
 * @Date 2019/10/19 0019
 * function:<
 * <p>
 * >
 */
public class JwtUtils {

    /**
     * 默认头 default header
     */
    public static final String DEFAULT_HEADER = "{\"alg\":\"HS256\",\"type\":\"JWT\"}";

    /**
     * HmacSHA256 加密算法
     */
    public static final String SECRET = "123456";

    /**
     * token 有效期 1天
     */
    public static final long EXPIRE_TIME = 60 * 60 * 24;

    /**
     * token在header中的名字
     */
    public static final String HEADER_TOKEN_NAME = "Authorization";


    /**
     * base64编码
     */
    public static String encode(String input) {
        return Base64.getUrlEncoder().encodeToString(input.getBytes());
    }

    /**
     * base64解码
     */
    public static String decode(String input) {
        return new String(Base64.getUrlDecoder().decode(input));
    }

    /**
     * HmacSHA256加密算法
     */
    public static String HMACSHA256(String data, String secret) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获取签名
     */
    public static String getSignature(String payload) throws Exception {
        return HMACSHA256(encode(DEFAULT_HEADER) + "." + encode(payload), SECRET);
    }

    /**
     * 验证JWT,正确返回载体数据，错误返回null
     */
    public static String testJwt(String jwt) throws Exception {
        String[] jwts = jwt.split("\\.");

        // 验证签名
        if (!HMACSHA256(jwts[0] + "." + jwts[1], SECRET).equals(jwts[2])) {
            return null;
        }

        // 验证头部信息
        if (!jwts[0].equals(encode(DEFAULT_HEADER))) {
            return null;
        }

        return decode(jwts[1]);
    }


}
