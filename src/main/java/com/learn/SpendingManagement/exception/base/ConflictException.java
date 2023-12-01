package com.learn.SpendingManagement.exception.base;


import static com.learn.SpendingManagement.exception.base.StatusConstants.CONFLICT;

public class ConflictException extends BaseException {
  public ConflictException(String id, String objectName) {
    setStatus(CONFLICT);
    setCode("com.lawnman.payment.server.exception.base.ConflictException");
    addParam("id", id);
    addParam("objectName", objectName);
  }

  public ConflictException(){
    setStatus(CONFLICT);
    setCode("com.lawnman.payment.server.exception.base.ConflictException");
  }
}

