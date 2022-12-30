package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.dto.TeamTournamentDto;
import com.jeremias.paddlechampion.entity.TeamTournament;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TeamTournamentMap {

  public TeamTournamentDto teamTournamentEntity2Dto(TeamTournament entity) {

    TeamTournamentDto dto = new TeamTournamentDto();

    dto.setTeam(entity.getTeam().getName());
    dto.setPoints(entity.getPoints());
    dto.setMatchesPlayed(entity.getMatchesPlayed());
    dto.setMatchesWon(entity.getMatchesWon());
    dto.setMatchesLost(entity.getMatchesLost());

    return dto;
  }

  public List<TeamTournamentDto> teamTournamentList2Dto(List<TeamTournament> entities) {

    List<TeamTournamentDto> dtos = new ArrayList<>();

    for (TeamTournament teamTournament : entities) {

      dtos.add(teamTournamentEntity2Dto(teamTournament));

    }

    return dtos;
  }

}
