package com.jeremias.paddlechampion.service;


import com.jeremias.paddlechampion.dto.MatchBasicDto;
import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ITournamentService {

  TournamentDto createTournament(TournamentDto dto);

 void startTournament(Long id);

  TournamentDto getTournament(Long tournamentId);

  void addTeam(Long tournamentId, Long teamId);

  void deleteTeam(Long tournamentId, Long teamId);

  List<MatchBasicDto> createRoundRobin(Long id);

  PageDto<MatchBasicDto> listMatches(Pageable page, HttpServletRequest request, Long id);

  void delete(Long id);

  TournamentDto update(Long id, TournamentDto dto);

  List<TeamEntity> getTeams(Long id);


}
