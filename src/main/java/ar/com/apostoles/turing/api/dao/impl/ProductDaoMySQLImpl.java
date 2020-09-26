package ar.com.apostoles.turing.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ar.com.apostoles.turing.api.dao.ProductDao;
import ar.com.apostoles.turing.api.model.Product;

@Repository
public class ProductDaoMySQLImpl implements ProductDao {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Product> findAll() {
    return jdbcTemplate.query("select s.sku, p.description from `sales` s inner join `products` p on (s.sku = p.sku) group by 1, 2 order by 1", 
        (rs, rowNum) -> new Product(
            rs.getLong("sku"),
            rs.getString("description")));
  }

}
