package com.example.transactionprocessor.repository;

import com.example.transactionprocessor.model.DailySummary;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DailySummaryRepository extends MongoRepository<DailySummary, String> {
}
