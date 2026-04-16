package com.example.event_ingestion.Model;

public enum RawEventStatus {
    RECEIVED,
    INVALID,
    PROCESSED,
    DUPLICATE,
    FAILED
}