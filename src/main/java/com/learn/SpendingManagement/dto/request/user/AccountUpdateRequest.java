package com.learn.SpendingManagement.dto.request.user;

import com.learn.SpendingManagement.annotation.ValidationPassword;
import com.learn.SpendingManagement.annotation.ValidationUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountUpdateRequest {
  @ValidationUsername
  private String username;
  private String oldPassword;
  @ValidationPassword
  private String newPassword;
  @ValidationPassword
  private String confirmPassword;
  private String roleId;
}