package org.springframework.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.env.redis.RedisRepository;

@Configuration
public class PropertySourceConfig {
    @Bean
    public org.springframework.core.env.PropertySource redisPropertySource() {
        return new RedisPropertySource(redisRepository());
    }

    @Bean
    public RedisRepository redisRepository() {
        return new RedisRepository(redisTemplate());
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        return new StringRedisTemplate(redisConnection());
    }

    @Bean
    public RedisConnectionFactory redisConnection() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        // connectionFactory.setHostName("");
        // connectionFactory.setPort(0);
        // connectionFactory.setPassword("");
        return connectionFactory;
    }
}
