package io.microsamples.cache.writer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
public class WriterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WriterApplication.class, args);
	}
}
