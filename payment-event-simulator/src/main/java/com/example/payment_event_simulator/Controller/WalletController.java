package com.example.payment_event_simulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_event_simulator.Model.WalletPaymentEvent;
import com.example.payment_event_simulator.Service.WalletService;

@RestController
@RequestMapping("/api")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/wallet/event")
    public ResponseEntity<String> simulateWalletEvent(@RequestBody WalletPaymentEvent event)
    {
        ResponseEntity<String> processedEvent = walletService.processWalletEvent(event);
        return ResponseEntity.ok("Wallet event processed: " + processedEvent.getBody());
    }

}
