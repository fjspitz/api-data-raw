package ar.com.apostoles.turing.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ar.com.apostoles.turing.api.dao.LocationDao;
import ar.com.apostoles.turing.api.model.Location;

@Repository
public class LocationDaoMySQLImpl implements LocationDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Override
  public List<Location> findBySku(Long sku) {
    
    return jdbcTemplate.query("select s.loc_id, l.loc_name from `sales` s inner join `locations` l "
        + "on (l.loc_id = s.loc_id) where sku = ? group by 1, 2 order by 1;", 
        new Object[] { sku },
        (rs, rowNum) -> new Location(
            rs.getLong("loc_id"),
            rs.getString("loc_name")));
  }
}
