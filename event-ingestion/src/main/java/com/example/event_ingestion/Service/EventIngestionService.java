package com.example.event_ingestion.Service;

import org.springframework.stereotype.Service;

import com.example.event_ingestion.DTO.IncomingPaymentEvent;

@Service
public class EventIngestionService {

    public void processEvent(IncomingPaymentEvent event) {

        // Basic validation
       if (event.getEventId() == null || event.getEventId().isBlank() ||
    event.getTransactionId() == null || event.getTransactionId().isBlank()) {

    throw new RuntimeException("Invalid event: missing required fields");
}

        System.out.println("Processing event:");
        System.out.println("TxnId: " + event.getTransactionId());
        System.out.println("Amount: " + event.getAmount());
        System.out.println("Status: " + event.getStatus());
        System.out.println("Time: " + event.getEventTime());

        // Next:
        // idempotency
        // raw save
        // canonical transform
    }
}