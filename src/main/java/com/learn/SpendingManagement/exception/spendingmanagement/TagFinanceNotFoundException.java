package com.learn.SpendingManagement.exception.spendingmanagement;

import com.learn.SpendingManagement.exception.base.NotFoundException;

public class TagFinanceNotFoundException extends NotFoundException {
  public TagFinanceNotFoundException(){
    setCode("com.learn.SpendingManagement.exception.spendingmanagement.TagFinanceNotFoundException");
  }
}
