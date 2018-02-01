package io.microsamples.cache.facade.account;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Source.class)
@Profile("rabbit")
@RestController
public class ManageAccountController {

    private Source accountQueueSource;

    public ManageAccountController(Source accountQueueSource) {
        this.accountQueueSource = accountQueueSource;
    }

    @PostMapping(value = "/accounts"
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public ResponseEntity<Account> requestAccountCreation(@RequestBody Account account){
        Account requested = Account.builder().cif(account.getCif()).description(account.getDescription()).build();
        accountQueueSource.output().send(MessageBuilder.withPayload(requested).build());
        return new ResponseEntity<>(requested, HttpStatus.CREATED);
    }

}
