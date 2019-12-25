package com.howtographql.hackernews.andy.repository;


import com.howtographql.hackernews.andy.mapper.*;
import com.howtographql.hackernews.andy.entities.User;

import java.util.List;

import org.apache.ibatis.session.SqlSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepositoryImpl implements UserRepository {

  public static Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
  private SqlSessionManager sessionManager;

  public UserRepositoryImpl(SqlSessionManager sesionManager) {
    this.sessionManager = sesionManager;
  }

  @Override
  public User getUser(Integer userId) {
    UserMapper userMapper = sessionManager.getMapper(UserMapper.class);
    User user = userMapper.getUser(userId);

    return user;
  }

  @Override
  public void updateUser(User user) {
    Integer userId = user.getId();
    UserMapper userMapper = sessionManager.getMapper(UserMapper.class);
    User existingUser = userMapper.getUser(userId);
    if (existingUser == null) {
      throw new RuntimeException(String.format("User with id {} does not exists", user.getId()));
    }
    userMapper.updateUser(user);
  }

  @Override
  public void createUser(User user) {
    Integer userId = user.getId();
    UserMapper userMapper = sessionManager.getMapper(UserMapper.class);
    userMapper.createUser(user);
  }

  @Override
  public void deleteUser(Integer userId) {
    UserMapper userMapper = sessionManager.getMapper(UserMapper.class);
    User user = userMapper.getUser(userId);
    if (user == null) {
      return;
    }

    userMapper.deleteUser(userId);
  }

  @Override
  public List<User> getUsersByLink(Integer linkId) {
    UserMapper userMapper = sessionManager.getMapper(UserMapper.class);
    return userMapper.getUsersByLink(linkId);
  }
}
