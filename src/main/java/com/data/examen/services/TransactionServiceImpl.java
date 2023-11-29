package com.data.examen.services;

import com.data.examen.entity.Exchange;
import com.data.examen.entity.Transaction;
import com.data.examen.enume.Operation;
import com.data.examen.repository.ExchangeRepository;
import com.data.examen.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;


    @Override
    public Iterable<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {

        Optional<Exchange> exchange = exchangeRepository.findBySourceCurrencyAndTargetCurrency(transaction.getSourceCurrency(), transaction.getTargetCurrency());
        transaction.setId(UUID.randomUUID().toString());
        if(exchange.isPresent()){
            transaction.setExchange(exchange.get().getExchange());
            if(exchange.get().getOperation().equals(Operation.MULTIPLICATION)){
                transaction.setAmountCalculated(transaction.getAmount()*transaction.getExchange());
            }else{
                transaction.setAmountCalculated(transaction.getAmount()/transaction.getExchange());
            }
        }
        return transactionRepository.save(transaction);
    }
}
