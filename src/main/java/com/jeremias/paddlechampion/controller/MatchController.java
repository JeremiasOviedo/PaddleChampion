package com.jeremias.paddlechampion.controller;

import com.jeremias.paddlechampion.dto.MatchDto;
import com.jeremias.paddlechampion.service.IMatchService;
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
  public ResponseEntity<MatchDto> getMatch(@PathVariable(name = "id") Long idMatch) {

    MatchDto dto = matchService.getMatch(idMatch);

    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }

  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<MatchDto> updateMatch(@PathVariable(name = "id") Long idMatch,
      @RequestBody MatchDto dto) {

    MatchDto responseDto = matchService.update(idMatch, dto);

    return ResponseEntity.status(HttpStatus.OK).body(responseDto);

  }

}
