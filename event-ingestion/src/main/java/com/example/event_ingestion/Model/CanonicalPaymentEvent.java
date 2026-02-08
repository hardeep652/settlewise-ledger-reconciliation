package com.example.event_ingestion.Model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "canonical_payment_events",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_canonical_event_event_id", columnNames = "eventId")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanonicalPaymentEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Same eventId as raw event
     * Ensures idempotency across the system
     */
    @Column(nullable = false, updatable = false)
    private String eventId;

    /**
     * Correlation key for reconciliation
     */
    @Column(nullable = false, updatable = false)
    private String transactionId;

    /**
     * Source system
     */
    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EventSource source;

    /**
     * Monetary amount
     */
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    /**
     * Currency (ISO-4217)
     */
    @Column(nullable = false, length = 3)
    private String currency;

    /**
     * Business status of the payment
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    /**
     * Time when the event happened in source system
     */
    @Column(nullable = false)
    private Instant eventTime;

    /**
     * Time when event was ingested
     */
    @Column(nullable = false, updatable = false)
    private Instant ingestedAt;
}
