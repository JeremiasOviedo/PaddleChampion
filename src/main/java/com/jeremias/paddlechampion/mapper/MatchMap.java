package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.dto.MatchBasicDto;
import com.jeremias.paddlechampion.dto.MatchDto;
import com.jeremias.paddlechampion.entity.MatchEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchMap {

  @Autowired
  TeamMap teamMap;

  public MatchDto matchEntity2Dto(MatchEntity entity) {

    MatchDto dto = new MatchDto();
    dto.setMatchId(entity.getMatchId());
    dto.setTeamA(entity.getTeamA());
    dto.setTeamB(entity.getTeamB());
    dto.setStatus(entity.getStatus());
    dto.setTeamsInfo(teamMap.teamEntityList2DtoList(entity.getMatchTeams()));

    if (entity.getWinner() != null) {
      dto.setWinner(entity.getWinner());
      dto.setTeamAScore(entity.getTeamAScore());
      dto.setTeamBScore(entity.getTeamBScore());
    } else {
      dto.setWinner("Undefined");
      dto.setTeamAScore(0);
      dto.setTeamBScore(0);
    }

    return dto;
  }

  public MatchBasicDto matchEntity2BasicDto(MatchEntity entity) {

    MatchBasicDto dto = new MatchBasicDto();
    dto.setId(entity.getMatchId());
    dto.setTeamA(entity.getTeamA());
    dto.setTeamB(entity.getTeamB());
    dto.setStatus(entity.getStatus());

    if (entity.getWinner() != null) {
      dto.setWinner(entity.getWinner());
    } else {
      dto.setWinner("Undefined");
    }

    return dto;
  }


  public List<MatchDto> matchEntityList2Dto(List<MatchEntity> entities) {

    List<MatchDto> dtos = new ArrayList<>();

    for (MatchEntity entity : entities) {

      dtos.add(matchEntity2Dto(entity));

    }

    return dtos;
  }

  public List<MatchBasicDto> matchEntityList2BasicDto(List<MatchEntity> entities) {

    List<MatchBasicDto> dtos = new ArrayList<>();

    for (MatchEntity entity : entities) {

      dtos.add(matchEntity2BasicDto(entity));
    }

    return dtos;
  }


}
