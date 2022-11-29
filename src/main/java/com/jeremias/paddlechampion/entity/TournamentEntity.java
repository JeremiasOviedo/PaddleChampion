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
@Table(name = "TOURNAMENTS")
public class TournamentEntity implements Serializable {

  public static final Long serialVersionUID = 1l;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TOURNAMENT_ID")
  private Long tournamentId;

  @Column(name = "TOURNAMENT_NAME", nullable = false)
  private String name;

  @Column(name = "MAX_CATEGORY", nullable = false)
  private Integer maxCategory;

  @ManyToMany(mappedBy = "tournaments")
  Set<TeamEntity> teams;

}
