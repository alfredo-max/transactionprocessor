package com.example.transactionprocessor.repository;

import com.example.transactionprocessor.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    @Query("{ 'timestamp' : { $gte: ?0, $lt: ?1 } }")
    List<Transaction> findByFechaBetween(Date start, Date end);
}

