package com.vela.cardmanageservice.infrastructure.gatewayImpl;

import com.vela.cardmanageservice.domain.CardVerificationDomain;
import com.vela.cardmanageservice.domain.CardVerificationStatisticDomain;
import com.vela.cardmanageservice.domain.gateway.CardVerificationDomainGateway;
import com.vela.cardmanageservice.domain.gateway.CardVerificationStatisticDomainGateway;
import com.vela.cardmanageservice.domain.model.CardSchemeTypeAndBank;
import com.vela.cardmanageservice.infrastructure.gatewayImpl.model.GeneralResponse;
import com.vela.cardmanageservice.infrastructure.persistence.entity.CardDetail;
import com.vela.cardmanageservice.infrastructure.persistence.impl.CardDetailsRepositoryService;
import com.vela.cardmanageservice.infrastructure.persistence.repository.CardDetailRepository;
import com.vela.cardmanageservice.usecase.exception.GenericInputErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CardManagementServiceDomainGatewayImpl implements CardVerificationDomainGateway, CardVerificationStatisticDomainGateway {
    @Autowired
    RestTemplate restTemplateConfig;
    @Autowired
    CardDetailRepository cardDetailRepository;
    @Autowired
    ProvideVerificationCardStatistics provideVerificationCardStatistics;
    @Autowired
    VerifyACard verifyACard;
    @Autowired
    CardDetailsRepositoryService repositoryService;

    final static String BASE_URL="https://lookup.binlist.net";
    @Override
    public CardVerificationDomain verifyCard(String cardNumber) {
        if(StringUtils.isEmpty(cardNumber)){
            throw new GenericInputErrorException("Card Number is empty");
         }
        if(!StringUtils.isNumeric(cardNumber)){
            throw new GenericInputErrorException("Card Number should be A number");
        }
        if(cardNumber.length()<6){
            throw new GenericInputErrorException("Card Number most be upto 6 digit");
        }
        if(cardNumber.length()>6){
            cardNumber=cardNumber.substring(0,6);
        }
        Optional<GeneralResponse> responseOptional=Optional.ofNullable(restTemplateConfig.getForObject(BASE_URL+"/"+cardNumber, GeneralResponse.class));
        if(!responseOptional.isPresent()){
                   throw new GenericInputErrorException("No record found");
        }
        GeneralResponse response=responseOptional.get();
        if(cardDetailRepository.findByIin(cardNumber).isPresent()){
            repositoryService.updateStats(cardNumber);
        }else {
            repositoryService.insert(CardDetail.createCardDetail(cardNumber,response.getScheme(),response.getBank().getName(),1));
        }
        return CardVerificationDomain.createCardVerificationDomain(true, CardSchemeTypeAndBank.from(response.getScheme(),response.getType(),response.getBank().getName()));
    }

    @Override
    public CardVerificationStatisticDomain getCardVerificationStatistics(int start, int limit) throws GeneralSecurityException {
        Optional<List<CardDetail>> cardDetailListOptional=Optional.ofNullable(cardDetailRepository.findAll());
        if(!cardDetailListOptional.isPresent()){
            throw new GenericInputErrorException("no record found");
        }
        CardVerificationStatisticDomain report=CardVerificationStatisticDomain.from(start,limit, cardDetailListOptional.get().size());
        report.setSuccess(true);
        report.setPayload(cardDetailListOptional.get().parallelStream().skip(start-1).limit(limit).collect(Collectors.toMap(CardDetail::getIin, CardDetail::getStats)));
        return report;
    }
}
