package com.vela.cardmanageservice.usecase;

import com.vela.cardmanageservice.domain.CardVerificationDomain;

public interface VerifyACard {
    CardVerificationDomain verifyCard(String cardVerificationUrl);

}
