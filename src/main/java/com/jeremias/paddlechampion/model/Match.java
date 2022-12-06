package com.jeremias.paddlechampion.model;

import com.jeremias.paddlechampion.entity.TeamEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import java.io.Serializable;

import org.springframework.context.annotation.Bean;


public class Match implements Serializable {

  private static final Long serialVersionUID = 1L;



  private TeamEntity teamA;
  private TeamEntity teamB;
  private Integer teamAScore;
  private Integer teamBScore;
  private TeamEntity winner;

  public Match(TeamEntity teamA, TeamEntity teamB){

    this.teamA = teamA;
    this.teamB = teamB;
  }

}
