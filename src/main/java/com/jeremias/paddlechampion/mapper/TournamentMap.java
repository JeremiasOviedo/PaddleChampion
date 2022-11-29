package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TournamentMap {

  TeamMap teamMap;

  UserMap userMap;

  public TournamentDto tournamentEntity2Dto(TournamentEntity entity) {
    TournamentDto dto = new TournamentDto();

    dto.setCreator(userMap.userEntity2Dto(entity.getUser()));
    dto.setId(entity.getTournamentId());
    dto.setName(entity.getName());
    dto.setMaxCategory(entity.getMaxCategory());
    dto.setMaxTeams(entity.getMaxTeams());
    dto.setInscriptionStatus(entity.getInscriptionStatus());
    dto.setTeams(teamMap.teamEntityList2DtoList(entity.getTeams()));
    dto.setMatches(entity.getMatches());

    return dto;

  }

  public TournamentEntity tournamentDto2Entity(TournamentDto dto) {
    TournamentEntity entity = new TournamentEntity();

    entity.setUser(userMap.userDto2Entity(dto.getCreator()));
    entity.setTournamentId(dto.getId());
    entity.setName(dto.getName());
    entity.setMaxCategory(dto.getMaxCategory());
    entity.setMaxTeams(dto.getMaxTeams());
    entity.setInscriptionStatus(dto.getInscriptionStatus());
    entity.setTeams(teamMap.teamDto2EntityList(dto.getTeams()));
    entity.setMatches(dto.getMatches());

    return entity;
  }

  public List<TournamentDto> tournamentEntityList2DtoList(List<TournamentEntity> entities) {
    List<TournamentDto> dtos = new ArrayList<>();

    for (TournamentEntity entity : entities) {
      dtos.add(tournamentEntity2Dto(entity));
    }

    return dtos;
  }

  public List<TournamentEntity> tournamentDtoList2EntityList(List<TournamentDto> dtos) {

    List<TournamentEntity> entities = new ArrayList<>();

    for (TournamentDto dto : dtos) {
      entities.add(tournamentDto2Entity(dto));
    }

    return entities;
  }

}
