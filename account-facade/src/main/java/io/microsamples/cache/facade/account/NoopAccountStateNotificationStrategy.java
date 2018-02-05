package io.microsamples.cache.facade.account;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NoopAccountStateNotificationStrategy implements AccountNotificationStrategy {

    @Override
    public void notify(List<Account> accounts) {
        log.info("Using " + NoopAccountStateNotificationStrategy.class + ", doing nothing");
    }
}
