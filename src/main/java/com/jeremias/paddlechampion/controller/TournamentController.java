package com.jeremias.paddlechampion.controller;

import com.jeremias.paddlechampion.dto.MatchBasicDto;
import com.jeremias.paddlechampion.dto.MatchDto;
import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.dto.TeamTournamentDto;
import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.entity.TeamTournament;
import com.jeremias.paddlechampion.service.impl.TournamentServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

  @Autowired
  TournamentServiceImpl tournamentService;

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<TournamentDto> getTournament(@PathVariable(name = "id") Long idTournament) {

    TournamentDto dto = tournamentService.getTournament(idTournament);

    return ResponseEntity.status(HttpStatus.OK).body(dto);

  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @PostMapping("/create")
  public ResponseEntity<TournamentDto> createTournament(@RequestBody TournamentDto tournament) {

    TournamentDto dto = tournamentService.createTournament(tournament);

    return ResponseEntity.status(HttpStatus.CREATED).body(dto);

  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @PostMapping("/{tournamentId}/addTeam/{teamId}")
  public ResponseEntity<String> addTeam(
      @PathVariable("tournamentId") Long tournamentId, @PathVariable("teamId") Long teamId) {

    tournamentService.addTeam(tournamentId, teamId);

    return ResponseEntity.status(HttpStatus.CREATED).body("Team added successfully");


  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("/{tournamentId}/matches")
  public ResponseEntity<List<MatchBasicDto>> listMatches(@PathVariable Long tournamentId) {

    List<MatchBasicDto> fixture = tournamentService.listMatches(tournamentId);

    return ResponseEntity.status(HttpStatus.CREATED).body(fixture);
  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("/{tournamentId}/teams")
  public ResponseEntity<List<TeamDto>> listTeams(@PathVariable Long tournamentId) {

    List<TeamDto> teams = tournamentService.getTeamsDto(tournamentId);

    return ResponseEntity.status(HttpStatus.CREATED).body(teams);

  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("/{tournamentId}/positions")
  public ResponseEntity<List<TeamTournamentDto>> positions(@PathVariable Long tournamentId){

    List <TeamTournamentDto> positions = tournamentService.teamTournamentList(tournamentId);

    return ResponseEntity.status(HttpStatus.OK).body(positions);
  }

}
