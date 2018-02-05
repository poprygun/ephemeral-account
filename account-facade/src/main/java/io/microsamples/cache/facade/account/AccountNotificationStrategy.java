package io.microsamples.cache.facade.account;

import java.util.List;

public interface AccountNotificationStrategy {

    void notify(List<Account> accounts);

}
