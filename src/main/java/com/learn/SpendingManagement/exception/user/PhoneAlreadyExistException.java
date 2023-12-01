package com.learn.SpendingManagement.exception.user;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class PhoneAlreadyExistException extends BadRequestException {
public PhoneAlreadyExistException(){
  setCode("com.learn.SpendingManagement.exception.user.PhoneAlreadyExistException");
}
}
