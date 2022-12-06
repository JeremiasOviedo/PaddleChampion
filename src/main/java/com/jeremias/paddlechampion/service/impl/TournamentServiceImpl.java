package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.MatchDto;
import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import com.jeremias.paddlechampion.mapper.MatchMap;
import com.jeremias.paddlechampion.mapper.TournamentMap;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
import com.jeremias.paddlechampion.entity.MatchEntity;
import com.jeremias.paddlechampion.repository.MatchRepository;
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
  private MatchRepository matchRepo;
  @Autowired
  private TournamentRepository tournamentRepo;
  @Autowired
  private TeamRepository teamRepo;
  @Autowired
  TournamentMap tournamentMap;
  @Autowired
  MatchMap matchMap;

  @Override
  public TournamentDto createTournament(TournamentDto dto) {
    TournamentEntity entity = tournamentMap.tournamentDto2Entity(dto);
    tournamentRepo.save(entity);

    return dto;
  }

  @Override
  public TournamentDto getTournament(Long tournamentId) {

    TournamentEntity tournament = tournamentRepo.findById(tournamentId).orElseThrow(
        () -> new ParamNotFound("Team doesn't exist"));

    TournamentDto dto = tournamentMap.tournamentEntity2Dto(tournament);

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
  public List<MatchDto> createFixture(Long id) {

    TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
        () -> new ParamNotFound("Tournament ID is invalid"));

    List<TeamEntity> teams = tournament.getTeams();

    if (teams.size() % 2 != 0) {
      TeamEntity free = teamRepo.findByName("FREE");
      teams.add(free);
    }

    int numRounds = (teams.size() - 1);
    int halfSize = (teams.size() / 2);

    List<TeamEntity> teamEntities = new ArrayList<>();
    teamEntities.addAll(teams);
    teamEntities.remove(0);

    List<MatchEntity> matchEntities = new ArrayList<>();

    int teamsSize = teamEntities.size();

    for (int round = 0; round < numRounds; round++) {

      int teamIdx = round % teamsSize;

      MatchEntity matchEntityA = new MatchEntity(teamEntities.get(teamIdx), teams.get(0),
          tournament);

      matchRepo.save(matchEntityA);
      matchEntities.add(matchEntityA);

      for (int idx = 1; idx < halfSize; idx++) {

        TeamEntity teamA = teamEntities.get((round + idx) % teamsSize);
        TeamEntity teamB = teamEntities.get((round + teamsSize - idx) % teamsSize);

        MatchEntity matchEntityB = new MatchEntity(teamA, teamB, tournament);

        matchRepo.save(matchEntityB);
        matchEntities.add(matchEntityB);

      }

    }

    List<MatchDto> matchesDto = matchMap.matchEntityList2Dto(matchEntities);
    return matchesDto;
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
