package com.inditex.challenge.domain.repository;

import com.inditex.challenge.domain.entities.Price;
import com.inditex.challenge.infrastructure.repository.h2.entities.PriceEntity;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PriceRepository {

  Optional<Price> findPrice(Specification<PriceEntity> spec, Pageable pageable);

}
