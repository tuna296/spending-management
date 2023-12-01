package com.learn.SpendingManagement.entity.user;

import com.learn.SpendingManagement.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {
  @Column(name = "addresses_id")
  private String addressId;
  @Column(name = "account_id")
  private String accountId;
  @Column(name = "email")
  private String email;
  @Column(name = "phone")
  private String phone;
  @Column(name = "full_name")
  private String fullName;
  @Column(name = "is_deleted")
  boolean isDeleted;

  public User(String addressId, String accountId, String email, String phone, String fullName) {
    this.addressId = addressId;
    this.accountId = accountId;
    this.email = email;
    this.phone = phone;
    this.fullName = fullName;
    isDeleted = false;
  }
}
