package com.inditex.challenge.application.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
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
public class PriceRequest implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Schema(description = "Product Id", example = "35455", required = true)
  private Long productId;

  @Schema(description = "Brand Id", example = "1", required = true)
  private Long brandId;

  @Schema(description = "Valid Price Date", example = "2020-06-14 10:00:00", required = true)
  private String priceDate;

}
