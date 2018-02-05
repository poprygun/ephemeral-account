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
    public void notify(Account account) {
        accountQueueSource.output().send(MessageBuilder.withPayload(account).build());
        log.info("Notified AMQP about the accounts read");
    }

}
