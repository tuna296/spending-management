package com.learn.SpendingManagement.entity.user;

import com.learn.SpendingManagement.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(staticName = "of")
@Table(name = "accounts")
public class Account extends BaseEntity {
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "is_deleted")
  private boolean isDeleted;

  @Column(name = "is_activated")
  private boolean isActivated;

  @Column(name = "role_id")
  private String roleId;


  public Account(String username, String password) {
    this.username = username;
    this.password = password;
    isDeleted = false;
    isActivated = true;
  }

  public Account(String username, String password, Boolean isActivated) {
    this.username = username;
    this.password = password;
    this.isActivated = isActivated;
  }

  public Account(String username, String password, String roleId) {
    this.username = username;
    this.password = password;
    this.roleId = roleId;
    isDeleted = false;
    isActivated = true;
  }
}
