package com.inditex.challenge.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Price {

	private Long id;

	private Long brandId;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Long productId;

	private Integer priority;

	private Double price;

	private String currency;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Price price1 = (Price) o;
		return Objects.equals(id, price1.id) && Objects.equals(brandId, price1.brandId)
				&& Objects.equals(startDate, price1.startDate) && Objects.equals(endDate, price1.endDate)
				&& Objects.equals(productId, price1.productId) && Objects.equals(priority, price1.priority)
				&& Objects.equals(price, price1.price) && Objects.equals(currency, price1.currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, brandId, startDate, endDate, productId, priority, price, currency);
	}
}
