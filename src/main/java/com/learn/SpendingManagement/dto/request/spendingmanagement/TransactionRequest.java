package com.learn.SpendingManagement.dto.request.spendingmanagement;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
  @NotBlank(message = "TagFinanceId not null")
  private String tagFinanceId;
  @NotBlank(message = "Title not null")
  private String title;
  @NotNull
  @DecimalMin(value = "0")
  @NotNull(message = "Amount not null")
  private Double amount;
  @NotBlank(message = "Description not null")
  private String description;
}
