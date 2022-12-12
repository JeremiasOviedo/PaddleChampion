package com.jeremias.paddlechampion.repository;

import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.entity.TeamTournament;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamTournamentRepository extends JpaRepository<TeamTournament, Long> {

  TeamTournament findByTournamentAndTeam(TournamentEntity tournament, TeamEntity team);

}
