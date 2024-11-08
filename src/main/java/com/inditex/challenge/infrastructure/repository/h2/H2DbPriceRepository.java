package com.inditex.challenge.infrastructure.repository.h2;

import com.inditex.challenge.domain.entities.Price;
import com.inditex.challenge.domain.repository.PriceRepository;
import com.inditex.challenge.infrastructure.repository.h2.entities.PriceEntity;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class H2DbPriceRepository implements PriceRepository {

  private final SpringDataH2PriceRepository springDataH2PriceRepository;

  public Optional<Price> findPrice(Specification<PriceEntity> spec, Pageable pageable) {
    Page<PriceEntity> pricePage = springDataH2PriceRepository.findAll(spec, pageable);
    if (!pricePage.isEmpty()) {
      return pricePage.map(PriceEntity::toDomainPrice).stream().findFirst();
    } else {
      return Optional.empty();
    }
  }

}
