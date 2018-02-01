package io.microsamples.cache.writer.account;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("gemfire")
public class AccountControllerTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldCacheTheAccount() {
        given().standaloneSetup(new AccountController(accountRepository))
                .body(Account.builder().cif("xyz3").description("description to POST").build())
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("accounts")
                .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract().response();

        Iterable<Account> allAccounts = accountRepository.findAll();
        for (Account next : allAccounts) {
            Assert.assertThat(next.getCif(), is("xyz3"));
        }
    }

    @After
    public void cleanup(){
        accountRepository.deleteAll();
    }

}