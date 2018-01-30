package io.microsamples.cache.facade.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompositeReadAccountServiceTest {

    @Autowired
    private CompositeReadAccountService compositeReadAccountService;

    @Test
    public void testCB() {
        compositeReadAccountService.findByCif("123");
    }
}