package ar.com.apostoles.turing.api.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ar.com.apostoles.turing.api.model.TransactionPerWeek;

public class TransactionPerWeekRowMapper implements RowMapper<TransactionPerWeek> {

  @Override
  public TransactionPerWeek mapRow(ResultSet rs, int rowNum) throws SQLException {
    
    TransactionPerWeek trx = new TransactionPerWeek();
    trx.setYear(rs.getInt("year"));
    trx.setWeekNumber(rs.getInt("week_nro"));
    trx.setLocal(rs.getLong("loc_id"));
    trx.setUnits(rs.getLong("units"));

    return trx;
  }
}