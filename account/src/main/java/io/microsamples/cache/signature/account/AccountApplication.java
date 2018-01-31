package io.microsamples.cache.signature.account;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@RestController
public class AccountApplication {

	EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
			.seed(123L)
			.objectPoolSize(10)
			.randomizationDepth(3)
			.charset(Charset.forName("UTF-8"))
			.stringLengthRange(5, 50)
			.collectionSizeRange(1, 10)

			.scanClasspathForConcreteTypes(false)
			.overrideDefaultInitialization(false)
			.build();

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAccounts(@RequestParam String cif) {
		Account[] accounts = random.random(Account[].class);
		Arrays.stream(accounts).forEach(account -> account.setCif(cif));
		Arrays.stream(accounts).forEach(account -> account.setDescription("from signature"));
		return new ResponseEntity<>(Arrays.asList(accounts), HttpStatus.OK);
	}

}
