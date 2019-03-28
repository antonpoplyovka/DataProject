package com.project.DataProject.DataBase;

import com.project.DataProject.Category;
import com.project.DataProject.Product;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO extends JdbcDaoSupport {
  public CategoryDAO(){
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setUrl("jdbc:h2:file:~/test");
    ds.setUsername("sa");
    ds.setPassword("");

    this.setDataSource(ds);
  }
  public List<Category> getCategories(){
    String sql = CategoryMapper.BASE_SQL;
    Object[] params = new Object[]{};
    CategoryMapper mapper = new CategoryMapper();
    List<Category> list= this.getJdbcTemplate().query(sql,params, mapper);

    return list;
  }
}
