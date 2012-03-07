package org.springframework.env.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisRepository {
    private StringRedisTemplate template;

    public RedisRepository() {
    }

    public RedisRepository(StringRedisTemplate template) {
        this.template = template;
    }

    public String getProperty(String name) {
        ValueOperations<String, String> valueOps = template.opsForValue();

        return valueOps.get(name);
    }
}
