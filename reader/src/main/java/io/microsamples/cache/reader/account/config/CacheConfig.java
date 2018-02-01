package io.microsamples.cache.reader.account.config;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import io.microsamples.cache.reader.account.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@Configuration
public class CacheConfig {

    @Configuration
    @Profile("gemfire")
    @ClientCacheApplication(name = "DataGemFireRestApplication", logLevel = "error")
    @EnableGemfireRepositories(basePackageClasses = AccountRepository.class)
    public static class GemfireConfig {
        @Bean("Accounts")
        public ClientRegionFactoryBean<Object, Object> accountsRegion(GemFireCache gemfireCache) {
            ClientRegionFactoryBean<Object, Object> accountsRegion = new ClientRegionFactoryBean<>();

            accountsRegion.setCache(gemfireCache);
            accountsRegion.setClose(false);
            accountsRegion.setShortcut(ClientRegionShortcut.LOCAL);

            return accountsRegion;
        }
    }


    @Configuration
    @Profile("redis")
    @EnableRedisRepositories(basePackageClasses = AccountRepository.class)
    public static class RedisConfig {
    }
}


