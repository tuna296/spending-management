package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class AccountIsDeactivated extends BadRequestException {
  public AccountIsDeactivated() {
    setCode("com.learn.SpendingManagement.exception.user.AccountIsDeactivated");
  }
}
