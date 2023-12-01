package com.learn.SpendingManagement.exception.base;

import static com.learn.SpendingManagement.exception.base.StatusConstants.BAD_REQUEST;

public class BadRequestException extends BaseException {
  public BadRequestException() {
    setCode("com.learn.SpendingManagement.exception.base.BadRequestException");
    setStatus(BAD_REQUEST);
  }

}