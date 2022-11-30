package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.mapper.TeamMap;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
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
  @Autowired
  TeamMap teamMap;

  @Override
  public TeamEntity createTeam(TeamDto dto) {

    TeamEntity entity = teamMap.teamDto2Entity(dto);
    teamRepo.save(entity);

    return entity;
  }

  @Override
  public void delete(Long id) {
    TeamEntity team = teamRepo.findById(id).orElseThrow(
        () -> new ParamNotFound("Team ID is invalid"));

    teamRepo.delete(team);

  }

  @Override
  public TeamDto update(Long id, TeamDto dto) {
    TeamEntity entity = teamRepo.findById(id).orElseThrow(
        () -> new ParamNotFound("Team ID is invalid"));

    entity.setName(dto.getName());
    entity.setCategory(dto.getCategory());

    teamRepo.save(entity);

    return dto;
  }

  @Override
  public PageDto<TeamDto> findAll(Pageable pageable, HttpServletRequest request) {
    return null;
  }
}
