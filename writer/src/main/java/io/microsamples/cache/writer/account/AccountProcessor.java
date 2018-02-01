package io.microsamples.cache.writer.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Profile;

@EnableBinding(Sink.class)
@Profile("rabbit")
@Slf4j
public class AccountProcessor {

    private AccountRepository accountRepository;

    public AccountProcessor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @StreamListener(target=Sink.INPUT)
    public void cacheAccount(Account account) {
        Account saved = accountRepository.save(account);
        log.info("Cached account ---> {}", saved);
    }

}
