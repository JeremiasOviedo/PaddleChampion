package com.jeremias.paddlechampion.service;

public interface ITeamTournamentService {

  void save(Long tournamentId, Long teamId);

  void delete(Long tournamentId, Long teamId);

}
