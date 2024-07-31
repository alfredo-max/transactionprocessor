package com.example.transactionprocessor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, Object idTran) {
        jmsTemplate.convertAndSend(destination,idTran);
    }
}
