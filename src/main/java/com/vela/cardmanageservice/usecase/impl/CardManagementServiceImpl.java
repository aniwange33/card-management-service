package com.vela.cardmanageservice.usecase.impl;

import com.vela.cardmanageservice.domain.CardVerificationDomain;
import com.vela.cardmanageservice.domain.CardVerificationStatisticDomain;
import com.vela.cardmanageservice.domain.gateway.CardVerificationDomainGateway;
import com.vela.cardmanageservice.domain.gateway.CardVerificationStatisticDomainGateway;
import com.vela.cardmanageservice.usecase.ProvideVerificationCardStatistics;
import com.vela.cardmanageservice.usecase.VerifyACard;

import javax.inject.Inject;
import javax.inject.Named;
import java.security.GeneralSecurityException;



@Named
public class CardManagementServiceImpl implements ProvideVerificationCardStatistics, VerifyACard {

   private final CardVerificationDomainGateway          managementServiceDomainGateway;
    private final CardVerificationStatisticDomainGateway cardVerificationStatisticDomainGateway;

    @Inject
    public CardManagementServiceImpl(CardVerificationDomainGateway managementServiceDomainGateway, CardVerificationStatisticDomainGateway cardVerificationStatisticDomainGateway) {
        this.managementServiceDomainGateway = managementServiceDomainGateway;
        this.cardVerificationStatisticDomainGateway = cardVerificationStatisticDomainGateway;
    }

    @Override
    public CardVerificationStatisticDomain getCardVerificationStatistics(int start, int limit) throws GeneralSecurityException {
        return cardVerificationStatisticDomainGateway.getCardVerificationStatistics(start,limit);
    }

    @Override
    public CardVerificationDomain verifyCard(String cardNumber) {
        return managementServiceDomainGateway.verifyCard(cardNumber);
    }

}
