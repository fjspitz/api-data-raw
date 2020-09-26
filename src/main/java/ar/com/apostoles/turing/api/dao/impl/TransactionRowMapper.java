package ar.com.apostoles.turing.api.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ar.com.apostoles.turing.api.model.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction> {

  @Override
  public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
    
    Transaction trx = new Transaction();
    trx.setDate(rs.getTimestamp("date"));
    trx.setUnits(rs.getLong("units"));

    return trx;
  }

}
