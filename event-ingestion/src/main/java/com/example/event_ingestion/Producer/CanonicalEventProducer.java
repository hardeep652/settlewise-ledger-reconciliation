package com.example.event_ingestion.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.event_ingestion.Model.CanonicalPaymentEvent;

@Component
public class CanonicalEventProducer {

    private static final String TOPIC = "canonical.payment.events";

    @Autowired
    private KafkaTemplate<String, CanonicalPaymentEvent> kafkaTemplate;

    public void publish(CanonicalPaymentEvent event) {

        kafkaTemplate.send(
            TOPIC,
            event.getTransactionId(), // 🔥 partition key (IMPORTANT)
            event
        );

        System.out.println("Published canonical event: " + event);
    }
}