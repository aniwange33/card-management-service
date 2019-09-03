package com.vela.cardmanageservice.usecase;

import com.vela.cardmanageservice.domain.CardVerificationStatisticDomain;
import com.vela.cardmanageservice.infrastructure.persistence.entity.CardDetail;

import java.security.GeneralSecurityException;
import java.util.Optional;

public interface ProvideVerificationCardStatistics {
    CardVerificationStatisticDomain getCardVerificationStatistics(int start, int limit) throws GeneralSecurityException;
    Optional<CardDetail> insert(CardDetail cardDetail);
    Optional<CardDetail> updateStats(String iin);
}
