package cn.ocoop.framework.esign;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AccessTokenService {
    @Autowired
    EsignProperties config;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RedissonClient redissonClient;

    public String get() {
        String accessTokenKey = EsignProperties.PREFIX + ":" + config.getApiPrefix() + ":access_token";

        String existAccessToken = redisTemplate.opsForValue().get(accessTokenKey);
        if (existAccessToken != null) return existAccessToken;

        RLock lock = redissonClient.getLock("_lock:" + accessTokenKey);
        lock.lock();

        existAccessToken = redisTemplate.opsForValue().get(accessTokenKey);
        if (existAccessToken != null) return existAccessToken;

        try {
            AccessTokenResponse token = EsignRestTemplateHelper.getTemplate().getForObject(
                    config.api("/v1/oauth2/access_token?appId={appid}&secret={secret}&grantType=client_credentials"),
                    AccessTokenResponse.class,
                    config.getAppId(),
                    config.getSecret()
            );
            redisTemplate.opsForValue().set(accessTokenKey, token.getData().getToken(), 300, TimeUnit.SECONDS);
            return token.getData().getToken();
        } finally {
            lock.unlock();
        }
    }

}
