package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class AccountNotFoundException extends BadRequestException {
  public AccountNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.account.AccountNotFoundException");
  }
}
