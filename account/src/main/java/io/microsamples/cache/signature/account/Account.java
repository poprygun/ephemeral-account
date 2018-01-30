package io.microsamples.cache.signature.account;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Account {
    private String id;
    private String cif;
}
