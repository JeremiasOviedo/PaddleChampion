package com.jeremias.paddlechampion.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email(message = "the email must be real email")
    private String email;
    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotEmpty
    private String passwordConfirm;

    private Integer category;
}
