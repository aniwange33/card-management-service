package com.vela.cardmanageservice.usecase.impl;

import com.vela.cardmanageservice.domain.CardVerificationDomain;
import com.vela.cardmanageservice.domain.CardVerificationStatisticDomain;
import com.vela.cardmanageservice.infrastructure.gatewayImpl.CardManagementServiceDomainGatewayImpl;
import com.vela.cardmanageservice.infrastructure.persistence.entity.CardDetail;
import com.vela.cardmanageservice.infrastructure.persistence.repository.CardDetailRepository;
import com.vela.cardmanageservice.usecase.ProvideVerificationCardStatistics;
import com.vela.cardmanageservice.usecase.VerifyACard;

import javax.inject.Inject;
import javax.inject.Named;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Named
public class CardManagementServiceImpl implements ProvideVerificationCardStatistics, VerifyACard {
    private final CardDetailRepository cardDetailRepository;
    private final CardManagementServiceDomainGatewayImpl managementServiceDomainGateway;

    @Inject
    public CardManagementServiceImpl(CardDetailRepository cardDetailRepository, CardManagementServiceDomainGatewayImpl managementServiceDomainGateway) {
        this.cardDetailRepository = cardDetailRepository;
        this.managementServiceDomainGateway = managementServiceDomainGateway;
    }

    @Override
    public CardVerificationStatisticDomain getCardVerificationStatistics(int start, int limit) throws GeneralSecurityException {
        return managementServiceDomainGateway.getCardVerificationStatistics(start,limit);
    }

    @Override
    public CardVerificationDomain verifyCard(String cardNumber) {
        return managementServiceDomainGateway.verifyCard(cardNumber);
    }

    @Override
    public Optional<CardDetail> insert(CardDetail cardDetail) {
        return Optional.ofNullable(cardDetailRepository.save(cardDetail));
    }

    @Override
    public Optional<CardDetail> updateStats(String iin) {
        CardDetail cardDetail=cardDetailRepository.findByIin(iin).get();
        cardDetail.setStats(cardDetail.getStats()+1);
        return Optional.ofNullable(cardDetailRepository.save(cardDetail));
    }
}
