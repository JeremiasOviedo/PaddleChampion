package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.dto.MatchDto;

public interface IMatchService {

  MatchDto update(Long id, MatchDto dto);

  void delete(Long id);

  MatchDto getMatch(Long id);


}
