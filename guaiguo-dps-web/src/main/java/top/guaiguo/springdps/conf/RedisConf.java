package top.guaiguo.springdps.conf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author zhangzy
 * @version 1.0.0
 * @createTime 2020/12/19
 * @Description
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConf {

    @Getter
    @Setter
    private String host;
    @Getter
    @Setter
    private int port;

    @Bean
    public RedisTemplate<?, ?> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }

    @Bean
    public RedissonClient redissonClient() {
        try {
            log.info(" RedissonClient init ");
            Config config = new Config();
            long lockWatchdogTimeout = 60000L;
            // 每隔 lockWatchdogTimeout/3 ms 为lock续时一次
            config.setLockWatchdogTimeout(lockWatchdogTimeout)
                    .useSingleServer()
                    .setAddress("redis://" + host + ":" + port);
            return Redisson.create(config);
        } catch (Exception e) {
            log.error(" RedissonClient init error ", e);
        }
        return null;
    }
}
