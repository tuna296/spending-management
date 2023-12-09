package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
  public AccountNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.account.AccountNotFoundException");
  }
}
