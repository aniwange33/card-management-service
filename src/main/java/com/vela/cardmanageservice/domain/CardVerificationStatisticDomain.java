package com.vela.cardmanageservice.domain;

import lombok.Data;

import java.security.GeneralSecurityException;

@Data
public class CardVerificationStatisticDomain {
    private boolean success;
    private int start;
    private int limit;
    private int size;
    private Object payload;

    private CardVerificationStatisticDomain(int start, int limit, int size) {
        this.start = start;
        this.limit = limit;
        this.size = size;
    }

    public static CardVerificationStatisticDomain from(int start, int limit, int size) throws GeneralSecurityException {
        if(start<1){
            throw new GeneralSecurityException("Start value cannot be less than one");
        }
        if(start>limit){
            throw new GeneralSecurityException("Start value cannot be greater than limit");
        }
        if(size==0){
            throw new GeneralSecurityException("No statistic found");
        }
        if(limit>size){
            limit=size;
        }
        return new CardVerificationStatisticDomain(start, limit, size);
    }
}
