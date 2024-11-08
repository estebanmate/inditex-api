package com.inditex.challenge.infrastructure.repository.h2;

import com.inditex.challenge.infrastructure.repository.h2.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpringDataH2PriceRepository extends JpaRepository<PriceEntity, Long>,
    JpaSpecificationExecutor<PriceEntity> {

}
