package com.example.payment_event_simulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_event_simulator.Model.MerchantPaymentEvent;
import com.example.payment_event_simulator.Service.MerchantService;

@RestController
@RequestMapping("/api")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/merchant/event")
    public ResponseEntity<String> simulateMerchantEvent(@RequestBody MerchantPaymentEvent event)
    {
        ResponseEntity<String> processedEvent = merchantService.processMerchantEvent(event);
        return ResponseEntity.ok("Merchant event processed: " + processedEvent.getBody());
    }
}
