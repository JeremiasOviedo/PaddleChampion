package com.jeremias.paddlechampion.auth.controller;

import com.jeremias.paddlechampion.auth.dto.*;
import com.jeremias.paddlechampion.auth.service.JwtUtils;
import com.jeremias.paddlechampion.auth.service.UserDetailsCustomService;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
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
public class AuthenticationController {

  private final UserDetailsCustomService userDetailsServices;
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtTokenUtils;



  @PostMapping("/register")
  @ApiOperation(value = "Register as a user",
          notes = "Create an account filling the form")
  public ResponseEntity<ResponseUserDto> signUp(@Valid @RequestBody UserRegistrationDto user) {
    ResponseUserDto userRegister = this.userDetailsServices.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userRegister);
  }


  @PostMapping("/registerAdmin")
  @ApiOperation(value = "Register as an Admin",
          notes = "Create an account filling the form, the role will be Admin in this case")
  public ResponseEntity<ResponseUserDto> signUpAdmin(@Valid @RequestBody UserRegistrationDto user) {

    ResponseUserDto userRegister = this.userDetailsServices.saveAdmin(user);
   return ResponseEntity.status(HttpStatus.CREATED).body(userRegister);
  }

  @PostMapping("/login")
  @ApiOperation(value = "Login using your email and password",
          notes = "This will retrieve a Json Web Token, granting you access to the endpoints, you need to copy and paste" +
                  " the JWT in the authorization section above the controllers, prepending the word Bearer before the token")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Invalid credentials"),
          @ApiResponse(code = 404, message = "User not found")})
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
