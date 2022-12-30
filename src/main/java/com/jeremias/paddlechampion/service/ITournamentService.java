package com.jeremias.paddlechampion.service;


import com.jeremias.paddlechampion.dto.MatchBasicDto;
import com.jeremias.paddlechampion.dto.MatchDto;
import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import java.util.List;

public interface ITournamentService {

  TournamentDto createTournament(TournamentDto dto);

  TournamentDto getTournament(Long tournamentId);

  void addTeam(Long tournamentId, Long teamId);

  void deleteTeam(Long tournamentId, Long teamId);

  List<MatchBasicDto> listMatches(Long id);

  void delete(Long id);

  TournamentDto update(Long id, TournamentDto dto);

  List<TeamEntity> getTeams(Long id);


}
