package com.project.DataProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

  private int id;
  private String category_name;
  private String descriprion;

  public Category() {
  }

  public Category(int id, String category_name, String descriprion) {
    this.id = id;
    this.category_name = category_name;
    this.descriprion = descriprion;
  }

  public String getDescriprion() {
    return descriprion;
  }

  public void setDescriprion(String descriprion) {
    this.descriprion = descriprion;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCategory_name() {
    return category_name;
  }

  public void setCategory_name(String category_name) {
    this.category_name = category_name;
  }
}
