package service.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value){
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value);
    }

    public void set(String key, String value, long seconds){
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value, seconds, TimeUnit.SECONDS);
    }

    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

}
