package com.jeremias.paddlechampion.model;

import com.jeremias.paddlechampion.entity.TeamEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {

  private TeamEntity teamA;
  private TeamEntity teamB;

  private Integer teamAScore;
  private Integer teamBScore;

  private TeamEntity winner;


}
