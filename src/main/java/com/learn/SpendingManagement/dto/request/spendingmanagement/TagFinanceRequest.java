package com.learn.SpendingManagement.dto.request.spendingmanagement;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagFinanceRequest {
  @NotBlank(message = "userId not blank")
  private String userId;
  @NotBlank(message = "Name not blank")
  private String name;
  @NotBlank(message = "Description not blank")
  private String description;
}
