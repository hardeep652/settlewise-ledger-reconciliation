package com.example.event_ingestion.Consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.event_ingestion.DTO.BankIncomingPaymentEvent;
import com.example.event_ingestion.Model.EventSource;
import com.example.event_ingestion.Service.EventIngestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BankEventConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventIngestionService eventIngestionService;

    @KafkaListener(topics="bank.payment.events", groupId="event_ingestion_group")
    public void consumeBank(String message)
    {

        try {

            System.out.println("Received bank event: " + message);

            BankIncomingPaymentEvent event = objectMapper.readValue(message, BankIncomingPaymentEvent.class);

            eventIngestionService.processEvent(event, EventSource.BANK);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
