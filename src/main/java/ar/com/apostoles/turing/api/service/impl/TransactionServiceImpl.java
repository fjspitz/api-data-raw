package ar.com.apostoles.turing.api.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.apostoles.turing.api.dao.TransactionDao;
import ar.com.apostoles.turing.api.model.Transaction;
import ar.com.apostoles.turing.api.model.TransactionPerWeek;
import ar.com.apostoles.turing.api.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private TransactionDao repository;
  
  @Override
  public List<Transaction> findBySkuAndLocal(Long sku, Long local, Timestamp since, Timestamp to) {
    return repository.findBySkuAndLocal(sku, local, since, to);
  }

  @Override
  public List<TransactionPerWeek> findBySkuAndLocalPerWeek(Long sku, Long local, Timestamp since, Timestamp to) {
    return repository.findBySkuAndLocalPerWeek(sku, local, since, to);
  }
  
  @Override
  public List<TransactionPerWeek> findAllBySkuAndLocalPerWeek(Long sku, Long local) {
    return repository.findAllBySkuAndLocalPerWeek(sku, local);
  }
}
