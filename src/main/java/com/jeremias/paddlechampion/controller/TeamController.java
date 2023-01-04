package com.jeremias.paddlechampion.controller;

import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.dto.AddPlayer2TeamDto;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.service.impl.TeamServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
  @ApiOperation(value = "Create a Team",
          notes = "This endpoint will create a team, adding the authorization token user as a team member, later you can add a second one")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Team name is already Taken"),
          @ApiResponse(code = 404, message = "Not Found")})
  public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto team) {
    TeamDto dto = teamService.createTeam(team);

    return ResponseEntity.status(HttpStatus.CREATED).body(dto);

  }
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @PostMapping("/addPlayer")
  @ApiOperation(value = "Add a team member",
          notes = "Add a member to a team using their IDs")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Team or User IDs are invalid (must use numbers value only)"),
          @ApiResponse(code = 404, message = "Team or user not found")})
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
