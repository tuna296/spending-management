package com.learn.SpendingManagement.dto.response.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleResponse {
  private String id;
  private String name;
  private String description;
}
