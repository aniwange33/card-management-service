package com.vela.cardmanageservice.infrastructure.web.controller;

import com.vela.cardmanageservice.usecase.ProvideVerificationCardStatistics;
import com.vela.cardmanageservice.usecase.VerifyACard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

;import java.security.GeneralSecurityException;

@RestController
@RequestMapping(value = "/api/")
public class CardVerificationController {
    @Autowired
    ProvideVerificationCardStatistics provideVerificationCardStatistics;
    @Autowired
    VerifyACard verifyACard;
    @GetMapping(value = "card-scheme/verify/{cardNumber}")
    ResponseEntity<?> verifyCard(@RequestParam String cardNumber) {
     return ResponseEntity.ok(verifyACard.verifyCard(cardNumber));
    }

    @GetMapping(value = "card-scheme/stats")
    ResponseEntity<?> getCardStats(@RequestParam int start, @RequestParam int limit) throws GeneralSecurityException {
     return ResponseEntity.ok(provideVerificationCardStatistics.getCardVerificationStatistics(start,limit));
    }

}

