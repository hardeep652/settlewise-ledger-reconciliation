package com.example.payment_event_simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.payment_event_simulator.Model.MerchantPaymentEvent;
@Service
public class MerchantService {

    @Autowired
    private KafkaTemplate<String, MerchantPaymentEvent> kafkaTemplate;

    public ResponseEntity<String> processMerchantEvent(MerchantPaymentEvent event)
    {
        kafkaTemplate.send("merchant.payment.events", event.getTransactionId(), event);
        return ResponseEntity.ok("Merchant event sent to Kafka: " + event.getEventId());
    }
}
