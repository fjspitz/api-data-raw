package ar.com.apostoles.turing.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.apostoles.turing.api.dao.ProductDao;
import ar.com.apostoles.turing.api.model.Product;
import ar.com.apostoles.turing.api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductDao repository;
  
  @Override
  public List<Product> findAll() {
    return repository.findAll();
  }

}
