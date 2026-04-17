package com.example.event_ingestion.Consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.event_ingestion.DTO.MerchantIncomingPaymentEvent;
import com.example.event_ingestion.Model.EventSource;
import com.example.event_ingestion.Service.EventIngestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MerchantEventConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventIngestionService eventIngestionService;
    
    @KafkaListener(topics="merchant.payment.events", groupId="event_ingestion_group")
    public void ConsumeMerchant(String message)
    {
        try{
            System.out.println("Recieved mechant event: " + message);

            MerchantIncomingPaymentEvent event = objectMapper.readValue(message, MerchantIncomingPaymentEvent.class);
            System.out.println("Parsed merchant event: " + event);
            eventIngestionService.processEvent(event, EventSource.MERCHANT);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
