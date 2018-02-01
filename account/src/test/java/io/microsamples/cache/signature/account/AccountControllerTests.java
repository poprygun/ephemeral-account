package io.microsamples.cache.signature.account;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;


public class AccountControllerTests {

    @Test
    public void shouldRespondWith500() {


        AccountApplication accountApplication = new AccountApplication();
        given()
                .standaloneSetup(accountApplication)
                .params("cif", "whatever")
                .log().all()
                .when()
                .get("accounts")
                .then()
                .statusCode(HttpStatus.OK.value());

        given().standaloneSetup(accountApplication)
                .log().all()
                .when()
                .put("status/" + HttpStatus.SERVICE_UNAVAILABLE.value())
                .then()
                .statusCode(HttpStatus.OK.value());

        given().standaloneSetup(accountApplication)
                .params("cif", "whatever")
                .log().all()
                .when()
                .get("accounts")
                .then()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value());

    }
}
