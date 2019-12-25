package com.howtographql.hackernews.andy.service;

import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.entities.User;

import java.util.List;

public interface UserService {
  User update(Integer userId, User user);

  User get(Integer userId);

  User create(User user);

  void delete(Integer userId);
}
