package com.vela.cardmanageservice.domain;

import com.vela.cardmanageservice.domain.model.CardSchemeTypeAndBank;
import lombok.Data;

@Data
public class CardVerificationDomain {
    private boolean  success;
    private CardSchemeTypeAndBank payload;

    private CardVerificationDomain(boolean success, CardSchemeTypeAndBank payload) {
        this.success = success;
        this.payload = payload;
    }

    public static CardVerificationDomain createCardVerificationDomain(boolean success, CardSchemeTypeAndBank payload) {
        return new CardVerificationDomain(success, payload);
    }
}
