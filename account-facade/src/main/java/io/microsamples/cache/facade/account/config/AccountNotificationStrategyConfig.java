package io.microsamples.cache.facade.account.config;

import io.microsamples.cache.facade.account.AccountNotificationStrategy;
import io.microsamples.cache.facade.account.AmqpAccountStateNotificationStrategy;
import io.microsamples.cache.facade.account.NoopAccountStateNotificationStrategy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AccountNotificationStrategyConfig {

    @Configuration
    @Profile("rabbit")
    @EnableBinding(Source.class)
    public static class AmqpConfig {
        @Bean
        public AccountNotificationStrategy amqpNotificationStrategy(Source accountQueueSource) {
            return new AmqpAccountStateNotificationStrategy(accountQueueSource);
        }
    }

    @Bean
    @Profile("!rabbit")
    public AccountNotificationStrategy noopNotificationStrategy() {
        return new NoopAccountStateNotificationStrategy();
    }

}
