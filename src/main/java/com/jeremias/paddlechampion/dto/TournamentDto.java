package com.jeremias.paddlechampion.dto;

import com.jeremias.paddlechampion.enumeration.Inscription;
import com.jeremias.paddlechampion.model.Match;
import java.util.List;

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
  private List<TeamDto> teams;
  private List<Match> matches;
  private UserDto creator;
}
