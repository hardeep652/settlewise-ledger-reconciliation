package com.example.event_ingestion.DTO;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Data;

@Data
public class BankIncomingPaymentEvent implements IncomingPaymentEvent {

    private String eventId;
    private String transactionId;
    private String bankRefId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private Instant eventTime;

    // No override needed — names already match
}