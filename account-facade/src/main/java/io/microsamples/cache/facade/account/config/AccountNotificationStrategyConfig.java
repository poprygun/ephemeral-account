package io.microsamples.cache.facade.account.config;

import io.microsamples.cache.facade.account.AccountNotificationStrategy;
import io.microsamples.cache.facade.account.AmqpAccountStateNotificationStrategy;
import io.microsamples.cache.facade.account.NoopAccountStateNotificationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AccountNotificationStrategyConfig {

    @Bean
    @Profile("rabbit")
    public AccountNotificationStrategy amqpNotificationStrategy(){
        return new AmqpAccountStateNotificationStrategy();
    }
    @Bean
    @Profile("!rabbit")
    public AccountNotificationStrategy noopNotificationStrategy(){
        return new NoopAccountStateNotificationStrategy();
    }

}
