package com.vela.cardmanageservice.domain.gateway;

import com.vela.cardmanageservice.domain.CardVerificationStatisticDomain;

import java.security.GeneralSecurityException;

public interface CardVerificationStatisticDomainGateway {
    CardVerificationStatisticDomain getCardVerificationStatistics(int start,int limit) throws GeneralSecurityException;
}
