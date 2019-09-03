package com.vela.cardmanageservice.infrastructure.persistence.impl;

import com.vela.cardmanageservice.infrastructure.persistence.entity.CardDetail;
import com.vela.cardmanageservice.infrastructure.persistence.repository.CardDetailRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class CardDetailsRepositoryService {
    private final CardDetailRepository cardDetailRepository;

    @Inject
    public CardDetailsRepositoryService(CardDetailRepository cardDetailRepository) {
        this.cardDetailRepository = cardDetailRepository;
    }

    public  Optional<CardDetail> insert(CardDetail cardDetail){
        return Optional.ofNullable(cardDetailRepository.save(cardDetail));
    }

    public Optional<CardDetail> updateStats(String cardNumber) {
        CardDetail cardDetail=cardDetailRepository.findByIin(cardNumber).get();
        cardDetail.setStats(cardDetail.getStats()+1);
        return Optional.ofNullable(cardDetailRepository.save(cardDetail));
    }
}
