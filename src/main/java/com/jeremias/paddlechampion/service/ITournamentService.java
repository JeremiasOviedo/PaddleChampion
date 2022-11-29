package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import com.jeremias.paddlechampion.model.Match;
import java.util.List;
import java.util.Set;

public interface ITournamentService {

TournamentDto createTournament(TournamentDto dto);

void addTeam(Long id);

List<Match> createFixture(Set<TeamDto> teams);



}
