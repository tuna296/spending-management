package com.learn.SpendingManagement.dto.response.spendingmanagement;

import com.learn.SpendingManagement.dto.response.User.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagFinanceResponse {
  private String id;
  private String name;
  private String description;
  private UserResponse userResponse;
  private List<TransactionResponse> transactionResponses;
  public TagFinanceResponse(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

//  public TagFinanceResponse(
//        String id, String name, String description,
//        String userId, String email, String phone, String fullName,
//        String idTransaction, String tagFinanceId, String title, Double amount, String descriptionTran
//  ) {
//    this.id = id;
//    this.name = name;
//    this.description = description;
//    this.userResponse = new UserResponse(userId, email, phone, fullName);
//    this.transactionResponse = new TransactionResponse(idTransaction, title, amount, descriptionTran);
//  }
}
