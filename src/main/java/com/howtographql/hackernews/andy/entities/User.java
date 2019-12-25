package com.howtographql.hackernews.andy.entities;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Objects;
import java.lang.Integer;
import java.lang.String;
import java.lang.String;
import java.lang.String;

@Data
public class User {

  public User() {}

  private Integer id;


  private String name;


  private String email;


  private String password;


  private Integer userLinkId;


  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof User) {
      final User other = (User) obj;
      return Objects.equals(id, other.id);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
