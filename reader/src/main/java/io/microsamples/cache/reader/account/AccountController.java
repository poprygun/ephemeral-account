package io.microsamples.cache.reader.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        List<Account> accounts = accountRepository.findByCif(cif);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
