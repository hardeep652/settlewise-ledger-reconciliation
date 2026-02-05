package com.example.payment_event_simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.payment_event_simulator.Model.WalletPaymentEvent;

@Service
public class WalletService {

    @Autowired
    private KafkaTemplate<String, WalletPaymentEvent> kafkaTemplate;

    public ResponseEntity<String> processWalletEvent(WalletPaymentEvent event)
    {
        kafkaTemplate.send("wallet.payment.events", event.getTransactionId(), event);
        return ResponseEntity.ok("Wallet event sent to Kafka: " + event.getEventId());
    }
}
