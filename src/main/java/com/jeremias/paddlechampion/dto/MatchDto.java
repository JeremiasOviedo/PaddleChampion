package com.jeremias.paddlechampion.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDto {

  private Long matchId;
  private String teamA;
  private String teamB;
  private List<TeamDto> teams;

}
