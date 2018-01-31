package io.microsamples.cache.writer.account.config;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import io.microsamples.cache.writer.account.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


@Configuration
public class CacheConfig {

    @Configuration
    @Profile("!redis")
    @ClientCacheApplication(name = "DataGemFireRestApplication", logLevel = "error")
    @EnableGemfireRepositories(basePackageClasses = AccountRepository.class)
    static class GemfireConfig {
        @Bean("Accounts")
        public ClientRegionFactoryBean<Object, Object> accountsRegion(GemFireCache gemfireCache) {

            ClientRegionFactoryBean<Object, Object> peopleRegion = new ClientRegionFactoryBean<>();

            peopleRegion.setCache(gemfireCache);
            peopleRegion.setClose(false);
            peopleRegion.setShortcut(ClientRegionShortcut.LOCAL);

            return peopleRegion;
        }
    }

    @Configuration
    @Profile("redis")
    @EnableRedisRepositories(basePackageClasses = AccountRepository.class)
    static class RedisConfig {

        @Bean
        RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {

            RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
            template.setConnectionFactory(connectionFactory);
//            template.setKeySerializer(new Jackson2JsonRedisSerializer<Object>());
            return template;
        }

        @Bean
        public RedisConnectionFactory jedisConnectionFactory() {
            JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
            jedisConFactory.setHostName("localhost");
            jedisConFactory.setPort(6379);
            return jedisConFactory;

        }
    }
}

