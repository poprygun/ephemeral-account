package io.microsamples.cache.reader.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTests {

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void shouldReturnAccountForCif() {
        String expected = "xyz2";
        List<Account> returnedAccounts = Collections.singletonList(Account.builder().cif(expected).build());

        Mockito.when(accountRepository.findByCif(expected)).thenReturn(returnedAccounts);

        Account[] accounts = given().standaloneSetup(new AccountController(accountRepository))
                .params("cif", expected)
                .log().all()
                .when()
                .get("accounts")
                .as(Account[].class);

        assertThat(Arrays.asList(accounts), hasSize(1));
        assertThat(accounts[0].getCif(), is(expected));

    }


}