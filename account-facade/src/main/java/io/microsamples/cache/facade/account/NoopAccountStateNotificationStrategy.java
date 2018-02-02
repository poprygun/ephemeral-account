package io.microsamples.cache.facade.account;

public class NoopAccountStateNotificationStrategy implements AccountNotificationStrategy {
    @Override
    public void notify(Account account) {

    }
}
