package com.jeremias.paddlechampion.controller;

import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.model.Match;
import com.jeremias.paddlechampion.service.impl.TournamentServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @PostMapping("/create")
  public ResponseEntity<TournamentDto> createTournament(@RequestBody TournamentDto tournament) {

    TournamentDto dto = tournamentService.createTournament(tournament);

    return ResponseEntity.status(HttpStatus.CREATED).body(dto);

  }

  @PostMapping("/addTeam/{tournamentId}/{teamId}")
  public ResponseEntity<String> addTeam(
      @PathVariable("tournamentId") Long tournamentId, @PathVariable("teamId") Long teamId) {

    tournamentService.addTeam(tournamentId, teamId);

    return ResponseEntity.status(HttpStatus.CREATED).body("Team added successfully");


  }

  @GetMapping("/createFixture/{tournamentId}")
  public ResponseEntity<List<List<Match>>> createFixture(@PathVariable Long tournamentId) {

    List<List<Match>> fixture = tournamentService.createFixture(tournamentId);

    return ResponseEntity.status(HttpStatus.CREATED).body(fixture);
  }

}
