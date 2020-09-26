package ar.com.apostoles.turing.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.apostoles.turing.api.dao.LocationDao;
import ar.com.apostoles.turing.api.model.Location;
import ar.com.apostoles.turing.api.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

  @Autowired
  private LocationDao repository;
  
  @Override
  public List<Location> findBySku(Long sku) {
    return repository.findBySku(sku);
  }
}
