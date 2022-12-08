package com.jeremias.paddlechampion.mapper;

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

  private MatchDto matchEntity2Dto(MatchEntity entity) {

    MatchDto dto = new MatchDto();
    dto.setMatchId(entity.getMatchId());
    dto.setTeamA(entity.getTeamA());
    dto.setTeamB(entity.getTeamB());
    dto.setTeams(teamMap.teamEntityList2DtoList(entity.getMatchTeams()));

    return dto;
  }


  public List<MatchDto> matchEntityList2Dto(List<MatchEntity> entities) {

    List<MatchDto> dtos = new ArrayList<>();

    for (MatchEntity entity : entities) {

      dtos.add(matchEntity2Dto(entity));

    }

    return dtos;
  }
}
