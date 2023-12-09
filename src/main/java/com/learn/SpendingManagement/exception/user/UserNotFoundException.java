package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.NotFoundException;

public class UserNotFoundException extends NotFoundException {
  public UserNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.user.UserNotFoundException");
  }
}
