package com.project.DataProject.DataBase;

import com.project.DataProject.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public  class ProductMapper implements RowMapper<Product> {
  public static final String BASE_SQL = "Select * from product";

  @Override
  public Product mapRow(ResultSet resultSet, int i) throws SQLException {
    int product_id = resultSet.getInt(1);
    String product_name = resultSet.getString(2);
    String description = resultSet.getString(3);
    String price = resultSet.getString(4);
    int category_id = resultSet.getInt(5);
    return new Product(product_id,product_name,description,price,category_id);
  }


}
