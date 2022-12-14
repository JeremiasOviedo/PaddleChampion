package com.jeremias.paddlechampion.controller;

import com.jeremias.paddlechampion.dto.*;
import com.jeremias.paddlechampion.service.impl.TournamentServiceImpl;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    TournamentServiceImpl tournamentService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a tournaments info",
            notes = "This will retrieve all the information of a tournament")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Tournament ID is invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "Tournament not found")})
    public ResponseEntity<TournamentDto> getTournament(@PathVariable(name = "id") Long idTournament) {

        TournamentDto dto = tournamentService.getTournament(idTournament);

        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/create")
    @ApiOperation(value = "Create a tournament",
            notes = "Create the tournament filling the form")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Tournament name is already taken)"),
            @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<TournamentDto> createTournament(@RequestBody TournamentDto tournament) {

        TournamentDto dto = tournamentService.createTournament(tournament);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{tournamentId}/addTeam/{teamId}")
    @ApiOperation(value = "Add a team to the tournament",
            notes = "Add a team to a tournament using their IDs. For the moment, everyone can add any team to any tournament")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Team or Tournament IDs are invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "Team or Tournament not found")})
    public ResponseEntity<String> addTeam(
            @PathVariable("tournamentId") Long tournamentId, @PathVariable("teamId") Long teamId) {

        tournamentService.addTeam(tournamentId, teamId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Team added successfully");


    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}/start")
    @ApiOperation(value = "Start the tournament",
            notes = "This will start the tournament, closing the inscription and creating the round robin phase")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Tournament ID is invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "Tournament not found")})
    public ResponseEntity<TournamentDto> startTournament(@PathVariable(name = "id") Long idTournament) {

        tournamentService.startTournament(idTournament);
        TournamentDto dto = tournamentService.getTournament(idTournament);

        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{tournamentId}/matches")
    @ApiOperation(value = "List all the tournament matches",
            notes = "This will be retrieve all the tournament matches in pages")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Tournament ID is invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "Tournament not found")})
    public ResponseEntity<PageDto<MatchBasicDto>> listMatches(@PageableDefault(size = 10) Pageable page, HttpServletRequest request,
                                                              @PathVariable Long tournamentId) {

        PageDto<MatchBasicDto> matches = tournamentService.listMatches(page, request, tournamentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(matches);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{tournamentId}/teams")
    @ApiOperation(value = "List the teams",
            notes = "this endpoint lists all the teams in the tournament")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Tournament ID is invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "Tournament not found")})
    public ResponseEntity<List<TeamDto>> listTeams(@PathVariable Long tournamentId) {

        List<TeamDto> teams = tournamentService.getTeamsDto(tournamentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(teams);

    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{tournamentId}/positions")
    @ApiOperation(value = "Get the positions table",
            notes = "This will execute an algorithm that gets all the matches that are finished" +
                    " and update the positions table based on the results")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Tournament ID is invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "Tournament not found")})
    public ResponseEntity<List<TeamTournamentDto>> positions(@PathVariable Long tournamentId) {

        List<TeamTournamentDto> positions = tournamentService.getPositionsTable(tournamentId);

        return ResponseEntity.status(HttpStatus.OK).body(positions);
    }

}
