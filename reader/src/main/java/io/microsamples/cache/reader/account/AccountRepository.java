package io.microsamples.cache.reader.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, String>{
    List<Account> findByCif(@Param("cif") String cif);
}
