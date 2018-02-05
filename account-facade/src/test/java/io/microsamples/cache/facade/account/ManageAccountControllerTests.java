package io.microsamples.cache.facade.account;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ManageAccountControllerTests {

/*
    @Test
    public void shouldNotifyOfAccountCreation() {

        String expected = "xyz2";
        Account account = Account.builder().cif(expected).build();

        Source sourceMock = Mockito.mock(Source.class);
        MessageChannel mockMessageChannel = Mockito.mock(MessageChannel.class);
        Mockito.when(sourceMock.output()).thenReturn(mockMessageChannel);

        Account sentAccount = given().standaloneSetup(new ManageAccountController(sourceMock))
                .body(account)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("accounts")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", not(isEmptyString()))
                .extract()
                .as(Account.class);

        assertThat(sentAccount, is(notNullValue()));

        verify(mockMessageChannel, times(1)).send(anyObject());
    }
*/

}
