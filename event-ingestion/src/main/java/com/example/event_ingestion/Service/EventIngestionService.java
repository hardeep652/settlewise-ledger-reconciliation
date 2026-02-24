package com.example.event_ingestion.Service;

import org.springframework.stereotype.Service;

import com.example.event_ingestion.DTO.IncomingPaymentEvent;

@Service
public class EventIngestionService {

    public void processEvent(IncomingPaymentEvent event)
    {
        // Here you can add logic to process the event, such as saving it to a database
        // or performing any necessary transformations before further processing.
        System.out.println("Processing event: " + event);
    }
    
}
