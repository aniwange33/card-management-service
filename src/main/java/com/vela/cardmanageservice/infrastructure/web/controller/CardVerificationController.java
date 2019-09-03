package com.vela.cardmanageservice.infrastructure.web.controller;

import com.vela.cardmanageservice.domain.CardVerificationDomain;
import com.vela.cardmanageservice.domain.CardVerificationStatisticDomain;
import com.vela.cardmanageservice.usecase.CardManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.GeneralSecurityException;

;

@RestController
@RequestMapping(value = "/api/")
public class CardVerificationController {
    @Autowired
    CardManagementService cardManagementService;

    @GetMapping(value = "card-scheme/verify/{cardNumber}")
    ResponseEntity<CardVerificationDomain> verifyCard(@RequestParam String cardNumber) {
     return ResponseEntity.ok(cardManagementService.verifyCard(cardNumber));
    }

    @GetMapping(value = "card-scheme/stats")
    ResponseEntity<CardVerificationStatisticDomain> getCardStats(@RequestParam int start, @RequestParam int limit) throws GeneralSecurityException {
     return ResponseEntity.ok(cardManagementService.getCardVerificationStatistics(start,limit));
    }

}

