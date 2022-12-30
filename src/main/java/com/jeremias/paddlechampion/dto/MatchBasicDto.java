package com.jeremias.paddlechampion.dto;

import com.jeremias.paddlechampion.enumeration.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchBasicDto {

  private Long id;
  private String teamA;
  private String teamB;
  private Status status;
  private String winner;

}
