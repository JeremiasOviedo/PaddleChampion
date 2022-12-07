package com.jeremias.paddlechampion.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAuthDto {

  @Email(message = "Must be an valid email")
  private String email;
  @Size(min = 8)
  private String password;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;

}
