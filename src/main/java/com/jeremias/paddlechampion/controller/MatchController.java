package com.jeremias.paddlechampion.controller;

import com.jeremias.paddlechampion.dto.MatchDto;
import com.jeremias.paddlechampion.service.IMatchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {

  @Autowired
  IMatchService matchService;

  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @GetMapping("/{id}")
  @ApiOperation(value = "Get a match information",
          notes = "This will retrieve all the information of the match, scores, teams , status (not played or finished)" +
                  " and winner (if the match was played)")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Match ID is invalid (must use numbers value only)"),
          @ApiResponse(code = 404, message = "Match not found")})
  public ResponseEntity<MatchDto> getMatch(@PathVariable(name = "id") Long idMatch) {

    MatchDto dto = matchService.getMatch(idMatch);

    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }

  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @PutMapping("/{id}")
  @ApiOperation(value = "Update a match",
          notes = "Update the match score, this will automatically select the winner and updates the TeamTournament entity")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Match ID or scores are invalid (must use numbers value only)"),
          @ApiResponse(code = 404, message = "Match not found")})
  public ResponseEntity<MatchDto> updateMatch(@PathVariable(name = "id") Long idMatch,
      @RequestBody MatchDto dto) {

    MatchDto responseDto = matchService.update(idMatch, dto);

    return ResponseEntity.status(HttpStatus.OK).body(responseDto);

  }

}
