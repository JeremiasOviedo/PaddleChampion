package com.jeremias.paddlechampion.repository;

import com.jeremias.paddlechampion.entity.MatchEntity;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    Page<MatchEntity> findAllByTournament(TournamentEntity tournament, Pageable page);

}
