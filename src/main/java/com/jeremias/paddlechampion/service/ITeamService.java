package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.entity.TeamEntity;

public interface ITeamService {

  TeamEntity createTeam(Long id, Long id2);

  void delete (Long id);

  TeamDto update (Long id, TeamDto dto);
}
