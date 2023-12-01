package com.learn.SpendingManagement.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleRequest {
  @NotBlank(message = "Name is not blank")
  private String name;
  @NotBlank(message = "Description is not blank")
  private String description;
}
