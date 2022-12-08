package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.AddPlayer2TeamDto;
import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.entity.UserEntity;
import com.jeremias.paddlechampion.mapper.TeamMap;
import com.jeremias.paddlechampion.mapper.UserMap;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
import com.jeremias.paddlechampion.mapper.exception.RepeatedUsername;
import com.jeremias.paddlechampion.repository.TeamRepository;
import com.jeremias.paddlechampion.repository.UserRepository;
import com.jeremias.paddlechampion.service.ITeamService;
import java.awt.print.Pageable;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements ITeamService {

  @Autowired
  private TeamRepository teamRepo;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  TeamMap teamMap;

  @Autowired
  UserMap userMap;

  @Override
  public TeamDto getTeam(Long id) {
    TeamEntity team = teamRepo.findById(id).orElseThrow(
        () -> new ParamNotFound("Team doesnt exist")
    );

    TeamDto dto = teamMap.teamEntity2Dto(team);

    return dto;

  }

  @Override
  public TeamDto createTeam(TeamDto dto) {

    if (teamRepo.findByName(dto.getName()) != null){
      throw new RepeatedUsername("Team name is already taken");

    }

    String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    UserEntity userEntity = userRepo.findByEmail(userEmail);

    TeamEntity entity = teamMap.teamDto2Entity(dto);
    entity.setMaxPlayers(2);
    entity.addUserToTeam(userEntity);
    
    TeamEntity entitySaved = teamRepo.save(entity);

    return teamMap.teamEntity2Dto(entitySaved);
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

  @Override
  public UserDto addPlayer(AddPlayer2TeamDto dto) {
    TeamEntity team = teamRepo.findByTeamId(dto.getTeamId());
    UserEntity player = userRepo.findByUserId(dto.getPlayerId());

    team.addUserToTeam(player);

    userRepo.save(player);
    teamRepo.save(team);

    return userMap.userEntity2Dto(player);
  }


}
