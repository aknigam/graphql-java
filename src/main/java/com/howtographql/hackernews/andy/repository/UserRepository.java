package com.howtographql.hackernews.andy.repository;

import com.howtographql.hackernews.andy.entities.User;

import java.util.List;

public interface UserRepository {

  void updateUser(User user);

  User getUser(Integer userId);

  void createUser(User user);

  void deleteUser(Integer userId);

  List<User> getUsersByLink(Integer linkId);
}
