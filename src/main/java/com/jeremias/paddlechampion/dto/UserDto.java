package com.jeremias.paddlechampion.dto;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Integer category;
  private List<TeamDto> teams;


}
