package com.data.examen.controllers;

import com.data.examen.entity.Transaction;
import com.data.examen.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.migraciones.common.dto.ExampleRequestDTO;
import pe.migraciones.common.dto.ExampleResponseDTO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping()
    public  ResponseEntity<?> getExchangeAll(){
        return ResponseEntity.ok().body(transactionService.getTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExchange(@PathVariable String id){
        Optional<Transaction> a = transactionService.getTransactionById(id);
        if(!a.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(a.get());
    }

    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction){
        Transaction transactionDb = transactionService.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDb);
    }

    @PostMapping("/library")
    public ResponseEntity<?> exampleLibrary(@RequestBody ExampleRequestDTO request){

        return ResponseEntity.status(HttpStatus.CREATED).body(ExampleResponseDTO.builder()
                .fullName(request.getName()+" "+request.getLastName())
                        .age(calcularEdad(request.getBirthdate()))
                .build());
    }

    public static int calcularEdad(LocalDateTime fechaNacimiento) {
        LocalDateTime ahora = LocalDateTime.now();
        return (int) ChronoUnit.YEARS.between(fechaNacimiento, ahora);
    }



}
