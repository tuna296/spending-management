package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class RoleNotFoundException extends BadRequestException {
  public RoleNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.account.RoleNotFoundException");
  }
}
