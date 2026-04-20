package com.example.event_ingestion.DTO;

import java.math.BigDecimal;
import java.time.Instant;

public interface IncomingPaymentEvent {

    String getEventId();
    String getTransactionId();

    BigDecimal getAmount();   // normalized
    String getStatus();       // normalized
    Instant getEventTime();   // normalized
    String getCurrency();
}