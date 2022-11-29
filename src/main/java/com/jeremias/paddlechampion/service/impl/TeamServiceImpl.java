package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.repository.TeamRepository;
import com.jeremias.paddlechampion.service.ITeamService;
import jakarta.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements ITeamService {

  @Autowired
  private TeamRepository teamRepo;

  @Override
  public TeamEntity createTeam(TeamDto dto) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public TeamDto update(Long id, TeamDto dto) {
    return null;
  }

  @Override
  public PageDto<TeamDto> findAll(Pageable pageable, HttpServletRequest request) {
    return null;
  }
}
