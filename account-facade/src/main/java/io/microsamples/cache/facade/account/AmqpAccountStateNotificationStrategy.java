package io.microsamples.cache.facade.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

@Slf4j
public class AmqpAccountStateNotificationStrategy implements AccountNotificationStrategy{

    private Source accountQueueSource;

    public AmqpAccountStateNotificationStrategy(Source accountQueueSource) {
        this.accountQueueSource = accountQueueSource;
    }

    @Override
    public void notify(List<Account> accounts) {
        accountQueueSource.output().send(MessageBuilder.withPayload(accounts).build());
        log.debug("Notified AMQP about the accounts read");
    }

}
