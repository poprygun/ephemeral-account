package io.microsamples.cache.reader.account;

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

    @org.springframework.data.gemfire.mapping.Indexed
    @org.springframework.data.redis.core.index.Indexed
    private String cif;

    private String description = "from cache";

    @Builder
    public Account(String cif) {
        this();
        this.cif = cif;
    }

    @PersistenceConstructor
    public Account() {
        this.id = UUID.randomUUID().toString();
    }

}

