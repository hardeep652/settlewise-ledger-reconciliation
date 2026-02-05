package com.example.payment_event_simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.payment_event_simulator.Model.BankPaymentEvent;

@Service
public class BankService {

    @Autowired
    private KafkaTemplate<String, BankPaymentEvent> kafkaTemplate;

    public ResponseEntity<String> processBankEvent(BankPaymentEvent event)
    {
        kafkaTemplate.send("bank.payment.events", event.getTransactionId(), event);
        return ResponseEntity.ok("Event sent to Kafka with Transaction ID: " + event.getTransactionId());
    }
}
