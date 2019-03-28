package com.project.DataProject.controller;

import com.project.DataProject.DataBase.ProductDAO;
import com.project.DataProject.Product;
import com.project.DataProject.exceptions.NotFoundException;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("product")
public class ProductController {

  @GetMapping
  public List<Product>  list(){
    return new ProductDAO().getProducts();
  }
  @GetMapping("{id}")
  public Product getOne (@PathVariable int id){
    return new ProductDAO().findProduct(id);

  }

  @PostMapping
  public Product create(@RequestBody Product product){
      System.out.println(product.toString()+"Controller");
      Product productDAO = new ProductDAO().createProduct(product);
      Product newProduct = new Product();
      newProduct = getOne(productDAO.getProduct_id());
      return newProduct;
  }

  @PutMapping("{id}")
  public Product update(@PathVariable int id, @RequestBody Product product){
  System.out.println("ok");
    return new ProductDAO().updateProduct(id,product);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable int id){

    Product newProduct = new Product();
    newProduct=getOne(id);
    //products.remove(newProduct);
  }
}
