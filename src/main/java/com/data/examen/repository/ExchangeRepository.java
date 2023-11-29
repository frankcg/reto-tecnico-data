package com.data.examen.repository;

import com.data.examen.entity.Exchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange, Long> {
    Optional<Exchange> findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);

}
