package com.jeremias.paddlechampion.dto;

import com.jeremias.paddlechampion.enumeration.RoleName;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleDto {

  private Long roleId;
  @NotNull
  private RoleName roleName;
  private String description;
}
