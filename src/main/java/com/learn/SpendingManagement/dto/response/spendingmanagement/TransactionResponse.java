package com.learn.SpendingManagement.dto.response.spendingmanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
  private String id;
  private String title;
  private Double amount;
  private String description;
  private TagFinanceResponse tagFinanceResponse;
  public TransactionResponse(String id, String title,Double amount, String description){
    this.id = id;
    this.title= title;
    this.amount= amount;
    this.description= description;
  }
  public TransactionResponse(
        String id, String title, Double amount, String description,
        String tagId, String name, String descriptionTag
  ){
    this.id= id;
    this.title= title;
    this.amount=amount;
    this.description=description;
    this.tagFinanceResponse= new TagFinanceResponse(tagId, name, descriptionTag);
  }
}
