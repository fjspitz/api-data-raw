package ar.com.apostoles.turing.api.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Transaction {
  private Timestamp date;
  private long units;
}
