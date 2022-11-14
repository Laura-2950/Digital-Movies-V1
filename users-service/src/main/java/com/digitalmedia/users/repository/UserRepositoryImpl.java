package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.UserKeycloak;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository

public class UserRepositoryImpl implements IUserRepository{

  private final IUserRepository userRepository;
  private final Keycloak keycloakClient;

  @Autowired
  public UserRepositoryImpl(IUserRepository userRepository, Keycloak keycloakClient) {
    this.userRepository = userRepository;
    this.keycloakClient = keycloakClient;
  }

  @Value("${users-service-internal.realm}")
  private String realm;


  public User validateAndGetUser(String username) {
    return  userRepository.validateAndGetUser(username);
  }

  public Optional<User> getUserExtra(String username) {
    return userRepository.getUserExtra(username);
  }

  public User saveUserExtra(User user) {
    return userRepository.saveUserExtra(user);
  }

  public Optional<UserKeycloak> findByUserName(String userName) {
    UserRepresentation user= keycloakClient.realm(realm).users().search(userName).get(0);
    return Optional.of(toUser(user));
  }

  @Override
  public List<UserKeycloak> findAll() {
    List<UserRepresentation> users= keycloakClient.realm(realm).users().list();
    return users.stream().map(userRepresentation -> toUser(userRepresentation)).collect(Collectors.toList());
  }

  //TODO falta chekear si el grupo admin va con mayúsculas
  @Override
  public List<UserKeycloak> findAllNonAdmin() {
    return keycloakClient
            .realm(realm)
            .users()
            .list()
            .stream()
            .filter(userRepresentation ->
                    userRepresentation.getGroups().stream().noneMatch(s -> Objects.equals(s, "admin")))
            .map(this::toUser).collect(Collectors.toList());
  }

  @Override
  public Optional<UserKeycloak> findById(String id) {
    UserRepresentation user=keycloakClient.realm(realm).users().get(id).toRepresentation();
    return Optional.of(toUser(user));
  }


  private UserKeycloak toUser(UserRepresentation userRepresentation){
    return new UserKeycloak(userRepresentation.getId(), userRepresentation.getUsername(), userRepresentation.getEmail(), userRepresentation.getFirstName(), userRepresentation.getLastName());
  }
}
