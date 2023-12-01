package com.learn.SpendingManagement.dto.response.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AccountResponse {
  private String id;
  private String username;
  private boolean isActivated;
  private String roleName;

  public AccountResponse(String id, String username) {
    this.id = id;
    this.username = username;
  }

  public AccountResponse(String id, String username, String roleName) {
    this.id = id;
    this.username = username;
    this.roleName= roleName;
    isActivated = true;
  }
}