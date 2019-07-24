import org.junit.Test;
import service.redis.RedisUtil;

import javax.annotation.Resource;

public class RedisUtilTest extends BaseTest {

    @Resource
    private RedisUtil redisUtil;

    @Test
    @Override
    public void test() {
        redisUtil.set("123", "te321st", 60);
        String test = redisUtil.get("123");
        redisUtil.set("123", "te3st", 60);
        String test1 = redisUtil.get("123");
        redisUtil.delete("123");
        String test2 = redisUtil.get("123");
    }
}
