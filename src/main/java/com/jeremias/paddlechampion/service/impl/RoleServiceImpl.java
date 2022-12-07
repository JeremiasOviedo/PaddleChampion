package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.RoleDto;
import com.jeremias.paddlechampion.entity.RoleEntity;
import com.jeremias.paddlechampion.mapper.RoleMap;
import com.jeremias.paddlechampion.repository.RoleRepository;
import com.jeremias.paddlechampion.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements IRoleService {

  @Autowired
  RoleMap roleMap;

  @Autowired
  RoleRepository roleRepo;

  @Override
  public void save(RoleDto role) {

    RoleEntity entity = roleMap.roleDto2Entity(role);

    roleRepo.save(entity);

  }
}
