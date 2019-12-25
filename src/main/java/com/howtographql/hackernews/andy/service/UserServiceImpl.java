package com.howtographql.hackernews.andy.service;

import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.entities.User;

import com.howtographql.hackernews.andy.service.UserService;
import com.howtographql.hackernews.andy.repository.UserRepository;
import java.util.List;

import org.apache.ibatis.session.SqlSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {
  public static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserRepository userRepository;
  private SqlSessionManager sessionManager;

  public UserServiceImpl(UserRepository userRepo, SqlSessionManager sesionManager) {
    this.userRepository = userRepo;
    this.sessionManager = sesionManager;
  }

  @Override
  public User update(Integer userId, User user) {
    sessionManager.startManagedSession();
    try {
      userRepository.updateUser(user);
      sessionManager.commit();
      return userRepository.getUser(userId);

    } finally {
      sessionManager.close();
    }
  }

  @Override
  public User get(Integer userId) {
    sessionManager.startManagedSession();
    try {
      return userRepository.getUser(userId);

    } finally {
      sessionManager.close();
    }
  }

  @Override
  public User create(User user) {
    sessionManager.startManagedSession();
    try {
      userRepository.createUser(user);
      sessionManager.commit();
      return userRepository.getUser(user.getId());

    } finally {
      sessionManager.close();
    }
  }

  @Override
  public void delete(Integer userId) {
    sessionManager.startManagedSession();
    try {
      userRepository.deleteUser(userId);
      sessionManager.commit();
    } finally {
      sessionManager.close();
    }
  }
}
