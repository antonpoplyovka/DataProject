package com.project.DataProject.controller;

import com.project.DataProject.Category;
import com.project.DataProject.DataBase.CategoryDAO;
import com.project.DataProject.DataBase.ProductDAO;
import com.project.DataProject.Product;
import com.project.DataProject.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {
  @GetMapping
  public List<Category>  list(){
    return new CategoryDAO().getCategories();
  }


}
