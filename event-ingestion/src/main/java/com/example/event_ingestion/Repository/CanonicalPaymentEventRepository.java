package com.example.event_ingestion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_ingestion.Model.CanonicalPaymentEvent;

public interface CanonicalPaymentEventRepository 
        extends JpaRepository<CanonicalPaymentEvent, Long> {
}