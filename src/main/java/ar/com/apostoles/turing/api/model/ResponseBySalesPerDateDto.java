package ar.com.apostoles.turing.api.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseBySalesPerDateDto {
  private List<Transaction> sales;
  private long rows;
  
}
