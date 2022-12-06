package com.jeremias.paddlechampion.entity;

import com.jeremias.paddlechampion.enumeration.Inscription;
import com.jeremias.paddlechampion.mapper.exception.MaxTeamsException;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TOURNAMENTS")
public class TournamentEntity implements Serializable {

  public static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TOURNAMENT_ID")
  private Long tournamentId;

  @Column(name = "TOURNAMENT_NAME", nullable = false)
  private String name;

  @Column(name = "MAX_CATEGORY", nullable = false)
  private Integer maxCategory;

  @Column(name = "MAX_TEAMS", nullable = false)
  private Integer maxTeams;

  @Column(name = "INSCRIPTION")
  private Inscription inscriptionStatus;

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
      name = "TOURNAMENT_TEAM",
      joinColumns = @JoinColumn(name = "TOURNAMENT_ID"),
      inverseJoinColumns = @JoinColumn(name = "TEAM_ID")
  )
  List<TeamEntity> teams = new ArrayList<>();


  @Column(name = "MATCHES", columnDefinition = "TEXT")
  @OneToMany(mappedBy = "tournament",
      fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  private List<MatchEntity> matchEntities;

  @ManyToOne(fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  @JoinColumn(name = "USER_ID")
  UserEntity user;

  public void addTeam(TeamEntity team) {

    if (teams.size() == maxTeams) {
      throw new MaxTeamsException("Cannot add more teams.");
    } else {
      teams.add(team);
    }
  }

  public void removeTeam(TeamEntity team) {

    if (!teams.remove(team)) {
      throw new ParamNotFound("Team doesn't belong in the tournament");
    } else {
      teams.remove(team);
    }

  }

}
