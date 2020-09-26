package ar.com.apostoles.turing.api.dao;

import java.sql.Timestamp;
import java.util.List;

import ar.com.apostoles.turing.api.model.Transaction;
import ar.com.apostoles.turing.api.model.TransactionPerWeek;

public interface TransactionDao {
  List<Transaction> findBySkuAndLocal(Long sku, Long local, Timestamp since, Timestamp to);
  
  List<TransactionPerWeek> findBySkuAndLocalPerWeek(Long sku, Long local, Timestamp since, Timestamp to);

  List<TransactionPerWeek> findAllBySkuAndLocalPerWeek(Long sku, Long local);
  
}
