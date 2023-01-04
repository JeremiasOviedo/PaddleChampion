package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.auth.dto.ResponseUserDto;
import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMap {

  @Autowired
  TeamMap teamMap;

  @Autowired
  TournamentMap tournamentMap;

  public UserDto userEntity2Dto(UserEntity entity) {

    UserDto dto = new UserDto();

    dto.setId(entity.getUserId());
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.getLastName());
    dto.setEmail(entity.getEmail());
    dto.setCategory(entity.getCategory());
    // dto.setTeams(teamMap.teamEntityList2DtoList(entity.getTeams()));

    return dto;
  }

  public UserEntity userDto2Entity(UserDto dto) {

    UserEntity entity = new UserEntity();

    entity.setUserId(dto.getId());
    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setEmail(dto.getEmail());
    entity.setPassword(dto.getPassword());
    entity.setCategory(dto.getCategory());

    return entity;
  }

  public List<UserDto> userEntityList2DtoList(List<UserEntity> entities) {

    List<UserDto> dtos = new ArrayList<>();

    for (UserEntity entity : entities) {
      dtos.add(userEntity2Dto(entity));
    }

    return dtos;

  }

  public List<UserEntity> userDtoList2EntityList(List<UserDto> dtos) {

    List<UserEntity> entities = new ArrayList<>();

    for (UserDto dto : dtos) {
      entities.add(userDto2Entity(dto));
    }

    return entities;
  }

  public UserEntity userAuthDto2Entity(ResponseUserDto userDto) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(userDto.getFirstName());
    userEntity.setLastName(userDto.getLastName());
    userEntity.setEmail(userDto.getEmail());
    userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    userEntity.setUpdateDate(userDto.getUpdateDate());
    userEntity.setCreationDate(userDto.getCreationDate());
    userEntity.setCategory(userDto.getCategory());

    return userEntity;
  }

  public ResponseUserDto userAuthEntity2Dto(UserEntity entitySaved) {
    ResponseUserDto dto = new ResponseUserDto();
    dto.setId(entitySaved.getUserId());
    dto.setFirstName(entitySaved.getFirstName());
    dto.setLastName(entitySaved.getLastName());
    dto.setEmail(entitySaved.getEmail());
    dto.setRole(entitySaved.getRole().getName());
    dto.setUpdateDate(entitySaved.getUpdateDate());
    dto.setCreationDate(entitySaved.getCreationDate());
    dto.setCategory(entitySaved.getCategory());
    dto.setPassword("[PROTECTED]");

    if (entitySaved.getTeams() != null){
      dto.setTeams(teamMap.teamEntityList2BasicDtoList(entitySaved.getTeams()));
    }

    if (entitySaved.getTournaments() != null){
      dto.setTournaments(tournamentMap.tournamentEntitySet2BasicDtoList(entitySaved.getTournaments()));
    }

    return dto;
  }

}
