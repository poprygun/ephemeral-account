package io.microsamples.cache.reader.account;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class AccountController {

    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccountsByCif(@RequestParam String cif){

        log.info("Loading data for {}", cif);

        Iterable<Account> accounts = accountRepository.findByCif(cif);

        ArrayList<Account> toReturn = Lists.newArrayList(accounts);

        for (Account account : accounts) {
            log.info("Found in cache {}", account);
        }

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }
}
