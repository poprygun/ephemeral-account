package io.microsamples.cache.facade.account.config;

import io.microsamples.cache.facade.account.AccountNotificationStrategy;
import io.microsamples.cache.facade.account.CompositeReadAccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IntegrationsConfig {

    @Value("${account.cache.reader:https://reader.cfapps.io}")
    private String cacheServiceUrl;

    @Value("${account.signature.service:https://account-signature.cfapps.io}")
    private String signatureServiceUrl;

    @Bean
    public CompositeReadAccountService compositeReadAccountService(AccountNotificationStrategy accountNotificationStrategy) {
        return new CompositeReadAccountService(signatureServiceUrl, new RestTemplate(),
                cacheServiceUrl, new RestTemplate(), accountNotificationStrategy);
    }

}
