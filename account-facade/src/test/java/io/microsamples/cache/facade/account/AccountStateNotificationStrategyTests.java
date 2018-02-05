package io.microsamples.cache.facade.account;

import io.microsamples.cache.facade.account.config.AccountNotificationStrategyConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = {
        AccountNotificationStrategyConfig.class
})
@RunWith(SpringRunner.class)
public abstract class AccountStateNotificationStrategyTests {

    @Autowired
    protected AccountNotificationStrategy accountNotificationStrategy;

}
