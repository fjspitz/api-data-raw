package ar.com.apostoles.turing.api.dao;

import java.util.List;

import ar.com.apostoles.turing.api.model.Location;

public interface LocationDao {
  
  List<Location> findBySku(Long sku);
}
