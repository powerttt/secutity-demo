package com.github.powerttt.security10.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author tongning
 * @Date 2019/10/11 0011
 * function:<
 * <p>
 * >
 */
@Component
public class RememberMeHandler implements PersistentTokenRepository {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * token有效天数 -  2分钟
     */
    private static final Long TOKEN_VALID_VALUE = 2L;

    /**
     * 生成token key
     *
     * @param series
     * @return
     */
    private String generatorToken(String series) {
        return "spring:security:rememberMe:token:" + series;
    }


    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        String key = generateTokenKey(persistentRememberMeToken.getSeries());
        Map<String, String> map = new HashMap<>(4);
        map.put("data", String.valueOf(persistentRememberMeToken.getDate().getTime()));
        map.put("tokenValue", persistentRememberMeToken.getTokenValue());
        stringRedisTemplate.opsForHash().putAll(key, map);
        stringRedisTemplate.expire(key, TOKEN_VALID_VALUE, TimeUnit.MINUTES);

        saveUsernameAndSeries(persistentRememberMeToken.getUsername(), persistentRememberMeToken.getSeries());
    }

    /**
     * 互相保存，便于查询
     *
     * @param username
     * @param series
     */
    private void saveUsernameAndSeries(String username, String series) {
        stringRedisTemplate.opsForValue().set(generateUsernameAndSeriesKey(username), series, TOKEN_VALID_VALUE, TimeUnit.MINUTES);
        stringRedisTemplate.opsForValue().set(generateUsernameAndSeriesKey(series), username, TOKEN_VALID_VALUE, TimeUnit.MINUTES);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String key = generateTokenKey(series);
        // 判断series是否存在，不存在结束
        Boolean hasSeries = stringRedisTemplate.hasKey(key);
        if (hasSeries == null || hasSeries) {
            return;
        }

        Map<String, String> map = new HashMap<>(4);
        // 更新最后登入时间
        map.put("data", String.valueOf(lastUsed.getTime()));
        map.put("tokenValue", tokenValue);
        stringRedisTemplate.opsForHash().putAll(key, map);
        stringRedisTemplate.expire(key, TOKEN_VALID_VALUE, TimeUnit.MINUTES);

        // 通过series找到username
        String username = stringRedisTemplate.opsForValue().get(generateUsernameAndSeriesKey(series));
        saveUsernameAndSeries(username, series);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String key = generateTokenKey(seriesId);
        Map hashValues = stringRedisTemplate.opsForHash().entries(key);
        if (hashValues == null) {
            return null;
        }
        Object username = hashValues.get("username");
        Object tokenValue = hashValues.get("tokenValue");
        Object data = hashValues.get("data");
        if (username == null || tokenValue == null || data == null) {
            return null;
        }
        long timestamp = Long.valueOf(String.valueOf(data));
        Date time = new Date(timestamp);
        return new PersistentRememberMeToken(String.valueOf(username), seriesId, String.valueOf(tokenValue), time);
    }

    @Override
    public void removeUserTokens(String username) {
        String series = stringRedisTemplate.opsForValue().get(generateUsernameAndSeriesKey(username));
        if (StringUtils.isEmpty(series)) {
            return;
        }
        stringRedisTemplate.delete(generateUsernameAndSeriesKey(username));
        stringRedisTemplate.delete(generateUsernameAndSeriesKey(series));
        stringRedisTemplate.delete(generateTokenKey(series));

    }

    /**
     * 生成token key
     */
    private String generateTokenKey(String series) {
        return "spring:security:rememberMe:token:" + series;
    }

    /**
     * 生成key
     */
    private String generateUsernameAndSeriesKey(String usernameOrSeries) {
        return "spring:security:rememberMe:" + usernameOrSeries;
    }
}
