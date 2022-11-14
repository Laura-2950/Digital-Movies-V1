package com.digitalmedia.users.repository;

import com.digitalmedia.users.exceptions.UserExtraNotFoundException;
import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.UserKeycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoUserRepository implements IUserRepository{

  private IMongoUserRepository mongoUserRepository;

  @Autowired
  public MongoUserRepository(IMongoUserRepository mongoUserRepository) {
    this.mongoUserRepository = mongoUserRepository;
  }

  @Override
  public User validateAndGetUser(String username) {
    return  getUserExtra(username).orElseThrow(() -> new UserExtraNotFoundException(username));
  }

  @Override
  public Optional<User> getUserExtra(String username) {
    return mongoUserRepository.findById(username);
  }

  @Override
  public User saveUserExtra(User user) {
    return  mongoUserRepository.save(user);
  }

  @Override
  public Optional<UserKeycloak> findByUserName(String userName) {
    return Optional.empty();
  }

  @Override
  public List<UserKeycloak> findAll() {
    return null;
  }

  @Override
  public List<UserKeycloak> findAllNonAdmin() {
    return null;
  }

  @Override
  public Optional<UserKeycloak> findById(String id) {
    return Optional.empty();
  }

}