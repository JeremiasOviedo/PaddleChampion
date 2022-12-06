package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserMap {

  TeamMap teamMap;

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

}
