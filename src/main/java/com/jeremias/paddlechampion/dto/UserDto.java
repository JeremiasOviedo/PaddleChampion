package com.jeremias.paddlechampion.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Integer category;
  private Set<TeamDto> teams;

}
