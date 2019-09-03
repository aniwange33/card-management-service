package com.vela.cardmanageservice.usecase;

import com.vela.cardmanageservice.domain.CardVerificationStatisticDomain;

import java.security.GeneralSecurityException;

public interface ProvideVerificationCardStatistics {
    CardVerificationStatisticDomain getCardVerificationStatistics(int start, int limit) throws GeneralSecurityException;

}
