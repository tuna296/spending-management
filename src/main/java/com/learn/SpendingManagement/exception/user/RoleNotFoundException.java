package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
  public RoleNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.account.RoleNotFoundException");
  }
}
