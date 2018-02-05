package io.microsamples.cache.facade.account;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AccountNotificationStrategyTests {

    @Test
    public void accountNotificationServiceCalledWhenAccountReadFromSignatureService() {

        RestTemplate signatureRestTemplateMock = mock(RestTemplate.class);
        AccountNotificationStrategy accountNotificationStrategy = mock(AccountNotificationStrategy.class);
        CompositeReadAccountService compositeReadAccountService = new CompositeReadAccountService("n/a",
                signatureRestTemplateMock, "n/a", null, accountNotificationStrategy);
        Mockito.when(signatureRestTemplateMock.getForObject(anyString(), eq(Account[].class)))
                .thenReturn(new Account[] {Account.builder().cif("cif").description("desc").build()});
        compositeReadAccountService.setSignatureTemplate(signatureRestTemplateMock);


        compositeReadAccountService.findByCif("cif");


        verify(accountNotificationStrategy, times(1)).notify(any(Account.class));
    }

}