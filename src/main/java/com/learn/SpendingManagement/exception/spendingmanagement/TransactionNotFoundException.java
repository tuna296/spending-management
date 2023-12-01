package com.learn.SpendingManagement.exception.spendingmanagement;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class TransactionNotFoundException extends BadRequestException {
  public TransactionNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.spendingmanagement.TransactionNotFoundException");
  }
}
