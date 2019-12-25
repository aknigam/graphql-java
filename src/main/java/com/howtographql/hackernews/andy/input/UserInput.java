package com.howtographql.hackernews.andy.input;

import lombok.Data;

import java.util.Objects;

@Data
public class UserInput {

  public UserInput() {}


  private String name;


  private String email;


  private String password;


  private Integer userLinkId;


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

}
