package io.microsamples.cache.writer.account;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;


@Region("Accounts")
@RedisHash("Accounts")
@Getter
@ToString
public class Account {

    @Id
    private String id;

    private String cif;

    private String description;

    @Builder
    public Account(String cif, String description) {
        this();
        this.cif = cif;
        this.description = description;
    }

    @PersistenceConstructor
    public Account() {
        this.id = UUID.randomUUID().toString();
    }

}

