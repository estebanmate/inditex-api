package com.inditex.challenge.infrastructure.repository.h2.entities;

import com.inditex.challenge.domain.entities.Price;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
public class PriceEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private Long id;

  @Column(name = "BRAND_ID")
  private Long brandId;

  @Column(name = "PRODUCT_ID")
  private Long productId;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "PRICE")
  private Double price;

  @Column(name = "CURRENCY", length = 3)
  private String currency;

  public Price toDomainPrice() {
    return new Price(this.id, this.brandId, this.startDate, this.endDate, this.productId,
        this.priority, this.price,
        this.currency);
  }
}
