package com.learn.SpendingManagement.exception.spendingmanagement;

import com.learn.SpendingManagement.exception.base.NotFoundException;

public class TransactionNotFoundException extends NotFoundException {
  public TransactionNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.spendingmanagement.TransactionNotFoundException");
  }
}
