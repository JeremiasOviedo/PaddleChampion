package com.jeremias.paddlechampion.repository;

import com.jeremias.paddlechampion.entity.RoleEntity;
import com.jeremias.paddlechampion.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  RoleEntity findByName(RoleName name);
}
