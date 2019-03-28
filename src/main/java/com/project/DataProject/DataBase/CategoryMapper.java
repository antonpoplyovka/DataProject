package com.project.DataProject.DataBase;

import com.project.DataProject.Category;
import com.project.DataProject.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class CategoryMapper implements RowMapper<Category> {
  public static final String BASE_SQL="Select * from Category";
  @Override
  public Category mapRow(ResultSet resultSet, int i) throws SQLException {
    int id = resultSet.getInt(1);
    String category_name = resultSet.getString(2);
    String description = resultSet.getString(3);

    return new Category(id,category_name,description);
  }

}
