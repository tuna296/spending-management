package com.learn.SpendingManagement.dto.request.user;

import com.learn.SpendingManagement.annotation.ValidationPassword;
import com.learn.SpendingManagement.annotation.ValidationUsername;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRequest {
  @ValidationUsername
  @NotBlank(message = "Username Not Blank")
  private String username;
  @ValidationPassword
  @NotBlank(message = "Password Not Blank")
  private String password;
//  @NotBlank(message = "RoleId not blank")
  private String roleId;
}