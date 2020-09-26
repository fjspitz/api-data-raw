package ar.com.apostoles.turing.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
  private long id;
  private String name;
}
