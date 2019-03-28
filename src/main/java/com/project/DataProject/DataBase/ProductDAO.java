package com.project.DataProject.DataBase;

import com.project.DataProject.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public class ProductDAO extends JdbcDaoSupport {
  @Autowired
  public ProductDAO(){
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setUrl("jdbc:h2:file:~/test");
    ds.setUsername("sa");
    ds.setPassword("");

    this.setDataSource(ds);
  }
  public List<Product> getProducts(){
    String sql = ProductMapper.BASE_SQL;
    Object[] params = new Object[]{};
    ProductMapper mapper = new ProductMapper();
    List<Product> list= this.getJdbcTemplate().query(sql,params, mapper);

    return list;
  }
  public Product findProduct(int id) {
    String sql = ProductMapper.BASE_SQL + " where PRODUCT_ID = ? ";
    Object[] params = new Object[] { id };
    ProductMapper mapper = new ProductMapper();
    try {
      Product product = this.getJdbcTemplate().queryForObject(sql, params, mapper);
      return product;
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  public Product updateProduct(int id, Product newProduct)
  {
    String sql = "UPDATE PRODUCT SET PRODUCT_NAME='"+newProduct.getProduct_name()+
                  "',DESCRIPTION='"+ newProduct.getDescription()+
                  "',PRICE='" + newProduct.getPrice()+ "' WHERE product_Id='"+id+"'";
    this.getJdbcTemplate().update(sql);
    return newProduct;
  }
  public Product createProduct( Product newProduct){
    String sqlCounter="SELECT MAX(PRODUCT_ID) FROM PRODUCT";
    //int a=Integer.parseInt((String) this.getJdbcTemplate().queryForList(sqlCounter).get(0).get(0));



    Object[] args = null;
    Number number = this.getJdbcTemplate().queryForObject(sqlCounter, args, Integer.class);
    System.out.println(number);


     System.out.println(this.getJdbcTemplate().queryForList(sqlCounter).get(0).get("MAX(Product_id)"));
    String sql = "INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_NAME, DESCRIPTION, PRICE, PRODUCT_CATEGORY) VALUES (?,?,?,?,?)";

    int maxId= number.intValue();
    this.getJdbcTemplate().update(sql,maxId+1,newProduct.getProduct_name(),newProduct.getDescription(),newProduct.getPrice(),newProduct.getCategory_id());//  update(sql);
    System.out.println(maxId);

    Product productWithId = new Product(maxId+1,newProduct.getProduct_name(),newProduct.getDescription(),newProduct.getPrice(),newProduct.getCategory_id());
    System.out.println(productWithId.toString());
    return productWithId;

  }
  public void deleteProduct(int id)
  {
    String sql = "DELETE FROM PRODUCT WHERE ID="+id;
    this.getJdbcTemplate().update(sql);
  }
}
