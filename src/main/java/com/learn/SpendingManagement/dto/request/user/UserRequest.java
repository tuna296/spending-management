package com.learn.SpendingManagement.dto.request.user;

import com.learn.SpendingManagement.annotation.ValidationEmail;
import com.learn.SpendingManagement.annotation.ValidationPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
  @NotBlank(message = "addressId not blank")
  private String addressId;
  @NotBlank(message = "accountId not blank")
  private String accountId;;
  @ValidationEmail
  private String email;
  @ValidationPhoneNumber
  private String phone;
  @NotBlank(message = "fullName not blank")
  private String fullName;

}
