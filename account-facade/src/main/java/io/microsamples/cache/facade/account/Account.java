package io.microsamples.cache.facade.account;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;



@Builder
@Getter
@ToString
public class Account {

    private UUID id;

    private String cif;
}

