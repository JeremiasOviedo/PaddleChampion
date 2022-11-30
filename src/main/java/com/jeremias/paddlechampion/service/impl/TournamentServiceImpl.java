package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import com.jeremias.paddlechampion.mapper.TournamentMap;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
import com.jeremias.paddlechampion.model.Match;
import com.jeremias.paddlechampion.repository.TeamRepository;
import com.jeremias.paddlechampion.repository.TournamentRepository;
import com.jeremias.paddlechampion.service.ITournamentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements ITournamentService {

  @Autowired
  private TournamentRepository tournamentRepo;
  @Autowired
  private TeamRepository teamRepo;
  @Autowired
  TournamentMap tournamentMap;

  @Override
  public TournamentDto createTournament(TournamentDto dto) {
    TournamentEntity entity = tournamentMap.tournamentDto2Entity(dto);
    tournamentRepo.save(entity);

    return dto;
  }

  @Override
  public void addTeam(Long tournamentId, Long teamId) {
    TeamEntity team = teamRepo.findById(teamId).orElseThrow(
        () -> new ParamNotFound("Team ID is invalid"));
    TournamentEntity tournament = tournamentRepo.findById(tournamentId).orElseThrow(
        () -> new ParamNotFound("Tournament ID is invalid"));

    tournament.addTeam(team);
    tournamentRepo.save(tournament);

  }

  @Override
  public void deleteTeam(Long tournamentId, Long teamId) {
    TeamEntity team = teamRepo.findById(teamId).orElseThrow(
        () -> new ParamNotFound("Team ID is invalid"));
    TournamentEntity tournament = tournamentRepo.findById(tournamentId).orElseThrow(
        () -> new ParamNotFound("Tournament ID is invalid"));

    tournament.removeTeam(team);
    tournamentRepo.save(tournament);
  }

  @Override
  public List<List<Match>> createFixture(Long id) {

    TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
        () -> new ParamNotFound("Tournament ID is invalid"));

    List<TeamEntity> teams = tournament.getTeams();

    List<List<Match>> fixture = tournament.getFixture();

    if (teams.size() % 2 != 0) {
      TeamEntity free = new TeamEntity();
      free.setName("FREE");
      teams.add(free);
    }

    int numRounds = (teams.size() - 1);
    int halfSize = (teams.size() / 2);

    List<TeamEntity> teamEntities = new ArrayList<>();
    teamEntities.addAll(teams);
    teamEntities.remove(0);

    int teamsSize = teamEntities.size();

    for (int round = 0; round < numRounds; round++) {

      List<Match> matches = new ArrayList<>();

      int teamIdx = round % teamsSize;

      Match matchA = new Match(teamEntities.get(teamIdx), teams.get(0));

      matches.add(matchA);

      for (int idx = 1; idx < halfSize; idx++) {

        TeamEntity teamA = teamEntities.get((round + idx) % teamsSize);
        TeamEntity teamB = teamEntities.get((round + teamsSize - idx) % teamsSize);

        Match matchB = new Match(teamA, teamB);

        matches.add(matchB);

      }

      fixture.add(matches);

    }

    return fixture;
  }

  @Override
  public void delete(Long id) {
    TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
        () -> new ParamNotFound("Tournament ID is invalid")
    );
    tournamentRepo.delete(tournament);
  }

  @Override
  public TournamentDto update(Long id, TournamentDto dto) {

    TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
        () -> new ParamNotFound("Tournament ID is invalid")
    );

    tournament.setName(dto.getName());
    tournament.setMaxTeams(dto.getMaxTeams());
    tournament.setMaxCategory(dto.getMaxCategory());
    tournament.setInscriptionStatus(dto.getInscriptionStatus());

    return dto;
  }
}
