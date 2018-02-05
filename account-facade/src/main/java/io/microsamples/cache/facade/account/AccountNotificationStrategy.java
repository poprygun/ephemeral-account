package io.microsamples.cache.facade.account;

import java.util.List;

public interface AccountNotificationStrategy {

    //note: it is Stream Data limitation not being able to consume List<Account> https://github.com/spring-cloud/spring-cloud-stream/issues/726
    void notify(Account account);

}
