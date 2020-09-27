package ar.com.apostoles.turing.api.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ar.com.apostoles.turing.api.dao.TransactionDao;
import ar.com.apostoles.turing.api.model.Transaction;
import ar.com.apostoles.turing.api.model.TransactionPerWeek;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TransactionDaoMySQLImpl implements TransactionDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Override
  public List<Transaction> findBySkuAndLocal(Long sku, Long local, Timestamp since, Timestamp to) {
    log.info("Sku: {}, Local: {}, Since: {}, To: {}", sku, local, since, to);
    
    return jdbcTemplate.query(
        "SELECT date, units FROM `sales` WHERE sku = ? AND loc_id = ? AND date BETWEEN ? AND ?", 
        new Object[] { sku, local, since, to }, new TransactionRowMapper());
  }

  @Override
  public List<TransactionPerWeek> findBySkuAndLocalPerWeek(Long sku, Long local, Timestamp since, Timestamp to) {
    log.info("Sku: {}, Local: {}, Since: {}, To: {}", sku, local, since, to);
    
    if (local == null) {
      return jdbcTemplate.query(
          "SELECT YEAR(date) as year, WEEK(date) as week_nro, loc_id, SUM(units) as units FROM `sales` WHERE sku = ? AND date BETWEEN ? and ? GROUP BY 1, 2, 3", 
          new Object[] { sku, since, to }, new TransactionPerWeekRowMapper());
    } else {
      return jdbcTemplate.query(
          "SELECT YEAR(date) as year, WEEK(date) as week_nro, SUM(units) as units FROM `sales` WHERE sku = ? AND loc_id = ? AND date BETWEEN ? and ? GROUP BY 1, 2", 
          new Object[] { sku, local, since, to }, new TransactionPerWeekRowMapper());
    }
  }

  @Override
  public List<TransactionPerWeek> findAllBySkuAndLocalPerWeek(Long sku, Long local) {
    log.info("Sku: {}, Local: {}", sku, local);
    
    if (local == null) {
      return jdbcTemplate.query(
          "SELECT YEAR(date) as year, WEEK(date) as week_nro, loc_id, SUM(units) as units FROM `sales` WHERE sku = ? GROUP BY 1, 2, 3", 
          new Object[] { sku }, new TransactionPerWeekRowMapper());
    } else {
      return jdbcTemplate.query(
          "SELECT YEAR(date) as year, WEEK(date) as week_nro, loc_id, SUM(units) as units FROM `sales` WHERE sku = ? AND loc_id = ? GROUP BY 1, 2, 3", 
          new Object[] { sku, local }, new TransactionPerWeekRowMapper());
    }
  }
}
