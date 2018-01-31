package io.microsamples.cache.writer.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
public class AccountController {

    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping(value = "/accounts", consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Account>> getAccountsByCif(@RequestBody Account account) {
        Account createdAccount = accountRepository.save(account);
        log.info("Passed account {}, created account {}", account, createdAccount);


        Iterable<Account> all = accountRepository.findAll();
        for (Account next : all) {
            log.info("Saved ==> {}", next);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
