package io.microsamples.cache.facade.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Profile("rabbit")
@RestController
@Slf4j
public class ManageAccountController {

    @PostMapping(value = "/accounts"
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public ResponseEntity<Account> requestAccountCreation(@RequestBody Account account){
        log.info("TODO implement account creation here, returning fake for now");
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}
