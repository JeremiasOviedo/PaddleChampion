package com.jeremias.paddlechampion.controller;


import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.repository.UserRepository;
import com.jeremias.paddlechampion.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserServiceImpl userService;
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @PostMapping("/register")
  public ResponseEntity<UserDto> singUp(@RequestBody UserDto user) {

    UserDto dto = userService.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
  }

}
