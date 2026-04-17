package com.example.event_ingestion.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.event_ingestion.DTO.IncomingPaymentEvent;
import com.example.event_ingestion.Model.EventSource;
import com.example.event_ingestion.Model.RawEvent;
import com.example.event_ingestion.Model.RawEventStatus;
import com.example.event_ingestion.Repository.RawEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventIngestionService {

    @Autowired
    private RawEventRepository rawEventRepository;

   @Autowired
    private ObjectMapper ObjectMapper;


   public void processEvent(IncomingPaymentEvent event, EventSource source) {

    if (event.getEventId() == null || event.getEventId().isBlank() ||
        event.getTransactionId() == null || event.getTransactionId().isBlank()) {

        throw new RuntimeException("Invalid event: missing required fields");
    }

    try {
String payload = ObjectMapper.writeValueAsString((Object) event);
        RawEvent rawEvent = RawEvent.builder()
                .eventId(event.getEventId())
                .source(source)
                .transactionId(event.getTransactionId())
                .payload(payload)
                .receivedAt(Instant.now())
                .status(RawEventStatus.RECEIVED)
                .build();

        try {
            rawEventRepository.save(rawEvent);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Duplicate event skipped: " + event.getEventId());
            return;
        }

    } catch (JsonProcessingException e) {
        throw new RuntimeException("Failed to serialize event", e);
    }
}
}