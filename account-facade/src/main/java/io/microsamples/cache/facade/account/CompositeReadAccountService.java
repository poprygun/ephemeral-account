package io.microsamples.cache.facade.account;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class CompositeReadAccountService {

    @Autowired
    private RestTemplate signatureTemplate;

    @Autowired
    private RestTemplate accountCacheTemplate;

    @Value("${account.cache.reader:https://reader.cfapps.io}")
    private String cacheServiceUrl;

    @Value("${account.signature.service:https://account-signature.cfapps.io}")
    private String signatureServiceUrl;


    public void setSignatureTemplate(RestTemplate signatureTemplate) {
        this.signatureTemplate = signatureTemplate;
    }

    public void setAccountCacheTemplate(RestTemplate accountCacheTemplate) {
        this.accountCacheTemplate = accountCacheTemplate;
    }

    @HystrixCommand(fallbackMethod = "accountsFromCacheByCif")
    public List<Account> findByCif(final String cif) {

        String transactionUrl = signatureServiceUrl.concat("/accounts");

        return getAccounts(cif, transactionUrl, signatureTemplate);
    }

    public List<Account> accountsFromCacheByCif(final String cif) {

        String transactionUrl = cacheServiceUrl.concat("/accounts");

        return getAccounts(cif, transactionUrl, accountCacheTemplate);


    }

    private List<Account> getAccounts(String cif, String transactionUrl, RestTemplate toCall) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(transactionUrl)
                .queryParam("cif", cif);

        Account[] accounts = toCall.getForObject(builder.toUriString(), Account[].class);

        return Arrays.asList(accounts);
    }


}
