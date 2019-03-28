package com.project.DataProject;



import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

  private int product_id;
  private String product_name;
  private String description;
  private String price;
  private int category_id;

  public Product() {
  }

  public Product(int id, String product_name, String description, String price, int category_id) {
    this.product_id = id;
    this.product_name = product_name;
    this.description = description;
    this.price = price;
    this.category_id = category_id;
  }


  public int getProduct_id() {
    return product_id;
  }

  public void setProduct_id(int product_id) {
    this.product_id = product_id;
  }

  /*public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }
  */
  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public int getCategory_id() {
    return category_id;
  }

  public void setCategory_id(int category_id) {
    this.category_id = category_id;
  }

  @Override
  public String toString() {
    return "Product{" +
            "product_id=" + product_id +
            ", product_name='" + product_name + '\'' +
            ", description='" + description + '\'' +
            ", price='" + price + '\'' +
            ", category_id=" + category_id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return product_id == product.product_id;
  }


}
