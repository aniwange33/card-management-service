package com.vela.cardmanageservice.domain.gateway;

import com.vela.cardmanageservice.domain.CardVerificationDomain;

public interface CardVerificationDomainGateway {
CardVerificationDomain verifyCard(String cardNumber);

}
