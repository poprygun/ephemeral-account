package io.microsamples.cache.reader.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("gemfire")
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void createAccounts() {

        accountRepository.deleteAll();

        String[] cifs = {"xyz1", "xyz2", "xyz3"};

        for (String cif : cifs) {
            Account account = Account.builder().cif(cif).build();
            accountRepository.save(account);
        }
    }

    @Test
    public void shouldReturnAccountByCif() {
        List<Account> accounts = accountRepository.findByCif("xyz2");
        assertThat(accounts, hasSize(1));
    }

    @After
    public void cleanup() {
        accountRepository.deleteAll();
    }


}