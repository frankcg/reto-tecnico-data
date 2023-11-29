package com.data.examen.bootstrap;

import com.data.examen.entity.Exchange;
import com.data.examen.enume.Operation;
import com.data.examen.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExchangeLoader implements CommandLineRunner {
	
	@Autowired
	ExchangeRepository exchangeRepository;

	@Override
	public void run(String... args) throws Exception {
		loadTodos();		
	}
	
	private void loadTodos() {
        if (exchangeRepository.count() == 0) {
			exchangeRepository.save(
                    Exchange.builder()
							.sourceCurrency("PEN")
							.targetCurrency("USD")
							.operation(Operation.DIVISION)
							.exchange(3.8)
                            .build()
            );
			exchangeRepository.save(
					Exchange.builder()
							.sourceCurrency("USD")
							.targetCurrency("PEN")
							.operation(Operation.MULTIPLICATION)
							.exchange(3.6)
							.build()
            );
            System.out.println("Load Exchange");
        }
    }
	
	
}
