package ar.com.apostoles.turing.api.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseBySalesPerWeekDto {
  private List<TransactionPerWeek> sales;
  private long rows;
}
