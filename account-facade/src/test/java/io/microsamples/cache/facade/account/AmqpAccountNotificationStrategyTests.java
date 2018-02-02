package io.microsamples.cache.facade.account;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@ActiveProfiles("rabbit")
public class AmqpAccountNotificationStrategyTests extends AccountStateNotificationStrategyTests {

    @Test
    public void shouldNotifyQueueWhenAmqpIsEnabled(){
        assertThat(accountNotificationStrategy, is(instanceOf(AmqpAccountStateNotificationStrategy.class)));
    }

}
