package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.UserKeycloak;
import com.digitalmedia.users.model.dto.UserRequest;
import com.digitalmedia.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  @Value("${server.port}")
  private int serverPort;
  private final IUserService userService;

  @GetMapping("/me")
  public User getUserExtra(Principal principal) {
    return userService.validateAndGetUserExtra(principal.getName());
  }

  @PostMapping("/me")
  public User saveUserExtra(@Valid @RequestBody UserRequest updateUserRequest, Principal principal) {
    Optional<User> userOptional = userService.getUserExtra(principal.getName());
    User userExtra = userOptional.orElseGet(() -> new User(principal.getName()));
    userExtra.setAvatar(updateUserRequest.getAvatar());
    return userService.saveUserExtra(userExtra);
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('GROUP_admin')")
  public ResponseEntity<List<UserKeycloak>> findByUserName() {
    return ResponseEntity.ok().body(userService.findAllNonAdmin());
  }

  @GetMapping("/{userName}")
  public ResponseEntity<Optional<UserKeycloak>> findByUserName(@RequestParam String userName, HttpServletResponse response) {
    response.addHeader("port", String.valueOf(serverPort));
    return ResponseEntity.ok().body(userService.getUserByUserName(userName));
  }

  @GetMapping
  public List<UserKeycloak> findAll(){
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<UserKeycloak> findById(@PathVariable String id){
    return userService.findById(id);
  }

}
