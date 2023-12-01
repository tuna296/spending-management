package com.learn.SpendingManagement.exception.spendingmanagement;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class TagFinanceAlreadyExistException extends BadRequestException {
  public TagFinanceAlreadyExistException(){
    setCode("com.learn.SpendingManagement.exception.spendingmanagement.TagFinanceAlreadyExistException");
  }
}
