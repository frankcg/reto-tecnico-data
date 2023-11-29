package com.data.examen.services;

import com.data.examen.entity.Exchange;
import com.data.examen.entity.Transaction;

import java.util.Optional;

public interface TransactionService {
    Iterable<Transaction> getTransactions();
    Optional<Transaction> getTransactionById(String id);
    Transaction saveTransaction(Transaction transaction);
}
