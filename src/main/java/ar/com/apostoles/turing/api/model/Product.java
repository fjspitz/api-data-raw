package ar.com.apostoles.turing.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
  private long sku;
  private String description;
}
