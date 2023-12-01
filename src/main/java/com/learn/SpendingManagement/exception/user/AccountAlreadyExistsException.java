package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class AccountAlreadyExistsException extends BadRequestException {
  public AccountAlreadyExistsException(){
    setCode("com.learn.SpendingManagement.exception.account.AccountAlreadyExistsException");
  }
}
