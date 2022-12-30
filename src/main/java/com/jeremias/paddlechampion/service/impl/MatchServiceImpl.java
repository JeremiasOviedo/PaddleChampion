package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.MatchDto;
import com.jeremias.paddlechampion.entity.MatchEntity;
import com.jeremias.paddlechampion.enumeration.Status;
import com.jeremias.paddlechampion.mapper.MatchMap;
import com.jeremias.paddlechampion.mapper.exception.MatchesException;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
import com.jeremias.paddlechampion.repository.MatchRepository;
import com.jeremias.paddlechampion.service.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements IMatchService {

  @Autowired
  MatchRepository matchRepo;

  @Autowired
  MatchMap matchMap;

  @Override
  public MatchDto update(Long id, MatchDto dto) {
    MatchEntity match = matchRepo.findById(id).orElseThrow(
        ()   -> new ParamNotFound("Match ID is invalid."));

    if (dto.getTeamAScore().equals(dto.getTeamBScore())){
      throw new MatchesException("Scores cannot be equal");

    }
    match.setTeamAScore(dto.getTeamAScore());
    match.setTeamBScore(dto.getTeamBScore());

    if (dto.getTeamAScore() > dto.getTeamBScore()){

      match.setWinner(match.getTeamA());

    } else {

      match.setWinner(match.getTeamB());
    }

    match.setStatus(Status.FINISHED);

    return matchMap.matchEntity2Dto(matchRepo.save(match));
  }

  @Override
  public void delete(Long id) {
    MatchEntity match = matchRepo.findById(id).orElseThrow(
        ()-> new ParamNotFound("Match ID is invalid."));

    matchRepo.delete(match);

  }

  @Override
  public MatchDto getMatch(Long id) {
    MatchEntity entity = matchRepo.findById(id).orElseThrow(
        ()-> new ParamNotFound("the match ID is invalid"));


    return matchMap.matchEntity2Dto(entity);
  }
}
