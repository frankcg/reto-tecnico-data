package com.data.examen.controllers;

import com.data.examen.entity.Exchange;
import com.data.examen.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeController {
	
	@Autowired
	private ExchangeService exchangeService;
	
	@GetMapping
	public ResponseEntity<?> getAllExchanges(){		
		return ResponseEntity.ok().body(exchangeService.getExchanges());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getExchange(@PathVariable Long id){
		Optional<Exchange> a = exchangeService.getExchangeById(id);
		if(!a.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(a.get());
	}
	
	@PostMapping
	public ResponseEntity<?> saveExchange(@RequestBody Exchange exchange){
		return ResponseEntity.status(HttpStatus.CREATED).body(exchangeService.saveExchange(exchange));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateExchange(@RequestBody Exchange exchange, @PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(exchangeService.updateExchange(id, exchange));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExchange(@PathVariable Long id){
		exchangeService.deleteExchange(id);
		return ResponseEntity.noContent().build();
	}	

}
