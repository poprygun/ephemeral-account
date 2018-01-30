package io.microsamples.cache.facade.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompositeReadAccountControllerTests {


    @Autowired
    private CompositeReadAccountService readAccountService;

    private String[] cifsFromSignature = {"xyz2-signature"};
    private String[] cifsFromCache = {"xyz1-cache", "xyz3-cache"};



    private RestTemplate createMockRestTemplate(List<Account> accounts) {
        RestTemplate signatureRestTemplate = mock(RestTemplate.class);
        Mockito.when(signatureRestTemplate.getForObject(anyString(), eq(Account[].class)))
                .thenReturn(accounts.toArray(new Account[accounts.size()]));
        return signatureRestTemplate;
    }


    @Test
    public void shouldReturnAccountForCifIfSignatureIsUp() {
        String expected = cifsFromSignature[0];
        List<Account> accountsFromSignature = Arrays.stream(cifsFromSignature).map(cif -> Account.builder().cif(cif).build())
                .collect(Collectors.toList());

        List<Account> accountsFromCache = Arrays.stream(cifsFromCache).map(cif -> Account.builder().cif(cif).build())
                .collect(Collectors.toList());
        RestTemplate signatureRestTemplate = createMockRestTemplate(accountsFromSignature);
        RestTemplate cacheAccountReadTemplate = createMockRestTemplate(accountsFromCache);

        readAccountService.setAccountCacheTemplate(cacheAccountReadTemplate);
        readAccountService.setSignatureTemplate(signatureRestTemplate);

        Account[] accounts = given().standaloneSetup(new CompositeReadAccountController(readAccountService))
                .params("cif", expected)
                .log().all()
                .when()
                .get("accounts")
                .as(Account[].class);

        assertThat(Arrays.asList(accounts), hasSize(1));
        assertThat(accounts[0].getCif(), is(expected));
        verify(signatureRestTemplate, times(1)).getForObject(anyString(), eq(Account[].class));
        verifyZeroInteractions(cacheAccountReadTemplate);
    }

    @Test
    public void shouldReturnAccountForCifIfSignatureIsDown() {
        List<Account> accountsFromCache = Arrays.stream(cifsFromCache).map(cif -> Account.builder().cif(cif).build())
                .collect(Collectors.toList());

        RestTemplate signatureRestTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(signatureRestTemplate.getForObject(anyString(), eq(Account[].class)))
                .thenThrow(new RestClientException("Signature is down"));

        RestTemplate cacheAccountReadTemplate = createMockRestTemplate(accountsFromCache);

        readAccountService.setAccountCacheTemplate(cacheAccountReadTemplate);
        readAccountService.setSignatureTemplate(signatureRestTemplate);

        Account[] accounts = given().standaloneSetup(new CompositeReadAccountController(readAccountService))
                .params("cif", "xyz1-cache")
                .log().all()
                .when()
                .get("accounts")
                .as(Account[].class);

        assertThat(Arrays.asList(accounts), hasSize(2));
        assertThat(accounts[0].getCif(), is("xyz1-cache"));
        verify(signatureRestTemplate, times(1)).getForObject(anyString(), eq(Account[].class));
        verify(cacheAccountReadTemplate, times(1)).getForObject(anyString(), eq(Account[].class));
    }
}
