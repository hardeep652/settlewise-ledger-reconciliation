package com.example.event_ingestion.Consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.event_ingestion.DTO.IncomingPaymentEvent;
import com.example.event_ingestion.Service.EventIngestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WalletEventConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
        private EventIngestionService eventIngestionService;


    @KafkaListener(topics="wallet.payment.events", groupId="event_ingestion_group")
    public void ConsumeWallet(String message)
    {
        try
        {
            System.out.println("Received message: " + message);

            IncomingPaymentEvent event = objectMapper.readValue(message, IncomingPaymentEvent.class);
            eventIngestionService.processEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
