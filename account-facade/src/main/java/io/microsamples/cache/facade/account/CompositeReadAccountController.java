package io.microsamples.cache.facade.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompositeReadAccountController {


    private CompositeReadAccountService compositeReadAccountService;

    public CompositeReadAccountController(CompositeReadAccountService compositeReadAccountService) {
        this.compositeReadAccountService = compositeReadAccountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getByCif(@RequestParam String cif){
        return new ResponseEntity<>(compositeReadAccountService.findByCif(cif), HttpStatus.OK);
    }

}
