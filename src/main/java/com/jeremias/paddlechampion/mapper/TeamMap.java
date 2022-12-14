package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.dto.TeamBasicDto;
import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.entity.TeamEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamMap {

    @Autowired
    UserMap userMap;

    public TeamDto teamEntity2Dto(TeamEntity entity) {
        TeamDto dto = new TeamDto();

        dto.setId(entity.getTeamId());
        dto.setName(entity.getName());
        dto.setCategory(entity.getCategory());
        dto.setPlayers(userMap.userEntityList2DtoList(entity.getPlayers().stream().toList()));

        return dto;
    }

    public TeamEntity teamDto2Entity(TeamDto dto) {
        TeamEntity entity = new TeamEntity();

        entity.setTeamId(dto.getId());
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());

        return entity;

    }

    public TeamBasicDto teamEntity2BasicDto(TeamEntity teamEntity) {

        TeamBasicDto dto = new TeamBasicDto();

        dto.setId(teamEntity.getTeamId());
        dto.setName(teamEntity.getName());
        dto.setCategory(teamEntity.getCategory());

        return dto;
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

    public List<TeamBasicDto> teamEntityList2BasicDtoList(List<TeamEntity> entities){

        List<TeamBasicDto> dtos = new ArrayList<>();

        for(TeamEntity entity : entities){

            dtos.add(teamEntity2BasicDto(entity));
        }

        return dtos;
    }
}
