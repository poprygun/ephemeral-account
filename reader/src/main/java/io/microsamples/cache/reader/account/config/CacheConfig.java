package io.microsamples.cache.reader.account.config;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;

@Configuration
@ClientCacheApplication(name = "DataGemFireRestApplication", logLevel = "error")
public class CacheConfig {
    @Bean("Accounts")
    public ClientRegionFactoryBean<Object, Object> accountsRegion(GemFireCache gemfireCache) {

        ClientRegionFactoryBean<Object, Object> peopleRegion = new ClientRegionFactoryBean<>();

        peopleRegion.setCache(gemfireCache);
        peopleRegion.setClose(false);
        peopleRegion.setShortcut(ClientRegionShortcut.LOCAL);

        return peopleRegion;
    }
}
