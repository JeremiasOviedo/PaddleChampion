package com.jeremias.paddlechampion.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "USERS")

public class UserEntity implements Serializable {

  public static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long userId;

  @Column(name = "FIRST_NAME", nullable = false)
  private String firstName;

  @Column(name = "LAST_NAME", nullable = false)
  private String lastName;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "CREATION_DATE")
  @CreationTimestamp
  private Date creationDate;

  @Column(name = "UPDATE_DATE")
  @UpdateTimestamp
  private Date updateDate;

  @Column(name = "CATEGORY", nullable = false)
  private Integer category;

  @ManyToMany(mappedBy = "players",
      fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  List<TeamEntity> teams = new ArrayList<>();

  @OneToMany(mappedBy = "user",
      fetch = FetchType.LAZY,
      cascade
          = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      })
  Set<TournamentEntity> tournaments;

  @ManyToOne
  @JoinColumn(name = "ROLE_ID")
  private RoleEntity role;


}
