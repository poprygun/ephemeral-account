package io.microsamples.cache.signature.account;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@Builder
@ToString
public class Account {
    private UUID id;
    private String cif;
}
