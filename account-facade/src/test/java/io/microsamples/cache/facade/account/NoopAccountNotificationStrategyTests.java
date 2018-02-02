package io.microsamples.cache.facade.account;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class NoopAccountNotificationStrategyTests extends AccountStateNotificationStrategyTests {

    @Test
    public void shouldNotifyQueueWhenAmqpIsEnabled(){
        assertThat(accountNotificationStrategy, is(instanceOf(NoopAccountStateNotificationStrategy.class)));
    }

}
