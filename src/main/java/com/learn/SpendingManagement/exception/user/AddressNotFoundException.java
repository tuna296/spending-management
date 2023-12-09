package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.NotFoundException;

public class AddressNotFoundException extends NotFoundException {
  public AddressNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.user.AddressNotFoundException");
  }
}
