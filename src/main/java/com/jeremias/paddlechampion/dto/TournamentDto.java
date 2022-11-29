package com.jeremias.paddlechampion.dto;

import com.jeremias.paddlechampion.enumeration.Inscription;
import com.jeremias.paddlechampion.model.Match;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentDto {

  private Long id;
  private String name;
  private Integer MaxCategory;
  private Integer MaxTeams;
  private Inscription inscriptionStatus;
  private Set<TeamDto> teams;
  private Set<Match> matches;

}
