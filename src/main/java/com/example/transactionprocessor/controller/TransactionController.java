package com.example.transactionprocessor.controller;

import com.example.transactionprocessor.service.MessageProducer;
import com.example.transactionprocessor.model.DailySummary;
import com.example.transactionprocessor.model.Transaction;
import com.example.transactionprocessor.repository.DailySummaryRepository;
import com.example.transactionprocessor.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private DailySummaryRepository dailySummaryRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    /**
     * @api {get} /obtener-registros Obtener Registros de Transacciones
     * @apiDescription Devuelve una lista de todos los registros de transacciones almacenados en la base de datos.
     */
    @GetMapping(value = "/obtener-registros")
    public ResponseEntity<List<Transaction>> obtenerRegistros() {
        return new ResponseEntity<>(transactionRepository.findAll(),HttpStatus.OK);
    }


     /**
     * @api {post} / Crear Transacción
     * @apiDescription Guarda un nuevo registro de transacción en la base de datos y lo envía a través de un broker de mensajería (ActiveMQ).
     * 
     * @apiError {String} error Mensaje de error en caso de fallo al guardar la transacción o enviar el mensaje.
     */
    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        try{
            Transaction newTransaction = transactionRepository.save(transaction);
            messageProducer.sendMessage("test-queue", transaction.toString());
            return new ResponseEntity<>(newTransaction,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @api {get} /obtener-registros-summary Obtener Resúmenes Diarios de Transacciones
     * @apiDescription Devuelve una lista de resúmenes diarios, donde cada resumen contiene la sumatoria de las transacciones por día.
     */
    @GetMapping(value = "/obtener-registros-summary")
    public ResponseEntity<List<DailySummary>> obtenerRegistrosSumarry() {
        return new ResponseEntity<>(dailySummaryRepository.findAll(),HttpStatus.OK);
    }

}
