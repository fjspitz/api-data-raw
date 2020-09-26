package ar.com.apostoles.turing.api.model;

import lombok.Data;

@Data
public class TransactionPerWeek {
  private int year;
  private int weekNumber;
  private long units;
}
