package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.UserKeycloak;

import java.util.List;
import java.util.Optional;

public interface IUserService {
  User validateAndGetUserExtra(String username);

  Optional<User> getUserExtra(String username);

  User saveUserExtra(User userExtra);

  Optional<UserKeycloak> getUserByUserName(String username);

  List<UserKeycloak> findAll();

  List<UserKeycloak> findAllNonAdmin();

  Optional<UserKeycloak> findById(String id);

}
