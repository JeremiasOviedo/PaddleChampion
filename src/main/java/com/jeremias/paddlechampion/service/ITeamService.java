package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.dto.AddPlayer2TeamDto;
import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import java.awt.print.Pageable;
import javax.servlet.http.HttpServletRequest;

public interface ITeamService {

  TeamDto getTeam(Long id);
  TeamDto createTeam(TeamDto dto);

  void delete(Long id);

  TeamDto update(Long id, TeamDto dto);

  PageDto<TeamDto> findAll(Pageable pageable, HttpServletRequest request);

 TeamDto addPlayer(AddPlayer2TeamDto dto);

}
