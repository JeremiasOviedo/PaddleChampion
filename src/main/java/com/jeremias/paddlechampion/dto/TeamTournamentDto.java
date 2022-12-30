package com.jeremias.paddlechampion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamTournamentDto {

  private String team;
  private Integer position;
  private Integer points;
  private Integer matchesPlayed;
  private Integer matchesWon;
  private Integer matchesLost;


}
