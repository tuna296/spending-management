package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class RoleAlreadyExistException extends BadRequestException {
  public RoleAlreadyExistException(){
    setCode("com.learn.SpendingManagement.exception.account.RoleAlreadyExistException");
  }
}
