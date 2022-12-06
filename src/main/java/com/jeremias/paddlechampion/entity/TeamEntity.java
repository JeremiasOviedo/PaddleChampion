package com.jeremias.paddlechampion.entity;

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
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TEAMS")
public class TeamEntity implements Serializable {

  public static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TEAM_ID")
  private Long teamId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "CATEGORY", nullable = false)
  private Integer category;

  @Column(name = "POINTS")
  private Integer points;

  @Column(name = "MAX_PLAYERS")
  private Integer maxPlayers;

  @ManyToMany(fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  @JoinTable(
      name = "TEAM_PLAYER",
      joinColumns = @JoinColumn(name = "TEAM_ID"),
      inverseJoinColumns = @JoinColumn(name = "USER_ID")
  )
  Set<UserEntity> players = new HashSet<>();

  @ManyToMany(mappedBy = "teams",
      fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  Set<TournamentEntity> tournaments = new HashSet<>();

  @ManyToMany(mappedBy = "matchTeams",
      fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  private List<MatchEntity> matches = new ArrayList<>();

  public void addUserToTeam(UserEntity user) {
    //  if (players.size() == maxPlayers) {
    //   System.out.println("Cannot add more players to team");
    //  } else {
    players.add(user);
  }
  // }

  public void deleteUserFromTeam(UserEntity user) {
    if (!players.contains(user)) {
      System.out.println("User doesnt belong to team");
    } else {
      players.remove(user);
    }
  }

}
