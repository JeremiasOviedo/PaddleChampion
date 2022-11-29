package com.jeremias.paddlechampion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;

@Data
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

  @ManyToMany(mappedBy = "teams")
  Set<UserEntity> players;
}
