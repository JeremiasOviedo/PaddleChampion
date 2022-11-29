package com.jeremias.paddlechampion.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "USERS")

@SQLDelete(sql = "UPDATE users SET deleted = true WHERE user_id=?")
@Where(clause = "deleted=false")
public class UserEntity implements Serializable {

  public static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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


}