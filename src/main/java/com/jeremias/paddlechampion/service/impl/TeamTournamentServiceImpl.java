package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.entity.MatchEntity;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.entity.TeamTournament;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import com.jeremias.paddlechampion.mapper.exception.MaxTeamsException;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
import com.jeremias.paddlechampion.repository.TeamRepository;
import com.jeremias.paddlechampion.repository.TeamTournamentRepository;
import com.jeremias.paddlechampion.repository.TournamentRepository;
import com.jeremias.paddlechampion.service.ITeamTournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamTournamentServiceImpl implements ITeamTournamentService {

  @Autowired
  TeamTournamentRepository teamTournamentRepo;
  @Autowired
  TournamentRepository tournamentRepo;
  @Autowired
  TeamRepository teamRepo;

  @Override
  public void save(Long tournamentId, Long teamId) {
    TeamEntity team = teamRepo.findById(teamId).orElseThrow(
        () -> new ParamNotFound("Team ID is invalid"));
    TournamentEntity tournament = tournamentRepo.findById(tournamentId).orElseThrow(
        () -> new ParamNotFound("Tournament ID is invalid"));

    if (tournament
        .getTeamTournaments()
        .size() >= tournament.getMaxTeams()) {

      throw new MaxTeamsException("The tournament is full, cant add more teams");

    } else {

      TeamTournament teamTournament = new TeamTournament();

      teamTournament.setTournament(tournament);
      teamTournament.setTeam(team);
      tournament.getTeamTournaments().add(teamTournament);
      team.getTeamTournament().add(teamTournament);

      teamTournamentRepo.save(teamTournament);
      tournamentRepo.save(tournament);
      teamRepo.save(team);


    }

  }

  @Override
  public void delete(Long tournamentId, Long teamId) {
    TeamEntity team = teamRepo.findById(teamId).orElseThrow(
        () -> new ParamNotFound("Team ID is invalid"));
    TournamentEntity tournament = tournamentRepo.findById(tournamentId).orElseThrow(
        () -> new ParamNotFound("Tournament ID is invalid"));

    TeamTournament teamTournament = teamTournamentRepo.findByTournamentAndTeam(tournament, team);

    teamTournamentRepo.delete(teamTournament);

  }

  @Override
  public void updateFromMatch(MatchEntity match) {

  }
}
