package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.UserKeycloak;
import com.digitalmedia.users.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

  private final UserRepositoryImpl userRepository;


  @Override
  public User validateAndGetUserExtra(String username) {
    return userRepository.validateAndGetUser(username);
  }

  @Override
  public Optional<User> getUserExtra(String username) {
    return userRepository.getUserExtra(username);
  }

  @Override
  public User saveUserExtra(User user) {
    return userRepository.saveUserExtra(user);
  }

  @Override
  public Optional<UserKeycloak> getUserByUserName(String username) {
    return userRepository.findByUserName(username);
  }
  @Override
  public List<UserKeycloak> findAll(){
    return userRepository.findAll();
  }

  @Override
  public Optional<UserKeycloak> findById(String id){
    return userRepository.findById(id);
  }

  @Override
  public List<UserKeycloak> findAllNonAdmin(){
    return userRepository.findAllNonAdmin();
  }

}
