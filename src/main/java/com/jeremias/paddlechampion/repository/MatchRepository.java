package com.jeremias.paddlechampion.repository;

import com.jeremias.paddlechampion.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

}
