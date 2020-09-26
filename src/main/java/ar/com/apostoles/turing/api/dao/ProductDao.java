package ar.com.apostoles.turing.api.dao;

import java.util.List;

import ar.com.apostoles.turing.api.model.Product;

public interface ProductDao {

  List<Product> findAll();
}
