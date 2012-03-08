package org.springframework.env.redis;

import org.springframework.core.env.PropertySource;

public class RedisPropertySource extends PropertySource<RedisRepository> {
    public static final String REDIS_PROPERTY_SOURCE_NAME = "redis";

    private RedisRepository repository;

    public RedisPropertySource(String name, RedisRepository source) {
        super(name, source);
        this.repository = source;
    }

    public RedisPropertySource(RedisRepository source) {
        this(REDIS_PROPERTY_SOURCE_NAME, source);
    }

    @Override
    public Object getProperty(String name) {
        try {
            return repository.getProperty(name);
        }
        catch (Exception e) {
            logger.error("Error accessing properties from Redis: " + e.getMessage());
            return null;
        }
    }
}
