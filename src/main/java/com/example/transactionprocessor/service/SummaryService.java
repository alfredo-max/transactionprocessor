package com.example.transactionprocessor.service;

import com.example.transactionprocessor.model.DailySummary;
import com.example.transactionprocessor.model.Transaction;
import com.example.transactionprocessor.repository.DailySummaryRepository;
import com.example.transactionprocessor.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SummaryService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private DailySummaryRepository dailySummaryRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void generateDailySummary() {
        Date currentDate = new Date();
        // Crear una instancia de Calendar y establecer la fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Restar un día
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        // Obtener la fecha del día anterior
        Date previousDate = calendar.getTime();

        List<Transaction> transactions = transactionRepository.findByFechaBetween(previousDate,currentDate);
        double totalAmount = transactions.stream().mapToDouble(Transaction::getAmount).sum();

        // Guardar el resumen en MongoDB
        DailySummary summary = new DailySummary(previousDate, totalAmount);
        dailySummaryRepository.save(summary);
    }

}
