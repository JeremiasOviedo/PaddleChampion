package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.entity.MatchEntity;

public interface ITeamTournamentService {

  void save(Long tournamentId, Long teamId);

  void delete(Long tournamentId, Long teamId);

  void updateFromMatch(MatchEntity match);

}
