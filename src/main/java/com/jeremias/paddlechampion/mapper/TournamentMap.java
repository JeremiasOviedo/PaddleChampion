package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TournamentMap {

  TeamMap teamMap;

  public TournamentDto tournamentEntity2Dto(TournamentEntity entity) {
    TournamentDto dto = new TournamentDto();

    dto.setId(entity.getTournamentId());
    dto.setName(entity.getName());
    dto.setMaxCategory(entity.getMaxCategory());
    dto.setMaxTeams(entity.getMaxTeams());
    dto.setInscriptionStatus(entity.getInscriptionStatus());
    dto.setTeams(teamMap.teamEntityList2DtoList(entity.getTeams()));
    dto.setMatches(matchMap.matchEntityList2DtoList(entity.getMatches()));

    return dto;

  }

  public TournamentEntity tournamentDto2Entity(TournamentDto dto) {
    TournamentEntity entity = new TournamentEntity();

    entity.setTournamentId(dto.getId());
    entity.setName(dto.getName());
    entity.setMaxCategory(dto.getMaxCategory());
    entity.setMaxTeams(dto.getMaxTeams());
    entity.setInscriptionStatus(dto.getInscriptionStatus());
    entity.setTeams(teamMap.teamDto2EntityList(dto.getTeams()));
    entity.setMatches(matchMap.matchDtoList2EntityList(dto.getMatches()));

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
