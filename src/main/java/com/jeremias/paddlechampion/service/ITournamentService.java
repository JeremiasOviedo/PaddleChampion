package com.jeremias.paddlechampion.service;


import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import com.jeremias.paddlechampion.model.Match;
import java.util.List;
import java.util.Set;

public interface ITournamentService {

  TournamentDto createTournament(TournamentDto dto);

  void addTeam(Long tournamentId, Long teamId);

  void deleteTeam(Long tournamentId, Long teamId);

  List<List<Match>> createFixture(List<TeamEntity> teams);

  void delete(Long id);

  TournamentDto update(Long id, TournamentDto dto);



}
