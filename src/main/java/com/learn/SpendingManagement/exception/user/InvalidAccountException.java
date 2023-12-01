package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class InvalidAccountException extends BadRequestException {
  public InvalidAccountException(){
    setCode("com.learn.SpendingManagement.exception.account.InvalidAccountException");
  }
}
