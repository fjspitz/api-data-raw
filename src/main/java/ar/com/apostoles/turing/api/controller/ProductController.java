package ar.com.apostoles.turing.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ar.com.apostoles.turing.api.model.Product;
import ar.com.apostoles.turing.api.service.ProductService;

@CrossOrigin
@RestController
public class ProductController {
  
  @Autowired
  private ProductService productService;
  
  @ResponseBody
  @GetMapping("/products")
  public ResponseEntity<List<Product>> findAllProducts() {
    
    try {
      List<Product> products = productService.findAll();
      
      return new ResponseEntity<>(products, HttpStatus.OK);
      
    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found", e);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

}
