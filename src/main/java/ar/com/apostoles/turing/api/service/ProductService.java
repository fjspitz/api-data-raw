package ar.com.apostoles.turing.api.service;

import java.util.List;

import ar.com.apostoles.turing.api.model.Product;

public interface ProductService {

  List<Product> findAll();
}
