package com.learn.SpendingManagement.dto.response.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private String id;
  private String email;
  private String phone;
  private String fullName;
  private AccountResponse accountResponse;
  private AddressResponse addressResponse;
  private RoleResponse roleResponse;

  public UserResponse(String id, String email, String phone, String fullName) {
    this.id = id;
    this.email = email;
    this.phone = phone;
    this.fullName = fullName;
  }

  public UserResponse(String id, String email, String phone, String fullName,
                      String accountId, String username,
                      String addressId, String province, String district, String ward,
                      String roleId, String name, String description

  ) {
    this.id = id;
    this.email = email;
    this.phone = phone;
    this.fullName = fullName;
    accountResponse = new AccountResponse(accountId, username);
    addressResponse = new AddressResponse(addressId, province, district, ward);
    roleResponse = new RoleResponse(roleId, name, description);
  }
}
