package com.jeremias.paddlechampion.repository;

import com.jeremias.paddlechampion.entity.TournamentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {

  TournamentEntity findByName(String name);

  TournamentEntity findByTournamentId(Long id);

  Page<TournamentEntity> findAll(Pageable pageable);

}
