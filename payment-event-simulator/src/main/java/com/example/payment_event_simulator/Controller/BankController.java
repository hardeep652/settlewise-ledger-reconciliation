package com.example.payment_event_simulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_event_simulator.Model.BankPaymentEvent;
import com.example.payment_event_simulator.Service.BankService;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/bank/event")
    public ResponseEntity<String> simulateBankEvent(@RequestBody BankPaymentEvent event)
    {
        ResponseEntity<String> processedEvent = bankService.processBankEvent(event);
        return ResponseEntity.ok("Bank event processed: " + processedEvent.getBody());
    }


}
