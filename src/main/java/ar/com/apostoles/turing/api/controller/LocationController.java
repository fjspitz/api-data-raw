package ar.com.apostoles.turing.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ar.com.apostoles.turing.api.model.Location;
import ar.com.apostoles.turing.api.service.LocationService;

@CrossOrigin
@RestController
public class LocationController {
  
  @Autowired
  private LocationService locationService;

  @ResponseBody
  @GetMapping("/locations")
  public ResponseEntity<List<Location>> findAllLocations(@RequestParam("sku") long sku) {
    
    try {
      List<Location> locations = locationService.findBySku(sku);
      
      return new ResponseEntity<>(locations, HttpStatus.OK);
      
    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No locations found", e);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }
}
