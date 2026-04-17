package com.example.event_ingestion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.event_ingestion.Model.RawEvent;

@Repository
public interface RawEventRepository extends JpaRepository<RawEvent, Long> {

    // Optional (not needed for idempotency, but useful for debugging)
    boolean existsByEventIdAndSource(String eventId, String source);
}