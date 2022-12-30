package com.jeremias.paddlechampion.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TEAM_TOURNAMENT")
public class TeamTournament implements Serializable {

  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long teamTournamentId;

  @ManyToOne(fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  @JoinColumn(name = "TEAM_ID")
  private TeamEntity team;

  @ManyToOne(fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  @JoinColumn(name = "TOURNAMENT_ID")
  private TournamentEntity tournament;

  @Column(name = "MATCHES_PLAYED")
  private Integer matchesPlayed = 0;

  @Column(name = "MATCHES_WON")
  private Integer matchesWon = 0;

  @Column(name = "MATCHES_LOST")
  private Integer matchesLost = 0;

  @Column(name = "POINTS")
  private Integer points = 0;

}


