package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.dto.RoleDto;
import com.jeremias.paddlechampion.entity.RoleEntity;
import com.jeremias.paddlechampion.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface IRoleService {

  void save (RoleDto role);

}
