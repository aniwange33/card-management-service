package com.vela.cardmanageservice.usecase;

import com.vela.cardmanageservice.domain.CardVerificationDomain;
import com.vela.cardmanageservice.domain.CardVerificationStatisticDomain;

import java.security.GeneralSecurityException;

public interface CardManagementService {
    CardVerificationStatisticDomain getCardVerificationStatistics(int start, int limit) throws GeneralSecurityException;
    CardVerificationDomain verifyCard(String cardNumber);
}
