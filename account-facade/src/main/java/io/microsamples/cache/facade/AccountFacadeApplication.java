package io.microsamples.cache.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class AccountFacadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountFacadeApplication.class, args);
	}
}
