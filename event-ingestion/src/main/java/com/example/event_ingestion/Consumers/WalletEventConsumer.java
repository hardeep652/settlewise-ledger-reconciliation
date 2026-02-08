package com.example.event_ingestion.Consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.event_ingestion.DTO.IncomingPaymentEvent;
import com.example.event_ingestion.Service.EventIngestionService;


public class WalletEventConsumer {

    @Autowired
        private EventIngestionService eventIngestionService;


    @KafkaListener(topics="wallet.payment.events", groupId="event_ingestion_group")
    public String ConsumeWallet(IncomingPaymentEvent event)
    {
        eventIngestionService.processEvent(event);
        String message = "Recieved wallet event"+event;
        System.out.println(message);
        return message;
    }
}
