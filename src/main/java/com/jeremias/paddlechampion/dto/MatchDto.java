package com.jeremias.paddlechampion.dto;

import com.jeremias.paddlechampion.enumeration.Status;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDto {

  private Long matchId;
  private String teamA;
  private String teamB;
  private Status status;
  private Integer teamAScore;
  private Integer teamBScore;
  private String winner;
  private List<TeamDto> teams;

}
