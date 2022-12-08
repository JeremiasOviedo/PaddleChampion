package com.jeremias.paddlechampion.auth.controller;

import com.jeremias.paddlechampion.auth.dto.AuthenticationRequest;
import com.jeremias.paddlechampion.auth.dto.AuthenticationResponse;
import com.jeremias.paddlechampion.auth.dto.ResponseUserDto;
import com.jeremias.paddlechampion.auth.dto.UserAuthDto;
import com.jeremias.paddlechampion.auth.service.JwtUtils;
import com.jeremias.paddlechampion.auth.service.UserDetailsCustomService;
import com.jeremias.paddlechampion.auth.service.UserDetailsImpl;
import com.jeremias.paddlechampion.service.IUserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

  private final UserDetailsCustomService userDetailsServices;
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtTokenUtils;



  @PostMapping("/register")
  public ResponseEntity<ResponseUserDto> signUp(@Valid @RequestBody ResponseUserDto user) {
    ResponseUserDto userRegister = this.userDetailsServices.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userRegister);
  }


  @PostMapping("/registerAdmin")
  public ResponseEntity<AuthenticationResponse> signUpAdmin(@Valid @RequestBody UserAuthDto user) {
    this.userDetailsServices.saveAdmin(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> signIn(
      @RequestBody AuthenticationRequest authenticationRequest) {

    UserDetails userDetails;

    Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
            authenticationRequest.getPassword())
    );
    userDetails = (UserDetails) auth.getPrincipal();

    final String jwt = jwtTokenUtils.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }


}
