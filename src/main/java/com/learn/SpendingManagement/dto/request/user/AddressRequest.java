package com.learn.SpendingManagement.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressRequest {
  @NotBlank(message = "Province not blank")
  private String province;
  @NotBlank(message = "District not blank")
  private String district;
  @NotBlank(message = "Ward not blank")
  private String ward;
}
