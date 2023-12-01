package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class AddressNotFoundException extends BadRequestException {
  public AddressNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.user.AddressNotFoundException");
  }
}
