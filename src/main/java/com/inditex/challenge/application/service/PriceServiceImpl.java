package com.inditex.challenge.application.service;

import com.inditex.challenge.application.request.PriceRequest;
import com.inditex.challenge.application.response.PriceResponse;
import com.inditex.challenge.application.rest.exception.type.BaseException;
import com.inditex.challenge.application.rest.exception.type.DataNotFoundException;
import com.inditex.challenge.application.rest.mapper.PriceMapper;
import com.inditex.challenge.application.rest.resources.ErrorMessages;
import com.inditex.challenge.application.request.PriceRequestValidation;
import com.inditex.challenge.domain.entities.Price;
import com.inditex.challenge.domain.repository.PriceRepository;
import com.inditex.challenge.domain.service.PriceService;
import com.inditex.challenge.infrastructure.repository.h2.entities.PriceEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class PriceServiceImpl implements PriceService {
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  @Inject
  PriceRepository priceRepository;
  @Inject
  PriceMapper priceMapper;

  private static Specification<PriceEntity> getPricesSpecification(PriceRequest priceRequest) {
    LocalDateTime priceDate = LocalDateTime.parse(priceRequest.getPriceDate(), dateTimeFormatter);

    return (Root<PriceEntity> priceEntityRoot, CriteriaQuery<?> criteriaQuery,
            CriteriaBuilder criteriaBuilder) -> {

      List<Predicate> predicates = new ArrayList<>();

      // Create filter in sql sentence
      predicates.add(criteriaBuilder.equal(priceEntityRoot.get("brandId"), priceRequest.getBrandId()));
      predicates.add(criteriaBuilder.equal(priceEntityRoot.get("productId"), priceRequest.getProductId()));
      predicates.add(criteriaBuilder.greaterThanOrEqualTo(priceEntityRoot.get("endDate"), priceDate));
      predicates.add(criteriaBuilder.lessThanOrEqualTo(priceEntityRoot.get("startDate"), priceDate));

      return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    };
  }

  @Override
  public PriceResponse findPrice(PriceRequest priceRequest) throws BaseException {

    log.info("Search Data with Request -> [{}]", priceRequest);

    // Validate request data format and values
    PriceRequestValidation.validatePriceFilterRequest(priceRequest);

    Specification<PriceEntity> specification = getPricesSpecification(
            priceRequest);

    Optional<Price> activePrice = priceRepository
        .findPrice(specification, PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority")));

    if (activePrice.isEmpty()) {
      log.info("Price Data not found..");
      throw new DataNotFoundException(ErrorMessages.NOT_FOUND_DESCRIPTION_ERROR_MESSAGE);
    }

    // Map domain object to response
    return priceMapper.activePriceToResponse(activePrice.get());
  }
}
