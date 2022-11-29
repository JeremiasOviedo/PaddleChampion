package com.jeremias.paddlechampion.entity;

import com.jeremias.paddlechampion.enumeration.Inscription;
import com.jeremias.paddlechampion.model.Match;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
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

  @Column(name="MAX_TEAMS", nullable = false)
  private Integer maxTeams;

  @Column(name = "INSCRIPTION")
  private Inscription inscriptionStatus;

  private List<Match> matches;

  @ManyToMany(mappedBy = "tournaments")
  Set<TeamEntity> teams;

  @ManyToOne
  @JoinColumn(name = "USER_ID", nullable = false)
  UserEntity user;

}
