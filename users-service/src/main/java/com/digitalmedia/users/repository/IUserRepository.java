package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.UserKeycloak;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

  User validateAndGetUser(String username);

  Optional<User> getUserExtra(String username);

  User saveUserExtra(User userExtra);

  Optional<UserKeycloak> findByUserName(String userName);

  public List<UserKeycloak> findAll();

  List<UserKeycloak> findAllNonAdmin();

  Optional<UserKeycloak> findById(String id);
}
