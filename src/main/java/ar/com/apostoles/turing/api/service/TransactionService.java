package ar.com.apostoles.turing.api.service;

import java.sql.Timestamp;
import java.util.List;

import ar.com.apostoles.turing.api.model.Transaction;
import ar.com.apostoles.turing.api.model.TransactionPerWeek;

public interface TransactionService {

  public List<Transaction> findBySkuAndLocal(Long sku, Long local, Timestamp since, Timestamp to);
  
  public List<TransactionPerWeek> findBySkuAndLocalPerWeek(Long sku, Long local, Timestamp since, Timestamp to);
  
  public List<TransactionPerWeek> findAllBySkuAndLocalPerWeek(Long sku, Long local);
}
