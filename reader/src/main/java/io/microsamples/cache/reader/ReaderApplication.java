package io.microsamples.cache.reader;


import io.microsamples.cache.reader.account.Account;
import io.microsamples.cache.reader.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableGemfireRepositories
public class ReaderApplication implements CommandLineRunner{

	@Autowired
	private AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReaderApplication.class, args);
	}


	@Override
	public void run(String... strings) {

		String[] cifs = {"xyz1", "xyz2", "xyz3"};
		Arrays.stream(cifs).forEach(cif -> accountRepository.save(Account.builder().cif(cif).build()));

	}
}
