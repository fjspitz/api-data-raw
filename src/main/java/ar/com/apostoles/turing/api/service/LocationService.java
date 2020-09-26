package ar.com.apostoles.turing.api.service;

import java.util.List;

import ar.com.apostoles.turing.api.model.Location;

public interface LocationService {
  
  List<Location> findBySku(Long sku);
}
