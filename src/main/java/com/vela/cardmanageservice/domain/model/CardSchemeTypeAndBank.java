package com.vela.cardmanageservice.domain.model;

import lombok.Data;

@Data
public class CardSchemeTypeAndBank {
    private  String scheme;
    private String  type;
    private String  bank;

    private CardSchemeTypeAndBank(String scheme, String type, String bank) {
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
    }

    public static CardSchemeTypeAndBank from (String scheme, String type, String bank) {
        return new CardSchemeTypeAndBank(scheme, type, bank);
    }
}
