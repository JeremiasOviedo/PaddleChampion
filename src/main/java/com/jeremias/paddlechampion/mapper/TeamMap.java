package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TeamMap {

  public TeamDto teamEntity2Dto(TeamEntity entity) {
    TeamDto dto = new TeamDto();

    dto.setId(entity.getTeamId());
    dto.setName(entity.getName());
    dto.setCategory(entity.getCategory());

    return dto;
  }

  public TeamEntity teamDto2Entity(TeamDto dto) {
    TeamEntity entity = new TeamEntity();

    entity.setTeamId(dto.getId());
    entity.setName(dto.getName());
    entity.setCategory(dto.getCategory());

    return entity;

  }

  public List<TeamDto> teamEntityList2DtoList(List<TeamEntity> entities) {

    List<TeamDto> dtos = new ArrayList<>();

    for (TeamEntity entity : entities) {
      dtos.add(teamEntity2Dto(entity));
    }

    return dtos;
  }

  public List<TeamEntity> teamDto2EntityList(List<TeamDto> dtos) {
    List<TeamEntity> entities = new ArrayList<>();

    for (TeamDto dto : dtos) {
      entities.add(teamDto2Entity(dto));
    }

    return entities;
  }
}
