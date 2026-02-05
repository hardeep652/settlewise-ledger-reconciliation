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
public class WalletPaymentEvent {

    private String eventId;
    private String transactionId;     // Shared transaction id
    private String walletId;           // User wallet identifier
    private BigDecimal amount;
    private String currency;
    private String walletStatus;       // DEBITED, FAILED
    private Instant createdAt;         // Wallet event creation time
}

