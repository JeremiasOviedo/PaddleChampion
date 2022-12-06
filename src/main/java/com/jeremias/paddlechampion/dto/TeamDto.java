package com.jeremias.paddlechampion.dto;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDto {

  private Long id;
  private String name;
  private Integer category;
  private List<UserDto> players;

}
