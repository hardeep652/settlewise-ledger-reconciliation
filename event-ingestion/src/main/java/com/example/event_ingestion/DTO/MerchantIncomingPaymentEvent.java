package com.example.event_ingestion.DTO;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Data;

@Data
public class MerchantIncomingPaymentEvent implements IncomingPaymentEvent {

    private String eventId;
    private String transactionId;
    private String merchantId;
    private BigDecimal orderAmount;
    private String currency;
    private String paymentResult;
    private Instant receivedAt;

    // 🔥 Mapping to common interface

    @Override
    public BigDecimal getAmount() {
        return orderAmount;
    }

    @Override
    public String getStatus() {
        return paymentResult;
    }

    @Override
    public Instant getEventTime() {
        return receivedAt;
    }
}