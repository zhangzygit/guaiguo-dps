package top.guaiguo.springdps.self.redis;

import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzy
 * @version 1.0.0
 * @createTime 2020/12/19
 * @Description
 */
@Service
public class RedissonSelf {

    @Autowired
    private RedissonClient redissonClient;

    public void test() {
        RMapCache<String, String> demo = redissonClient.getMapCache("demo");
        demo.put("mapcache", "mapcache", 1, TimeUnit.MINUTES);
    }
}
