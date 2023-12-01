package com.learn.SpendingManagement.exception.spendingmanagement;

import com.learn.SpendingManagement.exception.base.BadRequestException;

public class TagFinanceNotFoundException extends BadRequestException {
  public TagFinanceNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.spendingmanagement.TagFinanceNotFoundException");
  }
}
