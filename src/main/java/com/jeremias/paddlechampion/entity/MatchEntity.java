package com.jeremias.paddlechampion.entity;

import com.jeremias.paddlechampion.entity.TeamEntity;

import com.jeremias.paddlechampion.enumeration.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="MATCHES")
@NoArgsConstructor
public class MatchEntity implements Serializable {

  private static final Long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long matchId;
  @Column(name = "TEAM_A")
  private String teamA;

  @Column(name = "TEAM_B")
  private String teamB;
  @Column(name = "TEAM_A_SCORE")
  private Integer teamAScore;

  @Column(name = "TEAM_B_SCORE")
  private Integer teamBScore;

  @Column(name = "WINNER")
  private String winner;

  @Column
  @Enumerated(EnumType.STRING)
  private Status status;


  @ManyToMany(
      fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  @JoinTable(
      name = "MATCH_TEAM",
      joinColumns = @JoinColumn(name = "MATCH_ID"),
      inverseJoinColumns = @JoinColumn(name = "TEAM_ID")
  )
  private List<TeamEntity> matchTeams = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "TOURNAMENT_ID")
  private TournamentEntity tournament;

  public MatchEntity(TeamEntity teamA, TeamEntity teamB, Status status, TournamentEntity tournament) {

    matchTeams.add(teamA);
    matchTeams.add(teamB);
    this.teamAScore = 0;
    this.teamBScore = 0;
    this.teamA = matchTeams.get(0).getName();
    this.teamB = matchTeams.get(1).getName();
    this.status = status;
    this.tournament = tournament;


  }

}
