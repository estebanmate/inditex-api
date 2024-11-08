package com.inditex.challenge.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Schema(description = "Price Id")
  private Long id;

  @Schema(description = "Product Id")
  private Long productId;

  @Schema(description = "Brand Id")
  private Long brandId;

  @Schema(description = "Price List Start Date", pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startDate;

  @Schema(description = "Price List End Date", pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endDate;

  @Schema(description = "Price")
  private Double price;

  @Schema(description = "Currency")
  private String currency;

}
