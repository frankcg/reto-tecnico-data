package com.data.examen.services;

import com.data.examen.entity.Exchange;
import com.data.examen.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ExchangeServiceImpl implements ExchangeService {
	
	@Autowired
	ExchangeRepository exchangeRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Exchange> getExchanges() {
		return exchangeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Exchange> getExchangeById(Long id) {
		return exchangeRepository.findById(id);
	}

	@Override
	@Transactional
	public Exchange saveExchange(Exchange exchange) {
		return exchangeRepository.save(exchange);
	}

	@Override
	public Exchange updateExchange(Long id, Exchange exchange) {
		Optional<Exchange> a = exchangeRepository.findById(id);
		Exchange exchangeDb = new Exchange();
		if(a.isPresent()){
			exchangeDb = a.get();
			exchangeDb.setSourceCurrency(exchange.getSourceCurrency());
			exchangeDb.setTargetCurrency(exchange.getTargetCurrency());
			exchangeDb.setExchange(exchange.getExchange());
		}
		return exchangeRepository.save(exchangeDb);

	}

	@Override
	@Transactional
	public void deleteExchange(Long id) {
		exchangeRepository.deleteById(id);

	}

}
