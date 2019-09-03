package com.vela.cardmanageservice.infrastructure.persistence.repository;


import com.vela.cardmanageservice.infrastructure.persistence.entity.CardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardDetailRepository extends JpaRepository<CardDetail,Long> {
    Optional<CardDetail> findByIin(String iin);

}
