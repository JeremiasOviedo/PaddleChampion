package com.jeremias.paddlechampion.controller;

import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.dto.AddPlayer2TeamDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.service.impl.TeamServiceImpl;
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
@RequestMapping("/team")
public class TeamController {

  @Autowired
  TeamServiceImpl teamService;
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @PostMapping("/register")
  public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto team) {
    TeamDto dto = teamService.createTeam(team);

    return ResponseEntity.status(HttpStatus.CREATED).body(dto);

  }
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @PostMapping("/addPlayer")
  public ResponseEntity<TeamDto> addPlayer(AddPlayer2TeamDto dto) {


    return ResponseEntity.status(HttpStatus.CREATED).body(teamService.addPlayer(dto));
  }
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("/{teamId}")
  public ResponseEntity<TeamDto> getTeam(@PathVariable(name = "teamId") Long teamId) {

    TeamDto dto = teamService.getTeam(teamId);

    return ResponseEntity.status(HttpStatus.OK).body(dto);

  }

}
