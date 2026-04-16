package com.example.event_ingestion.DTO;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Data;

@Data
public class WalletIncomingPaymentEvent implements IncomingPaymentEvent {

    private String eventId;
    private String transactionId;
    private String walletId;
    private BigDecimal amount;
    private String currency;
    private String walletStatus;
    private Instant createdAt;

    // 🔥 Mapping

    @Override
    public String getStatus() {
        return walletStatus;
    }

    @Override
    public Instant getEventTime() {
        return createdAt;
    }
}