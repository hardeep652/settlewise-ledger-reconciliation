package com.example.payment_event_simulator.Model;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MerchantPaymentEvent {

    private String eventId;
    private String transactionId;      // Payment transaction
    private String merchantId;          // Merchant identifier
    private BigDecimal orderAmount;
    private String currency;
    private String paymentResult;       // RECEIVED, REJECTED
    private Instant receivedAt;         // When merchant got confirmation
}
