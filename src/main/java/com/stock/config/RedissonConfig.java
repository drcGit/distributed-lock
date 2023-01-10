package com.stock.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 丁瑞超
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedissonConfig {
    private String host;
    private String port;
    private String password;

    @Bean
    public RedissonClient getRedisSon() {
        Config config = new Config();
        String address = "redis://" + host + ":" + port;
        config.useSingleServer().setAddress(address);
        if (null != password && !"".equals(password.trim())) {
            config.useSingleServer().setPassword(password);
        }
        return Redisson.create(config);
    }
}
