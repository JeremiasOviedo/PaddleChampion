package com.jeremias.paddlechampion.repository;

import com.jeremias.paddlechampion.entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

  TeamEntity findByName(String name);

  TeamEntity findByTeamId(Long id);

  Page<TeamEntity> findAll(Pageable pageable);
}
