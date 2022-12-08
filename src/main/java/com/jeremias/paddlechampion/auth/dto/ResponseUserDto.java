package com.jeremias.paddlechampion.auth.dto;

import com.jeremias.paddlechampion.dto.TeamDto;
import com.jeremias.paddlechampion.dto.TournamentDto;
import com.jeremias.paddlechampion.enumeration.RoleName;
import java.sql.Date;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto {

  private Long id;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @NotEmpty
  @Email(message = "the email must be real email")
  private String email;
  @NotEmpty
  private String password;
  private RoleName role;
  private List<TeamDto> teams;
  private List<TournamentDto> tournaments;
  private Integer category;
  private Date creationDate;
  private Date updateDate;
  private String jwt;
}
