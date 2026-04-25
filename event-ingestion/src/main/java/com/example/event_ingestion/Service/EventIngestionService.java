package com.example.event_ingestion.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.event_ingestion.DTO.IncomingPaymentEvent;
import com.example.event_ingestion.Model.CanonicalPaymentEvent;
import com.example.event_ingestion.Model.EventSource;
import com.example.event_ingestion.Model.PaymentStatus;
import com.example.event_ingestion.Model.RawEvent;
import com.example.event_ingestion.Model.RawEventStatus;
import com.example.event_ingestion.Producer.CanonicalEventProducer;
import com.example.event_ingestion.Repository.CanonicalPaymentEventRepository;
import com.example.event_ingestion.Repository.RawEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventIngestionService {

    @Autowired
    private CanonicalEventProducer canonicalEventProducer;

    @Autowired
    private CanonicalPaymentEventRepository canonicalPaymentEventRepository;

    @Autowired
    private RawEventRepository rawEventRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void processEvent(IncomingPaymentEvent event, EventSource source) {

        // ✅ Basic validation
        if (event.getEventId() == null || event.getEventId().isBlank() ||
            event.getTransactionId() == null || event.getTransactionId().isBlank()) {

            throw new RuntimeException("Invalid event: missing required fields");
            }

        RawEvent rawEvent = null;

        try {
            // ✅ Serialize payload (keeping your approach)
            String payload = objectMapper.writeValueAsString(event);

            rawEvent = RawEvent.builder()
                    .eventId(event.getEventId())
                    .source(source)
                    .transactionId(event.getTransactionId())
                    .payload(payload)
                    .receivedAt(Instant.now())
                    .status(RawEventStatus.RECEIVED)
                    .build();

            // ✅ Always save raw (no duplicate skipping here)
            rawEventRepository.save(rawEvent);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }

        // ✅ Normalize currency safely
        String currency = event.getCurrency();
        if (currency != null) {
            currency = currency.toUpperCase();
        }

        // ✅ Build canonical
        CanonicalPaymentEvent canonicalEvent = CanonicalPaymentEvent.builder()
                .eventId(event.getEventId())
                .transactionId(event.getTransactionId())
                .source(source)
                .amount(event.getAmount())
                .currency(currency)
                .status(mapStatus(event.getStatus()))
                .eventTime(event.getEventTime())
                .ingestedAt(Instant.now())   // 🔥 REQUIRED FIX
                .build();

        try {
            System.out.println("Canonical event created: " + canonicalEvent);

            canonicalPaymentEventRepository.save(canonicalEvent);
            canonicalEventProducer.publish(canonicalEvent);

            if (rawEvent != null) {
                rawEvent.setStatus(RawEventStatus.PROCESSED);
                rawEventRepository.save(rawEvent);
            }

        } catch (DataIntegrityViolationException e) {
            // ✅ Duplicate handled at canonical level
            if (rawEvent != null) {
                rawEvent.setStatus(RawEventStatus.DUPLICATE);
                rawEventRepository.save(rawEvent);
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (rawEvent != null) {
                rawEvent.setStatus(RawEventStatus.FAILED);
                rawEventRepository.save(rawEvent);
            }
        }
    }

    private PaymentStatus mapStatus(String status) {

        if (status == null)
            return PaymentStatus.PENDING;

        switch (status.toUpperCase()) {
            case "SUCCESS":
            case "DEBITED":
            case "RECEIVED":
                return PaymentStatus.SUCCESS;

            case "FAILED":
            case "REJECTED":
                return PaymentStatus.FAILED;

            default:
                return PaymentStatus.PENDING;
        }
    }
}
