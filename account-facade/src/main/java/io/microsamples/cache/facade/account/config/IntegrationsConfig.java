package io.microsamples.cache.facade.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IntegrationsConfig {
    @Bean
    public RestTemplate signatureTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate accountCacheTemplate() {
        return new RestTemplate();
    }


}
