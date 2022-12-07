package com.jeremias.paddlechampion.mapper;

import com.jeremias.paddlechampion.dto.RoleDto;
import com.jeremias.paddlechampion.entity.RoleEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoleMap {

  public RoleDto roleEntity2Dto(RoleEntity entity) {

    RoleDto dto = new RoleDto();
    dto.setRoleId(entity.getRoleId());
    dto.setRoleName(entity.getName());
    dto.setDescription(entity.getDescription());

    return dto;
  }

  public RoleEntity roleDto2Entity(RoleDto dto) {

    RoleEntity entity = new RoleEntity();

    entity.setName(dto.getRoleName());
    entity.setDescription(dto.getDescription());

    return entity;
  }

  public List<RoleDto> roleEntityList2Dto(List<RoleEntity> entities) {

    List<RoleDto> dtos = new ArrayList<>();

    for (RoleEntity entity : entities) {
      dtos.add(roleEntity2Dto(entity));
    }

    return dtos;
  }

}
