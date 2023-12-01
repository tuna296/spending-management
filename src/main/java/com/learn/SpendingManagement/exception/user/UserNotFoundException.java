package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class UserNotFoundException extends BadRequestException {
  public UserNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.user.UserNotFoundException");
  }
}
