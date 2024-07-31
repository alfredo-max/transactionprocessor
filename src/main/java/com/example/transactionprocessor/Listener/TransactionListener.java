package com.example.transactionprocessor.Listener;

import jakarta.jms.TextMessage;
import org.springframework.stereotype.Service;
import org.springframework.jms.annotation.JmsListener;

@Service
public class TransactionListener {

    @JmsListener(destination = "test-queue")
    public void receiveMessage(Object mensaje) {
        if (mensaje instanceof TextMessage) {
            try {
                TextMessage textMessage = (TextMessage) mensaje;
                String text = textMessage.getText();
                System.out.println("Transaction received: " + text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Received non-text message: " + mensaje);
        }
    }
}

