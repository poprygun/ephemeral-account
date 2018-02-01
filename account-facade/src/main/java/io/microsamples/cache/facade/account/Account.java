package io.microsamples.cache.facade.account;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;


@Getter
@ToString
public class Account {

    private String id;

    private String cif;

    private String description;

    @Builder
    public Account(String cif, String description) {
        this();
        this.cif = cif;
        this.description = description;
    }

    public Account() {
        this.id = UUID.randomUUID().toString();
    }
}

