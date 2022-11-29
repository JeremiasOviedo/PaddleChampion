package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.model.Match;
import com.jeremias.paddlechampion.repository.TournamentRepository;
import com.jeremias.paddlechampion.service.ITournamentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements ITournamentService {

  @Autowired
  private TournamentRepository tournamentRepo;

  @Override
  public TournamentDto createTournament(TournamentDto dto) {
    return null;
  }

  @Override
  public void addTeam(Long id) {

  }

  @Override
  public List<Match> createFixture(List<TeamEntity> teams) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public TournamentDto update(Long id, TournamentDto dto) {
    return null;
  }
}
