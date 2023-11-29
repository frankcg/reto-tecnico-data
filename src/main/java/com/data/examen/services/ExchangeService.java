package com.data.examen.services;

import com.data.examen.entity.Exchange;

import java.util.Optional;

public interface ExchangeService {
	
	Iterable<Exchange> getExchanges();

	Optional<Exchange> getExchangeById(Long id);

	Exchange saveExchange(Exchange exchange);

	Exchange updateExchange(Long id, Exchange exchange);

    void deleteExchange(Long id);

}
