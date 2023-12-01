package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class EmailAlreadyExistException extends BadRequestException {
  public EmailAlreadyExistException(){
    setCode("com.learn.SpendingManagement.exception.user.EmailAlreadyExistException");
  }
}
